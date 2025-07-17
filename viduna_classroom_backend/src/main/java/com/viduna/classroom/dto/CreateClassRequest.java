package com.viduna.classroom.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateClassRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String teacherName;

    @NotBlank
    private String teacherEmail;

    @NotBlank
    private String subject;

    @NotBlank
    private String schedule;

    public CreateClassRequest() {}

    public CreateClassRequest(String title, String description, String teacherName, 
                             String teacherEmail, String subject, String schedule) {
        this.title = title;
        this.description = description;
        this.teacherName = teacherName;
        this.teacherEmail = teacherEmail;
        this.subject = subject;
        this.schedule = schedule;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
