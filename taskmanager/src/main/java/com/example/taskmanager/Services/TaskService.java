package com.example.taskmanager.Services;

import com.example.taskmanager.Entities.TaskEntity;
import com.example.taskmanager.Models.ApiResponse;
import com.example.taskmanager.Models.TaskRequest;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    TaskEntity createTask(TaskRequest taskRequest);
    List<TaskEntity> viewAllTasks();
    String viewATask(Long id);
    List<TaskEntity> viewAllPendingTask();
    List<TaskEntity> viewAllDoneTask();
    List<TaskEntity> viewAllTaskInProgress();
    ApiResponse<String> changeTaskToPending(Long id);
    ApiResponse<String> changeTaskToDone(Long id);
    ApiResponse<String> editTask(Long id, TaskRequest updatedTask);
    ApiResponse<String> deleteTask(Long id);

}
