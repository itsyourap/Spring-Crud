package com.crud.serverside.controller;

import com.crud.serverside.exception.ResourceNotFoundException;
import com.crud.serverside.model.Employee;
import com.crud.serverside.model.Task;
import com.crud.serverside.repository.EmployeeRepository;
import com.crud.serverside.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/")
public class TaskController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TaskRepository taskRepository;

    // Get All Tasks
    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Create New Task
    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        for (Employee employee : task.getAssignedEmployees()){
            Optional<Employee> employeeInDb = employeeRepository.findById(employee.getId());
            if (employeeInDb.isEmpty() || !employeeInDb.get().equals(employee))
                return ResponseEntity.badRequest().body(task);
        }
        return ResponseEntity.ok(taskRepository.save(task));
    }

    // Get Task by ID
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task does not exist with id:" + id));

        return ResponseEntity.ok(task);
    }

    // Update Task by ID
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task does not exist with id:" + id));

        for (Employee employee : task.getAssignedEmployees()){
            Optional<Employee> employeeInDb = employeeRepository.findById(employee.getId());
            if (employeeInDb.isEmpty() || !employeeInDb.get().equals(employee))
                return ResponseEntity.badRequest().body(task);
        }

        task.setTaskName(taskDetails.getTaskName());
        task.setTaskDescription(task.getTaskDescription());
        task.setAssignedEmployees(taskDetails.getAssignedEmployees());

        Task updateTask = taskRepository.save(task);

        return ResponseEntity.ok(updateTask);
    }

    // Delete Task by ID
    @DeleteMapping("/tasks/{id}")
    public Map<String, Boolean> deleteTask(@PathVariable Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task does not exist with id:" + id));
        taskRepository.delete(task);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }

    // Add Employee to Task by ID
    @PutMapping("/tasks/{taskId}/employees")
    public ResponseEntity<Task> assignEmployeeToTask(@PathVariable Long taskId, @RequestBody Map<String, Long> data) {
        long employeeId = data.containsKey("employeeId") ? data.get("employeeId") : -1;

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task does not exist with id:" + taskId));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id:" + employeeId));

        boolean assigned = task.assignEmployee(employee);
        Task updatedTask = taskRepository.save(task);
        if (assigned)
            return ResponseEntity.ok(updatedTask);
        else
            return ResponseEntity.badRequest().body(updatedTask);
    }

    // Remove Employee from Task
    @DeleteMapping("/tasks/{taskId}/employees/{employeeId}")
    public ResponseEntity<Task> removeEmployeeFromTask(@PathVariable Long taskId, @PathVariable Long employeeId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task does not exist with id:" + taskId));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id:" + employeeId));

        boolean removed = task.removeEmployeeAssignment(employee);
        Task updatedTask = taskRepository.save(task);
        if (removed)
            return ResponseEntity.ok(updatedTask);
        else
            return ResponseEntity.badRequest().body(updatedTask);
    }
}
