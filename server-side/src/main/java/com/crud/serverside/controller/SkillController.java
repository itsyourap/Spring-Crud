package com.crud.serverside.controller;


import com.crud.serverside.exception.ResourceNotFoundException;
import com.crud.serverside.model.Employee;
import com.crud.serverside.model.Skill;
import com.crud.serverside.repository.EmployeeRepository;
import com.crud.serverside.repository.SkillRepository;
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
public class SkillController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SkillRepository skillRepository;

    // Get All Skills
    @GetMapping("/skills")
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    // Create New Skill
    @PostMapping("/skills")
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) {
        for (Employee employee : skill.getSkilledEmployees()){
            Optional<Employee> employeeInDb = employeeRepository.findById(employee.getId());
            if (employeeInDb.isEmpty() || !employeeInDb.get().equals(employee))
                return ResponseEntity.badRequest().body(skill);
        }
        return ResponseEntity.ok(skillRepository.save(skill));
    }

    // Get Skill by ID
    @GetMapping("/skills/{id}")
    public ResponseEntity<Skill> getSkillById(@PathVariable Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill does not exist with id:" + id));

        return ResponseEntity.ok(skill);
    }

    // Update Skill by ID
    @PutMapping("/skills/{id}")
    public ResponseEntity<Skill> updateSkill(@PathVariable Long id, @RequestBody Skill skillDetails) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill does not exist with id:" + id));

        for (Employee employee : skill.getSkilledEmployees()){
            Optional<Employee> employeeInDb = employeeRepository.findById(employee.getId());
            if (employeeInDb.isEmpty() || !employeeInDb.get().equals(employee))
                return ResponseEntity.badRequest().body(skill);
        }

        skill.setSkillName(skillDetails.getSkillName());
        skill.setSkillDescription(skill.getSkillDescription());
        skill.setSkilledEmployees(skillDetails.getSkilledEmployees());

        Skill updateSkill = skillRepository.save(skill);

        return ResponseEntity.ok(updateSkill);
    }

    // Delete Skill by ID
    @DeleteMapping("/skills/{id}")
    public Map<String, Boolean> deleteSkill(@PathVariable Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill does not exist with id:" + id));
        skillRepository.delete(skill);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }

    // Add Employee to Skill by ID
    @PutMapping("/skills/{skillId}/employees")
    public ResponseEntity<Skill> assignEmployeeToSkill(@PathVariable Long skillId, @RequestBody Map<String, Long> data) {
        long employeeId = data.containsKey("employeeId") ? data.get("employeeId") : -1;

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new ResourceNotFoundException("Skill does not exist with id:" + skillId));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id:" + employeeId));

        boolean assigned = skill.assignSkilledEmployee(employee);
        Skill updatedSkill = skillRepository.save(skill);
        if (assigned)
            return ResponseEntity.ok(updatedSkill);
        else
            return ResponseEntity.badRequest().body(updatedSkill);
    }

    // Remove Employee from Skill
    @DeleteMapping("/skills/{skillId}/employees/{employeeId}")
    public ResponseEntity<Skill> removeEmployeeFromSkill(@PathVariable Long skillId, @PathVariable Long employeeId) {
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new ResourceNotFoundException("Skill does not exist with id:" + skillId));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id:" + employeeId));

        boolean removed = skill.removeSkilledEmployee(employee);
        Skill updatedSkill = skillRepository.save(skill);
        if (removed)
            return ResponseEntity.ok(updatedSkill);
        else
            return ResponseEntity.badRequest().body(updatedSkill);
    }
}
