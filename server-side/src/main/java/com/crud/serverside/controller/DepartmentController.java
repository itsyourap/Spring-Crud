package com.crud.serverside.controller;

import com.crud.serverside.exception.ResourceNotFoundException;
import com.crud.serverside.model.Department;
import com.crud.serverside.model.Employee;
import com.crud.serverside.repository.DepartmentRepository;
import com.crud.serverside.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class DepartmentController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Create New Department
    @PostMapping("/departments")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        for (Employee employee : department.getAssignedEmployees()){
            Optional<Employee> employeeInDb = employeeRepository.findById(employee.getId());
            if (employeeInDb.isEmpty() || !employeeInDb.get().equals(employee))
                return ResponseEntity.badRequest().body(department);
        }
        return ResponseEntity.ok(departmentRepository.save(department));
    }

    // Get Department by ID
    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department does not exist with id:" + id));

        return ResponseEntity.ok(department);
    }

    // Update Department by ID
    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department departmentDetails) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department does not exist with id:" + id));

        for (Employee employee : department.getAssignedEmployees()){
            Optional<Employee> employeeInDb = employeeRepository.findById(employee.getId());
            if (employeeInDb.isEmpty() || !employeeInDb.get().equals(employee))
                return ResponseEntity.badRequest().body(department);
        }

        department.setDepartmentName(departmentDetails.getDepartmentName());
        department.setAssignedEmployees(departmentDetails.getAssignedEmployees());

        Department updateDepartment = departmentRepository.save(department);

        return ResponseEntity.ok(updateDepartment);
    }

    // Delete Department by ID
    @DeleteMapping("/departments/{id}")
    public Map<String, Boolean> deleteDepartment(@PathVariable Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department does not exist with id:" + id));
        departmentRepository.delete(department);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }

    // Add Employee to Department by ID
    @PutMapping("/departments/{departmentId}/employees")
    public ResponseEntity<Department> assignEmployeeToDepartment(@PathVariable Long departmentId, @RequestBody Map<String, Long> data) {
        long employeeId = data.containsKey("employeeId") ? data.get("employeeId") : -1;

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department does not exist with id:" + departmentId));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id:" + employeeId));

        boolean assigned = department.assignEmployee(employee);
        Department updatedDepartment = departmentRepository.save(department);
        if (assigned)
            return ResponseEntity.ok(updatedDepartment);
        else
            return ResponseEntity.badRequest().body(updatedDepartment);
    }

    // Remove Employee from Department
    @DeleteMapping("/departments/{departmentId}/employees/{employeeId}")
    public ResponseEntity<Department> removeEmployeeFromDepartment(@PathVariable Long departmentId, @PathVariable Long employeeId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department does not exist with id:" + departmentId));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id:" + employeeId));

        boolean removed = department.removeEmployeeAssignment(employee);
        Department updatedDepartment = departmentRepository.save(department);
        if (removed)
            return ResponseEntity.ok(updatedDepartment);
        else
            return ResponseEntity.badRequest().body(updatedDepartment);
    }
}
