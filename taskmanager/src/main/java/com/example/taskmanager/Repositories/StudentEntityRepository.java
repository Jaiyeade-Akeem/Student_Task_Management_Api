package com.example.taskmanager.Repositories;

import com.example.taskmanager.Entities.StudentEntity;
import com.example.taskmanager.Models.StudentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentEntityRepository extends JpaRepository<StudentEntity, Long> {

    Boolean existsByEmail(String email);
    StudentEntity getStudentEntitiesByEmailAndPassword(String email, String password);
    StudentEntity getStudentEntitiesById(Long id);

    StudentEntity getStudentEntityById(Long id);
}