package com.example.taskmanager.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.taskmanager.Entities.StudentEntity} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest implements Serializable {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;

    public StudentRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

}