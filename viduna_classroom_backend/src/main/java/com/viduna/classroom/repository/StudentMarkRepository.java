package com.viduna.classroom.repository;

import com.viduna.classroom.entity.StudentMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMarkRepository extends JpaRepository<StudentMark, Long> {
    List<StudentMark> findByStudentId(Long studentId);
    List<StudentMark> findByClassId(Long classId);
    List<StudentMark> findByStudentIdAndClassId(Long studentId, Long classId);
}
