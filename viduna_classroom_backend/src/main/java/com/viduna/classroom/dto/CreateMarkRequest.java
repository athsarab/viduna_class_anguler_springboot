package com.viduna.classroom.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CreateMarkRequest {
    @NotNull
    private Long studentId;

    @NotNull
    private Long classId;

    @NotNull
    @Min(0)
    private Integer marks;

    @NotNull
    @Min(1)
    private Integer maxMarks;

    @NotNull
    private String examDate;

    private String remarks;

    public CreateMarkRequest() {}

    public CreateMarkRequest(Long studentId, Long classId, Integer marks, 
                           Integer maxMarks, String examDate, String remarks) {
        this.studentId = studentId;
        this.classId = classId;
        this.marks = marks;
        this.maxMarks = maxMarks;
        this.examDate = examDate;
        this.remarks = remarks;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public Integer getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(Integer maxMarks) {
        this.maxMarks = maxMarks;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
