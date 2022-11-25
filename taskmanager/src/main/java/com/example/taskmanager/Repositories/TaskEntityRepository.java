package com.example.taskmanager.Repositories;

import com.example.taskmanager.Entities.StudentEntity;
import com.example.taskmanager.Entities.TaskEntity;
import com.example.taskmanager.Models.ApiResponse;
import com.example.taskmanager.Utils.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskEntityRepository extends JpaRepository<TaskEntity, Long> {
    TaskEntity getById(Long id);
    List<TaskEntity> findTaskEntitiesByStatus(Status status);
    TaskEntity getTaskEntityById(Long id);
}