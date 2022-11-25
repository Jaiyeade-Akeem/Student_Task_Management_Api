package com.example.taskmanager.Entities;

import com.example.taskmanager.Utils.Status;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "student")
    private StudentEntity student;

    @Enumerated(EnumType.STRING)
    @Column(name ="status")
    private Status status;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updateAt;

    @Column( nullable = false)
    private Timestamp completedAt;

    public TaskEntity() {

    }

    public TaskEntity(StudentEntity student, String title, String description, Status status, Timestamp completedAt) {
        this.student = student;
        this.title = title;
        this.description = description;
        this.status = status;
        //this.createdAt = createdAt;
        //this.updateAt = updateAt;
        this.completedAt = completedAt;
    }
}
