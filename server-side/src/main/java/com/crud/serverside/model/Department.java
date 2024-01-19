package com.crud.serverside.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "department_name")
    private String departmentName;

    @ManyToMany
    @JoinTable(
            name = "employee_department",
            joinColumns = {@JoinColumn(name = "department_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")}
    )
    private List<Employee> assignedEmployees;

    public Department(){}

    public Department(long id, String departmentName, List<Employee> assignedEmployees) {
        this.id = id;
        this.departmentName = departmentName;
        this.assignedEmployees = assignedEmployees;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Employee> getAssignedEmployees() {
        return assignedEmployees;
    }

    public void setAssignedEmployees(List<Employee> assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
    }

    public boolean assignEmployee(Employee employee){
        if (assignedEmployees.stream().anyMatch(o -> o.getId() == employee.getId()))
            return false;

        return this.assignedEmployees.add(employee);
    }

    public boolean removeEmployeeAssignment(Employee employee){
        if (assignedEmployees.stream().anyMatch(o ->  o.getId() == employee.getId()))
            return this.assignedEmployees.remove(employee);

        return false;
    }
}
