package com.crud.serverside.controller;

import com.crud.serverside.exception.ResourceNotFoundException;
import com.crud.serverside.model.Employee;
import com.crud.serverside.model.Meeting;
import com.crud.serverside.repository.EmployeeRepository;
import com.crud.serverside.repository.MeetingRepository;
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
public class MeetingController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MeetingRepository meetingRepository;

    // Get All Meetings
    @GetMapping("/meetings")
    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    // Create New Meeting
    @PostMapping("/meetings")
    public ResponseEntity<Meeting> createMeeting(@RequestBody Meeting meeting) {
        for (Employee employee : meeting.getAllowedEmployees()){
            Optional<Employee> employeeInDb = employeeRepository.findById(employee.getId());
            if (employeeInDb.isEmpty() || !employeeInDb.get().equals(employee))
                return ResponseEntity.badRequest().body(meeting);
        }
        return ResponseEntity.ok(meetingRepository.save(meeting));
    }

    // Get Meeting by ID
    @GetMapping("/meetings/{id}")
    public ResponseEntity<Meeting> getMeetingById(@PathVariable Long id) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meeting does not exist with id:" + id));

        return ResponseEntity.ok(meeting);
    }

    // Update Meeting by ID
    @PutMapping("/meetings/{id}")
    public ResponseEntity<Meeting> updateMeeting(@PathVariable Long id, @RequestBody Meeting meetingDetails) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meeting does not exist with id:" + id));

        for (Employee employee : meeting.getAllowedEmployees()){
            Optional<Employee> employeeInDb = employeeRepository.findById(employee.getId());
            if (employeeInDb.isEmpty() || !employeeInDb.get().equals(employee))
                return ResponseEntity.badRequest().body(meeting);
        }

        meeting.setMeetingTopic(meetingDetails.getMeetingTopic());
        meeting.setMeetingLink(meeting.getMeetingLink());
        meeting.setAllowedEmployees(meetingDetails.getAllowedEmployees());

        Meeting updateMeeting = meetingRepository.save(meeting);

        return ResponseEntity.ok(updateMeeting);
    }

    // Delete Meeting by ID
    @DeleteMapping("/meetings/{id}")
    public Map<String, Boolean> deleteMeeting(@PathVariable Long id) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meeting does not exist with id:" + id));
        meetingRepository.delete(meeting);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }

    // Add Employee to Meeting by ID
    @PutMapping("/meetings/{meetingId}/employees")
    public ResponseEntity<Meeting> assignEmployeeToMeeting(@PathVariable Long meetingId, @RequestBody Map<String, Long> data) {
        long employeeId = data.containsKey("employeeId") ? data.get("employeeId") : -1;

        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new ResourceNotFoundException("Meeting does not exist with id:" + meetingId));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id:" + employeeId));

        boolean assigned = meeting.allowEmployee(employee);
        Meeting updatedMeeting = meetingRepository.save(meeting);
        if (assigned)
            return ResponseEntity.ok(updatedMeeting);
        else
            return ResponseEntity.badRequest().body(updatedMeeting);
    }

    // Remove Employee from Meeting
    @DeleteMapping("/meetings/{meetingId}/employees/{employeeId}")
    public ResponseEntity<Meeting> removeEmployeeFromMeeting(@PathVariable Long meetingId, @PathVariable Long employeeId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new ResourceNotFoundException("Meeting does not exist with id:" + meetingId));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id:" + employeeId));

        boolean removed = meeting.denyEmployee(employee);
        Meeting updatedMeeting = meetingRepository.save(meeting);
        if (removed)
            return ResponseEntity.ok(updatedMeeting);
        else
            return ResponseEntity.badRequest().body(updatedMeeting);
    }
}
