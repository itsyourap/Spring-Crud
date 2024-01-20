package com.crud.serverside.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "meetings")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "meeting_topic")
    private String meetingTopic;

    @Column(name = "meeting_link")
    private String meetingLink;

    @ManyToMany
    @JoinTable(
            name = "employee_meeting",
            joinColumns = {@JoinColumn(name = "meeting_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")}
    )
    private List<Employee> allowedEmployees;

    public Meeting(){

    }

    public Meeting(long id, String meetingTopic, String meetingLink, List<Employee> allowedEmployees) {
        this.id = id;
        this.meetingTopic = meetingTopic;
        this.meetingLink = meetingLink;
        this.allowedEmployees = allowedEmployees;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMeetingTopic() {
        return meetingTopic;
    }

    public void setMeetingTopic(String meetingTopic) {
        this.meetingTopic = meetingTopic;
    }

    public String getMeetingLink() {
        return meetingLink;
    }

    public void setMeetingLink(String meetingLink) {
        this.meetingLink = meetingLink;
    }

    public List<Employee> getAllowedEmployees() {
        return allowedEmployees;
    }

    public void setAllowedEmployees(List<Employee> allowedEmployees) {
        this.allowedEmployees = allowedEmployees;
    }

    public boolean allowEmployee(Employee employee){
        if (allowedEmployees.stream().anyMatch(o -> o.getId() == employee.getId()))
            return false;

        return this.allowedEmployees.add(employee);
    }

    public boolean denyEmployee(Employee employee){
        if (allowedEmployees.stream().anyMatch(o ->  o.getId() == employee.getId()))
            return this.allowedEmployees.remove(employee);

        return false;
    }
}
