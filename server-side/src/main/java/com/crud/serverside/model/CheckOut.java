package com.crud.serverside.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class CheckOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    public CheckOut(){

    }

    public CheckOut(long id, Employee employee, LocalDateTime dateTime) {
        this.id = id;
        this.employee = employee;
        this.dateTime = dateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
