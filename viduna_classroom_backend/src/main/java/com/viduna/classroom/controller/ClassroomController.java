package com.viduna.classroom.controller;

import com.viduna.classroom.dto.CreateClassRequest;
import com.viduna.classroom.entity.Classroom;
import com.viduna.classroom.service.ClassroomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @GetMapping
    public ResponseEntity<List<Classroom>> getAllClasses() {
        List<Classroom> classes = classroomService.getAllClassrooms();
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classroom> getClassById(@PathVariable Long id) {
        try {
            Classroom classroom = classroomService.getClassroomById(id);
            return ResponseEntity.ok(classroom);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Classroom> createClass(@Valid @RequestBody CreateClassRequest request) {
        try {
            Classroom classroom = classroomService.createClassroom(request);
            return ResponseEntity.ok(classroom);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Classroom> updateClass(@PathVariable Long id, @Valid @RequestBody CreateClassRequest request) {
        try {
            Classroom classroom = classroomService.updateClassroom(id, request);
            return ResponseEntity.ok(classroom);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        try {
            classroomService.deleteClassroom(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/upload-image")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> uploadClassImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            String fileName = classroomService.uploadClassImage(id, file);
            return ResponseEntity.ok("Class image uploaded successfully: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Failed to upload image: " + e.getMessage());
        }
    }

    @PostMapping("/{id}/upload-teacher-image")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> uploadTeacherImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            String fileName = classroomService.uploadTeacherImage(id, file);
            return ResponseEntity.ok("Teacher image uploaded successfully: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Failed to upload image: " + e.getMessage());
        }
    }
}
