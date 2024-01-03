package com.crud.serverside.controller;

import com.crud.serverside.exception.ResourceNotFoundException;
import com.crud.serverside.model.Task;
import com.crud.serverside.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    // Get All Tasks
    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Create New Task
    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
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
}
