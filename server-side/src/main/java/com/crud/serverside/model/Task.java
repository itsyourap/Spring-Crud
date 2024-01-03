package com.crud.serverside.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_description")
    private String taskDescription;

    @ManyToMany
    @JoinTable(
            name = "employee_task",
            joinColumns = {@JoinColumn(name = "task_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")}
    )
    private List<Employee> assignedEmployees;

    public Task(){

    }

    public Task(long id, String taskName, String taskDescription) {
        this.id = id;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.assignedEmployees = new ArrayList<>();
    }

    public Task(long id, String taskName, String taskDescription, List<Employee> assignedEmployees) {
        this.id = id;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.assignedEmployees = assignedEmployees;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public List<Employee> getAssignedEmployees() {
        return assignedEmployees;
    }

    public void setAssignedEmployees(List<Employee> assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
    }

    public boolean assignEmployee(Employee employee){
        return this.assignedEmployees.add(employee);
    }

    public boolean removeEmployeeAssignment(Employee employee){
        return this.assignedEmployees.remove(employee);
    }
}
