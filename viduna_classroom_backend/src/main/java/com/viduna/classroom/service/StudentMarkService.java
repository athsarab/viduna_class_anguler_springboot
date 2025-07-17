package com.viduna.classroom.service;

import com.viduna.classroom.dto.CreateMarkRequest;
import com.viduna.classroom.entity.Classroom;
import com.viduna.classroom.entity.StudentMark;
import com.viduna.classroom.entity.User;
import com.viduna.classroom.repository.StudentMarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class StudentMarkService {

    @Autowired
    private StudentMarkRepository studentMarkRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ClassroomService classroomService;

    public List<StudentMark> getAllMarks() {
        return studentMarkRepository.findAll();
    }

    public List<StudentMark> getMarksByStudentId(Long studentId) {
        return studentMarkRepository.findByStudentId(studentId);
    }

    public List<StudentMark> getMarksByClassId(Long classId) {
        return studentMarkRepository.findByClassId(classId);
    }

    public StudentMark createMark(CreateMarkRequest request) {
        User student = userService.findById(request.getStudentId());
        Classroom classroom = classroomService.getClassroomById(request.getClassId());

        StudentMark mark = new StudentMark();
        mark.setStudentId(request.getStudentId());
        mark.setStudentName(student.getFirstName() + " " + student.getLastName());
        mark.setStudentEmail(student.getEmail());
        mark.setClassId(request.getClassId());
        mark.setClassName(classroom.getTitle());
        mark.setSubject(classroom.getSubject());
        mark.setMarks(request.getMarks());
        mark.setMaxMarks(request.getMaxMarks());
        mark.setExamDate(LocalDate.parse(request.getExamDate(), DateTimeFormatter.ISO_LOCAL_DATE));
        mark.setRemarks(request.getRemarks());

        return studentMarkRepository.save(mark);
    }

    public StudentMark updateMark(Long id, CreateMarkRequest request) {
        StudentMark mark = studentMarkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mark not found with id: " + id));

        User student = userService.findById(request.getStudentId());
        Classroom classroom = classroomService.getClassroomById(request.getClassId());

        mark.setStudentId(request.getStudentId());
        mark.setStudentName(student.getFirstName() + " " + student.getLastName());
        mark.setStudentEmail(student.getEmail());
        mark.setClassId(request.getClassId());
        mark.setClassName(classroom.getTitle());
        mark.setSubject(classroom.getSubject());
        mark.setMarks(request.getMarks());
        mark.setMaxMarks(request.getMaxMarks());
        mark.setExamDate(LocalDate.parse(request.getExamDate(), DateTimeFormatter.ISO_LOCAL_DATE));
        mark.setRemarks(request.getRemarks());

        return studentMarkRepository.save(mark);
    }

    public void deleteMark(Long id) {
        StudentMark mark = studentMarkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mark not found with id: " + id));
        studentMarkRepository.delete(mark);
    }
}
