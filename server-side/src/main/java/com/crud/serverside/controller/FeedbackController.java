package com.crud.serverside.controller;

import com.crud.serverside.exception.ResourceNotFoundException;
import com.crud.serverside.model.Employee;
import com.crud.serverside.model.Feedback;
import com.crud.serverside.repository.EmployeeRepository;
import com.crud.serverside.repository.FeedbackRepository;
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
public class FeedbackController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @GetMapping("/feedbacks")
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @PostMapping("/feedbacks")
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback) {
        Optional<Employee> employeeInDb = employeeRepository.findById(feedback.getEmployee().getId());
        if (employeeInDb.isEmpty())
            return ResponseEntity.badRequest().body(feedback);

        return ResponseEntity.ok(feedbackRepository.save(feedback));
    }

    // Get Feedback by ID
    @GetMapping("/feedbacks/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback does not exist with id:" + id));

        return ResponseEntity.ok(feedback);
    }

    @PutMapping("/feedbacks/{id}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable Long id, @RequestBody Feedback feedbackDetails) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback does not exist with id:" + id));

        feedback.setEmployee(feedbackDetails.getEmployee());
        feedback.setFeedback(feedbackDetails.getFeedback());

        Feedback updateFeedback = feedbackRepository.save(feedback);
        return ResponseEntity.ok(updateFeedback);
    }

    // Delete Feedback
    @DeleteMapping("/feedbacks/{id}")
    public Map<String, Boolean> deleteFeedback(@PathVariable Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback does not exist with id:" + id));
        feedbackRepository.delete(feedback);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }
}
