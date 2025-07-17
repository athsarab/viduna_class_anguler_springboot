package com.viduna.classroom.repository;

import com.viduna.classroom.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    List<Classroom> findBySubjectContainingIgnoreCase(String subject);
    List<Classroom> findByTeacherNameContainingIgnoreCase(String teacherName);
}
