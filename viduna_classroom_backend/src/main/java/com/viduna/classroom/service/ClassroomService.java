package com.viduna.classroom.service;

import com.viduna.classroom.dto.CreateClassRequest;
import com.viduna.classroom.entity.Classroom;
import com.viduna.classroom.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Value("${app.file.upload-dir}")
    private String uploadDir;

    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    public Classroom getClassroomById(Long id) {
        return classroomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classroom not found with id: " + id));
    }

    public Classroom createClassroom(CreateClassRequest request) {
        Classroom classroom = new Classroom();
        classroom.setTitle(request.getTitle());
        classroom.setDescription(request.getDescription());
        classroom.setTeacherName(request.getTeacherName());
        classroom.setTeacherEmail(request.getTeacherEmail());
        classroom.setSubject(request.getSubject());
        classroom.setSchedule(request.getSchedule());

        return classroomRepository.save(classroom);
    }

    public Classroom updateClassroom(Long id, CreateClassRequest request) {
        Classroom classroom = getClassroomById(id);
        classroom.setTitle(request.getTitle());
        classroom.setDescription(request.getDescription());
        classroom.setTeacherName(request.getTeacherName());
        classroom.setTeacherEmail(request.getTeacherEmail());
        classroom.setSubject(request.getSubject());
        classroom.setSchedule(request.getSchedule());

        return classroomRepository.save(classroom);
    }

    public void deleteClassroom(Long id) {
        Classroom classroom = getClassroomById(id);
        classroomRepository.delete(classroom);
    }

    public String uploadClassImage(Long classId, MultipartFile file) throws IOException {
        Classroom classroom = getClassroomById(classId);
        String fileName = saveFile(file, "class_images");
        classroom.setClassImage("/api/files/" + fileName);
        classroomRepository.save(classroom);
        return fileName;
    }

    public String uploadTeacherImage(Long classId, MultipartFile file) throws IOException {
        Classroom classroom = getClassroomById(classId);
        String fileName = saveFile(file, "teacher_images");
        classroom.setTeacherImage("/api/files/" + fileName);
        classroomRepository.save(classroom);
        return fileName;
    }

    private String saveFile(MultipartFile file, String folder) throws IOException {
        // Create upload directory if it doesn't exist
        Path uploadPath = Paths.get(uploadDir, folder);
        Files.createDirectories(uploadPath);

        // Generate unique filename
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            originalFilename = "file.jpg"; // default filename
        }
        String extension = originalFilename.contains(".") 
            ? originalFilename.substring(originalFilename.lastIndexOf("."))
            : ".jpg";
        String fileName = folder + "_" + UUID.randomUUID() + extension;

        // Save file
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        return fileName;
    }
}
