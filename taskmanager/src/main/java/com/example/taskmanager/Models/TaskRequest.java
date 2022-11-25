package com.example.taskmanager.Models;

import com.example.taskmanager.Entities.StudentEntity;
import com.example.taskmanager.Utils.Status;
import lombok.*;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
public class TaskRequest {
    private Long studentId;
    private String title;
    private String description;
    private Status status;
    private Timestamp completedAt;

    public TaskRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
