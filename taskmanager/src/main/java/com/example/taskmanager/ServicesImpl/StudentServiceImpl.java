package com.example.taskmanager.ServicesImpl;

import com.example.taskmanager.Entities.StudentEntity;
import com.example.taskmanager.Models.ApiResponse;
import com.example.taskmanager.Models.StudentRequest;
import com.example.taskmanager.Repositories.StudentEntityRepository;
import com.example.taskmanager.Services.StudentService;
import com.example.taskmanager.Utils.ResponseManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    public final StudentEntityRepository studentRepository;
    public final ResponseManager response;
    @Override
    public ApiResponse signUp(StudentRequest studentRequest) {
        if (studentRequest.getFirstname().length() == 0)
            return response.error("First name required");
        else if (studentRequest.getLastname().length() == 0)
            return response.error("Last name required");
        else if (studentRequest.getUsername().length() == 0)
            return response.error("User name required");
        else if (studentRequest.getEmail().length() == 0)
            return response.error("Email required");
        else if (studentRequest.getPassword().length() == 0)
            return response.error("Password required");

        Boolean isStudentExit = studentRepository.existsByEmail(studentRequest.getEmail());

        if (isStudentExit)
            return response.error("Student already exit!");


        StudentEntity newStudent = new StudentEntity();
        newStudent.setFirstname(studentRequest.getFirstname());
        newStudent.setLastname(studentRequest.getLastname());
        newStudent.setUsername(studentRequest.getUsername());
        newStudent.setEmail(studentRequest.getEmail());
        newStudent.setPassword(studentRequest.getPassword());

        studentRepository.save(newStudent);

        return response.success("Hello " + newStudent.getUsername() +
                " you have successfully registered");



    }

    @Override
    public ApiResponse login(StudentRequest studentRequest) {
        if (studentRequest.getEmail().length() == 0 && studentRequest.getPassword().length() == 0)
            return response.error("Email and Password required");
        else if (studentRequest.getEmail().length() == 0)
            return response.error("Email required");
        else if (studentRequest.getPassword().length() == 0)
            return response.error("Password required");

        StudentEntity exististingUser = studentRepository.getStudentEntitiesByEmailAndPassword(
                studentRequest.getEmail(),
                studentRequest.getPassword());
        if (exististingUser != null)
            return response.success("Hello " + exististingUser.getUsername() + " you logged in successfully");
        return response.error("Login failed. Try again with correct email and password or create a new account");
    }
}















