package com.crud.serverside.controller;

import com.crud.serverside.exception.ResourceNotFoundException;
import com.crud.serverside.model.Employee;
import com.crud.serverside.model.Inventory;
import com.crud.serverside.repository.EmployeeRepository;
import com.crud.serverside.repository.InventoryRepository;
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
public class InventoryController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping("/inventory")
    public List<Inventory> getAllInventoryItems() {
        return inventoryRepository.findAll();
    }

    @PostMapping("/inventory")
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        Optional<Employee> employeeInDb = employeeRepository.findById(inventory.getOwner().getId());
        if (employeeInDb.isEmpty())
            return ResponseEntity.badRequest().body(inventory);

        return ResponseEntity.ok(inventoryRepository.save(inventory));
    }

    // Get Inventory by ID
    @GetMapping("/inventory/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory does not exist with id:" + id));

        return ResponseEntity.ok(inventory);
    }

    @PutMapping("/inventory/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Long id, @RequestBody Inventory inventoryDetails) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory does not exist with id:" + id));

        inventory.setOwner(inventoryDetails.getOwner());
        inventory.setInventoryItem(inventoryDetails.getInventoryItem());

        Inventory updateInventory = inventoryRepository.save(inventory);
        return ResponseEntity.ok(updateInventory);
    }

    // Delete Inventory
    @DeleteMapping("/inventory/{id}")
    public Map<String, Boolean> deleteInventory(@PathVariable Long id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory does not exist with id:" + id));
        inventoryRepository.delete(inventory);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }
}
