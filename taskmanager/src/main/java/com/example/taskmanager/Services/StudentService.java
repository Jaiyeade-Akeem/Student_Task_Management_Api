package com.example.taskmanager.Services;

import com.example.taskmanager.Entities.StudentEntity;
import com.example.taskmanager.Models.ApiResponse;
import com.example.taskmanager.Models.StudentRequest;

public interface StudentService {
    ApiResponse signUp(StudentRequest studentRequest);
    ApiResponse login(StudentRequest studentRequest);
}
