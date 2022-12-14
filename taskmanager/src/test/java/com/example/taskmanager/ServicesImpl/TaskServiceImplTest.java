package com.example.taskmanager.ServicesImpl;

import com.example.taskmanager.Entities.StudentEntity;
import com.example.taskmanager.Entities.TaskEntity;
import com.example.taskmanager.Models.TaskRequest;
import com.example.taskmanager.Repositories.StudentEntityRepository;
import com.example.taskmanager.Repositories.TaskEntityRepository;
import com.example.taskmanager.TaskServiceImplMockitoTest;
import com.example.taskmanager.Utils.Status;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {TaskServiceImplTest.class})
class TaskServiceImplTest {

    @Mock
    TaskEntityRepository taskEntityRepository;

    @Mock
    StudentEntityRepository studentEntityRepository;

    @InjectMocks
    TaskServiceImpl taskService;

    StudentEntity student = new StudentEntity(1L,"Ademiluwa", "Abraham", "Adlam", "ade@gmail.com", "1234");

    public List<TaskEntity> tasks = List.of(
            new TaskEntity(1L, "SpringBoot Assignment", "Create a task management application", student,
                    Status.PENDING, new Timestamp(2022-11-25), new Timestamp(2022-11-25), new Timestamp(2022-11-27)),
            new TaskEntity(2L, "Stream Api Assignment", "Create an application using stream api", student,
                    Status.COMPLETED, new Timestamp(2022-11-23), new Timestamp(2022-11-23), new Timestamp(2022-11-25)),
            new TaskEntity(3L, "Mysql Assignment", "Create an application using mysql", student,
                    Status.IN_PROGRESS, new Timestamp(2022-11-22), new Timestamp(2022-11-22), new Timestamp(2022-11-25)),
            new TaskEntity(4L, "Integration testing Assignment", "Create an application using int. testing", student,
                    Status.COMPLETED, new Timestamp(2022-11-21), new Timestamp(2022-11-21), new Timestamp(2022-11-23))
    );

    @Test
    @Order(1)
    void createTask() {

        TaskRequest taskRequest = new TaskRequest(student.getId(), "Integration testing Assignment","Create an application using int. testing",
                Status.COMPLETED, new Timestamp(2022-11-23));
        StudentEntity studentEntity = studentEntityRepository.getStudentEntityById(student.getId());
        TaskEntity taskEntity = new TaskEntity(studentEntity, "Integration testing Assignment", "Create an application using int. testing",
                Status.COMPLETED, new Timestamp(2022-11-23));
        when(taskEntityRepository.save(taskEntity)).thenReturn(taskEntity);
        assertEquals(taskEntity, taskService.createTask(taskRequest));
    }

    @Test
    @Order(2)
    void viewAllTasks() {
        Integer count = tasks.size();
        when(taskEntityRepository.findAll()).thenReturn(tasks);
        assertEquals(count, taskService.viewAllTasks().size());
    }

    @Test
    @Order(3)
    void viewATask() {
        Long id = 3L;
        TaskEntity existingTask = taskEntityRepository.getTaskEntityById(id);
       when(taskEntityRepository.getTaskEntityById(id)).thenReturn(new TaskEntity());
       assertEquals(existingTask, taskService.viewATask(id));
    }

    @Test
    void viewAllPendingTask() {
    }

    @Test
    void viewAllDoneTask() {
    }

    @Test
    void viewAllTaskInProgress() {
    }

    @Test
    void changeTaskToPending() {
    }

    @Test
    void changeTaskToDone() {
    }

    @Test
    void editTask() {
    }

    @Test
    void deleteTask() {
    }
}