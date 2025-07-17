package com.viduna.classroom.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "student_marks")
public class StudentMark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long studentId;

    private String studentName;
    private String studentEmail;

    @NotNull
    private Long classId;

    private String className;
    private String subject;

    @NotNull
    @Min(0)
    private Integer marks;

    @NotNull
    @Min(1)
    private Integer maxMarks;

    @NotNull
    private LocalDate examDate;

    private String remarks;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public StudentMark() {}

    public StudentMark(Long studentId, Long classId, Integer marks, Integer maxMarks, 
                      LocalDate examDate, String remarks) {
        this.studentId = studentId;
        this.classId = classId;
        this.marks = marks;
        this.maxMarks = maxMarks;
        this.examDate = examDate;
        this.remarks = remarks;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    
    public String getStudentEmail() { return studentEmail; }
    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }
    
    public Long getClassId() { return classId; }
    public void setClassId(Long classId) { this.classId = classId; }
    
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
    
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    
    public Integer getMarks() { return marks; }
    public void setMarks(Integer marks) { this.marks = marks; }
    
    public Integer getMaxMarks() { return maxMarks; }
    public void setMaxMarks(Integer maxMarks) { this.maxMarks = maxMarks; }
    
    public LocalDate getExamDate() { return examDate; }
    public void setExamDate(LocalDate examDate) { this.examDate = examDate; }
    
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
