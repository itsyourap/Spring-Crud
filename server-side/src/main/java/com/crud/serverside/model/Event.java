package com.crud.serverside.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_description")
    private String eventDescription;

    public Event(){

    }

    public Event(long id, String eventName, String eventDescription, List<Employee> allowedEmployees) {
        this.id = id;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventTopic) {
        this.eventName = eventTopic;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventLink) {
        this.eventDescription = eventLink;
    }
}