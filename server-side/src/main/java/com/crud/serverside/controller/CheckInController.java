package com.crud.serverside.controller;

import com.crud.serverside.model.CheckIn;
import com.crud.serverside.model.Employee;
import com.crud.serverside.repository.CheckInRepository;
import com.crud.serverside.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/")
public class CheckInController {
    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // Get All Check-Ins
    @GetMapping("/check-in")
    public List<CheckIn> getAllCheckIns() {
        return checkInRepository.findAll();
    }

    // Create New Check In Entry
    @PostMapping("/check-in")
    public ResponseEntity<CheckIn> createTask(@RequestBody CheckIn checkIn) {
        Optional<Employee> employeeInDb = employeeRepository.findById(checkIn.getEmployee().getId());
        if (employeeInDb.isEmpty())
            return ResponseEntity.badRequest().body(checkIn);
        return ResponseEntity.ok(checkInRepository.save(checkIn));
    }
}
