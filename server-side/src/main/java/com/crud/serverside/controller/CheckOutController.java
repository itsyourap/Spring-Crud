package com.crud.serverside.controller;


import com.crud.serverside.model.CheckOut;
import com.crud.serverside.model.Employee;
import com.crud.serverside.repository.CheckOutRepository;
import com.crud.serverside.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class CheckOutController {
    @Autowired
    private CheckOutRepository checkOutRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // Get All Check-Outs
    @GetMapping("/check-out")
    public List<CheckOut> getAllCheckOuts() {
        return checkOutRepository.findAll();
    }

    // Create New Check Out Entry
    @PostMapping("/check-out")
    public ResponseEntity<CheckOut> createTask(@RequestBody CheckOut checkOut) {
        Optional<Employee> employeeInDb = employeeRepository.findById(checkOut.getEmployee().getId());
        if (employeeInDb.isEmpty())
            return ResponseEntity.badRequest().body(checkOut);
        return ResponseEntity.ok(checkOutRepository.save(checkOut));
    }
}
