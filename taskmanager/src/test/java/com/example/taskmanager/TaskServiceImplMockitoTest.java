package com.example.taskmanager;

import com.example.taskmanager.Entities.TaskEntity;
import com.example.taskmanager.Models.TaskRequest;
import com.example.taskmanager.Repositories.TaskEntityRepository;
import com.example.taskmanager.ServicesImpl.TaskServiceImpl;
import com.example.taskmanager.Utils.Status;
import org.assertj.core.internal.Classes;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {TaskServiceImplMockitoTest.class})
public class TaskServiceImplMockitoTest {

    @Mock
    TaskEntityRepository taskEntityRepository;

    @InjectMocks
    TaskServiceImpl taskService;

    public List<TaskEntity> tasks = List.of(
            new TaskEntity(1L, "SpringBoot Assignment", "Create a task management application",
                    Status.PENDING, new Timestamp(2022-11-25), new Timestamp(2022-11-25), new Timestamp(2022-11-27)),
            new TaskEntity(2L, "Stream Api Assignment", "Create an application using stream api",
                    Status.COMPLETED, new Timestamp(2022-11-23), new Timestamp(2022-11-23), new Timestamp(2022-11-25)),
            new TaskEntity(3L, "Mysql Assignment", "Create an application using mysql",
                    Status.IN_PROGRESS, new Timestamp(2022-11-22), new Timestamp(2022-11-22), new Timestamp(2022-11-25)),
            new TaskEntity(4L, "Integration testing Assignment", "Create an application using int. testing",
                    Status.COMPLETED, new Timestamp(2022-11-21), new Timestamp(2022-11-21), new Timestamp(2022-11-23))
    );

    @Test
    @Order(1)

    public void test_createTask(){
        TaskEntity taskEntity = new TaskEntity(4L, "Integration testing Assignment", "Create an application using int. testing",
                Status.COMPLETED, new Timestamp(2022-11-21), new Timestamp(2022-11-21), new Timestamp(2022-11-23))
        TaskRequest taskRequest = new TaskRequest(1L, "Api application","Building a rest api", Status.PENDING, new Timestamp(2022-11-28));
        when(taskEntityRepository.save(taskRequest)).thenReturn(taskEntity);
        assertEquals(taskRequest, taskService.createTask(taskRequest));

    }
}
