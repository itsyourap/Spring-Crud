package com.crud.serverside.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.serverside.model.Employee;
import com.crud.serverside.repository.EmployeeRepository;

@RestController

// rest api mapping
@RequestMapping("/api/v1/")
public class EmployeeController {

    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
