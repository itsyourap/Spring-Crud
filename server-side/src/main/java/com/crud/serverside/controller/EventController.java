package com.crud.serverside.controller;

import com.crud.serverside.exception.ResourceNotFoundException;
import com.crud.serverside.model.Employee;
import com.crud.serverside.model.Event;
import com.crud.serverside.repository.EmployeeRepository;
import com.crud.serverside.repository.EventRepository;
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
public class EventController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EventRepository eventRepository;

    // Get All Events
    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Create New Event
    @PostMapping("/events")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return ResponseEntity.ok(eventRepository.save(event));
    }

    // Get Event by ID
    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event does not exist with id:" + id));

        return ResponseEntity.ok(event);
    }

    // Update Event by ID
    @PutMapping("/events/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event does not exist with id:" + id));

        event.setEventName(eventDetails.getEventName());
        event.setEventDescription(event.getEventDescription());
        Event updateEvent = eventRepository.save(event);
        return ResponseEntity.ok(updateEvent);
    }

    // Delete Event by ID
    @DeleteMapping("/events/{id}")
    public Map<String, Boolean> deleteEvent(@PathVariable Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event does not exist with id:" + id));
        eventRepository.delete(event);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }
}
