package com.example.taskmanager.Controllers;

import com.example.taskmanager.Entities.TaskEntity;
import com.example.taskmanager.Models.ApiResponse;
import com.example.taskmanager.Models.StudentRequest;
import com.example.taskmanager.Models.TaskRequest;
import com.example.taskmanager.ServicesImpl.StudentServiceImpl;
import com.example.taskmanager.ServicesImpl.TaskServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("taskmanager/Student")
public class StudentController {
    private StudentServiceImpl studentService;
    private TaskServiceImpl taskService;

    @PostMapping("/sign-up")
    public ApiResponse<String> createAccount(@RequestBody StudentRequest student){
        return studentService.signUp(student);
    }

    @PostMapping("/sign-in")
    public ApiResponse<String> login(@RequestBody StudentRequest student){
        return studentService.login(student);
    }
    @PostMapping("/create-task")
    public  ApiResponse<String> createTask (@RequestBody TaskRequest taskRequest){
        return taskService.createTask(taskRequest);
    }
    @GetMapping("/view-all-task")
    public String view_all_task(){

        return taskService.viewAllTasks();
    }
    @GetMapping(path = "view-task/{id}")
    public String view_a_task(@PathVariable("id") Long id){
        return taskService.viewATask(id);
    }

    @GetMapping("/view-pending-task")
    public List<TaskEntity> view_pending_task(){

        return taskService.viewAllPendingTask();
    }

    @GetMapping("/view-completed-task")
    public List<TaskEntity> view_completed_task(){

        return taskService.viewAllDoneTask();
    }

    @GetMapping("/view-inprogress-task")
    public List<TaskEntity> view_inprogress_task(){

        return taskService.viewAllTaskInProgress();
    }

    @PutMapping(path = "/{id}/move-task-to-pending")
    public ApiResponse<String> move_task_to_pending(@PathVariable("id") Long id){
        return taskService.changeTaskToPending(id);
    }

    @PutMapping(path = "/{id}/move-task-to-done")
    public ApiResponse<String> move_task_to_done(@PathVariable("id") Long id){
        return taskService.changeTaskToDone(id);
    }

    @PutMapping(path = "/{taskId}/update-task")
    public ApiResponse<String> edit_task (@PathVariable("taskId") Long taskId, @RequestBody TaskRequest updatedTask ){
        return taskService.editTask(taskId, updatedTask);
    }

    @DeleteMapping(path = "/{id}")
    public ApiResponse<String> delete_task (@PathVariable("id") Long id){
        return taskService.deleteTask(id);
    }
}
