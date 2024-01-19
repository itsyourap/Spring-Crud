package com.crud.serverside.controller;

import com.crud.serverside.model.Employee;
import com.crud.serverside.repository.EmployeeRepository;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/")
public class LoginController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private FirebaseApp firebaseApp;

    @PostMapping("/login")
    public Employee loginEmployee(@RequestBody Map<String, String> loginData) throws FirebaseAuthException {
        String uid = loginData.get("uid");
        UserRecord userRecord = FirebaseAuth.getInstance(firebaseApp).getUser(uid);
        return employeeRepository.findEmployeeByEmailId(userRecord.getEmail());
    }
}
