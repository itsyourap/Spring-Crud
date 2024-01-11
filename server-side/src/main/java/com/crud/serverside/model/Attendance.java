package com.crud.serverside.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date")
    private LocalDate date;

    @ManyToMany
    @JoinTable(
            name = "employee_attendance",
            joinColumns = {@JoinColumn(name = "attendance_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")}
    )
    private List<Employee> presentEmployees;

    public Attendance(){

    }

    public Attendance(long id, LocalDate date) {
        this.id = id;
        this.date = date;
        this.presentEmployees = new ArrayList<>();
    }

    public Attendance(long id, LocalDate date, List<Employee> presentEmployees) {
        this.id = id;
        this.date = date;
        this.presentEmployees = presentEmployees;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Employee> getPresentEmployees() {
        return presentEmployees;
    }

    public void setPresentEmployees(List<Employee> presentEmployees) {
        this.presentEmployees = presentEmployees;
    }

    public boolean  markEmployeeAsPresent(Employee employee){
        if (presentEmployees.stream().anyMatch(o -> o.getId() == employee.getId()))
            return false;

        return presentEmployees.add(employee);
    }

    public boolean markEmployeeAsAbsent(Employee employee){
        if (presentEmployees.stream().anyMatch(o ->  o.getId() == employee.getId()))
            return presentEmployees.remove(employee);

        return false;
    }
}
