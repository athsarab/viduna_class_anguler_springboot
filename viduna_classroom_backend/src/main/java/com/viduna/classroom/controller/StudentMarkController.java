package com.viduna.classroom.controller;

import com.viduna.classroom.dto.CreateMarkRequest;
import com.viduna.classroom.entity.StudentMark;
import com.viduna.classroom.service.StudentMarkService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marks")
@CrossOrigin(origins = "*", maxAge = 3600)
public class StudentMarkController {

    @Autowired
    private StudentMarkService studentMarkService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<StudentMark>> getAllMarks() {
        List<StudentMark> marks = studentMarkService.getAllMarks();
        return ResponseEntity.ok(marks);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentMark>> getMarksByStudentId(@PathVariable Long studentId) {
        List<StudentMark> marks = studentMarkService.getMarksByStudentId(studentId);
        return ResponseEntity.ok(marks);
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<List<StudentMark>> getMarksByClassId(@PathVariable Long classId) {
        List<StudentMark> marks = studentMarkService.getMarksByClassId(classId);
        return ResponseEntity.ok(marks);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentMark> createMark(@Valid @RequestBody CreateMarkRequest request) {
        try {
            StudentMark mark = studentMarkService.createMark(request);
            return ResponseEntity.ok(mark);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentMark> updateMark(@PathVariable Long id, @Valid @RequestBody CreateMarkRequest request) {
        try {
            StudentMark mark = studentMarkService.updateMark(id, request);
            return ResponseEntity.ok(mark);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMark(@PathVariable Long id) {
        try {
            studentMarkService.deleteMark(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
