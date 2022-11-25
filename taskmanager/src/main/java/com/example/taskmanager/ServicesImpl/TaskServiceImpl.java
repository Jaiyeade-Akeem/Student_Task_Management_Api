package com.example.taskmanager.ServicesImpl;

import com.example.taskmanager.Entities.StudentEntity;
import com.example.taskmanager.Entities.TaskEntity;
import com.example.taskmanager.Models.ApiResponse;
import com.example.taskmanager.Models.TaskRequest;
import com.example.taskmanager.Repositories.StudentEntityRepository;
import com.example.taskmanager.Repositories.TaskEntityRepository;
import com.example.taskmanager.Services.TaskService;
import com.example.taskmanager.Utils.ResponseManager;
import com.example.taskmanager.Utils.Status;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class TaskServiceImpl implements TaskService {

    private final StudentEntityRepository studentEntityRepository;
    private final TaskEntityRepository taskEntityRepository;
    private final ResponseManager response;
    @Override
    public TaskEntity createTask(TaskRequest taskRequest) {
        StudentEntity studentWhoCreatedTask = studentEntityRepository.getStudentEntitiesById(taskRequest.getStudentId());
        TaskEntity newTask = new TaskEntity();
        newTask.setTitle(taskRequest.getTitle());
        newTask.setDescription(taskRequest.getDescription());
        newTask.setStudent(studentWhoCreatedTask);
        newTask.setCompletedAt(taskRequest.getCompletedAt());
        newTask.setStatus(taskRequest.getStatus());
        taskEntityRepository.save(newTask);

        return newTask;
        //return response.success("Task created successfully");

    }

    @Override
    public List<TaskEntity> viewAllTasks() {
        List<TaskEntity> tasks;
        tasks = taskEntityRepository.findAll();
//        String newTasks = "";
//        for (TaskEntity task : tasks){
//
//            newTasks += "[Task-Id]: " + task.getId() + "\n[Task-Title]: " + task.getTitle() + "\n[Task-Description]:"
//                    + task.getDescription() + "\n[Task-Created by]: "
//                    + task.getStudent().getUsername() + "\n[Task-CreatedAt]: " + task.getCreatedAt() + "\n[Task-Status]: "
//                    + task.getStatus() + "\n\n" ;}
        return tasks;
    }

    @Override
    public String viewATask(Long id) {
        TaskEntity task = taskEntityRepository.getById(id);
        return "[Task-Id]: " + task.getId() + "\n[Task-Title]: " + task.getTitle() + "\n[Task-Description]:"
                + task.getDescription() + "\n[Task-Created by]: "
                + task.getStudent().getUsername() + "\n[Task-CreatedAt]: " + task.getCreatedAt() + "\n[Task-Status]: "
                + task.getStatus() ;
    }

    @Override
    public List<TaskEntity> viewAllPendingTask() {
        List<TaskEntity> pendingTasks;
        pendingTasks = taskEntityRepository.findTaskEntitiesByStatus(Status.PENDING);
        return pendingTasks;
    }

    @Override
    public List<TaskEntity> viewAllDoneTask() {
        List<TaskEntity> doneTasks;
        doneTasks = taskEntityRepository.findTaskEntitiesByStatus(Status.COMPLETED);
        return doneTasks;
    }

    @Override
    public List<TaskEntity> viewAllTaskInProgress() {
        List<TaskEntity> inProgressTasks;
        inProgressTasks = taskEntityRepository.findTaskEntitiesByStatus(Status.IN_PROGRESS);
        return inProgressTasks;
    }

    @Override
    @Transactional
    public ApiResponse<String> changeTaskToPending(Long id) {
        TaskEntity existingTask = taskEntityRepository.getTaskEntityById(id);
        existingTask.setStatus(Status.PENDING);
        taskEntityRepository.save(existingTask);
        return response.success("Task " + existingTask.getId() + " successfully updated pending.");
    }

    @Override
    public ApiResponse<String> changeTaskToDone(Long id) {
        TaskEntity existingTask = taskEntityRepository.getTaskEntityById(id);
        existingTask.setStatus(Status.COMPLETED);
        taskEntityRepository.save(existingTask);
        return response.success("Task " + existingTask.getId() + " successfully updated to completed.");
    }

    @Override
    public ApiResponse<String> editTask(Long taskId, TaskRequest updatedTask) {
        TaskEntity existingTask = taskEntityRepository.getTaskEntityById(taskId);
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        taskEntityRepository.save(existingTask);
        return  response.success("Task " + existingTask.getId() + " successfully updated.");
    }

    @Override
    public ApiResponse<String> deleteTask(Long id) {
        TaskEntity taskToBeDeleted = taskEntityRepository.getById(id);
        taskEntityRepository.deleteById(id);
        return response.success("Task " + taskToBeDeleted.getId() + " successfully deleted" );
    }
}
