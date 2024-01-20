package com.crud.serverside.model;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee owner;

    @Column(name = "inventory_item")
    private String inventoryItem;

    public Inventory() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Employee getOwner() {
        return owner;
    }

    public void setOwner(Employee employee) {
        this.owner = employee;
    }

    public String getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(String inventory) {
        this.inventoryItem = inventory;
    }
}