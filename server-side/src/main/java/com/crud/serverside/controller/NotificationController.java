package com.crud.serverside.controller;

import com.crud.serverside.exception.ResourceNotFoundException;
import com.crud.serverside.model.Notification;
import com.crud.serverside.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    // Get All Notifications
    @GetMapping("/notifications")
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // Create New Notification
    @PostMapping("/notifications")
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationRepository.save(notification);
    }

    // Get Notification by ID
    @GetMapping("/notifications/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification does not exist with id:" + id));

        return ResponseEntity.ok(notification);
    }

    @PutMapping("/notifications/{id}")
    public ResponseEntity<Notification> updateNotification(@PathVariable Long id, @RequestBody Notification notificationDetails) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification does not exist with id:" + id));

        notification.setNotificationTitle(notificationDetails.getNotificationTitle());
        notification.setNotificationContent(notificationDetails.getNotificationContent());

        Notification updateNotification = notificationRepository.save(notification);
        return ResponseEntity.ok(updateNotification);
    }

    // Delete Notification
    @DeleteMapping("/notifications/{id}")
    public Map<String, Boolean> deleteNotification(@PathVariable Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification does not exist with id:" + id));
        notificationRepository.delete(notification);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }
}
