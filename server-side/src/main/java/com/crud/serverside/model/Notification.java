package com.crud.serverside.model;

import jakarta.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "notification_title")
    private String notificationTitle;

    @Column(name = "notification_content")
    private String notificationContent;

    public Notification() {

    }

    public Notification(long id, String notificationTitle, String notificationContent) {
        this.id = id;
        this.notificationTitle = notificationTitle;
        this.notificationContent = notificationContent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }
}
