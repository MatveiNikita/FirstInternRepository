package com.example.firstinternrepository.controller;

import com.example.firstinternrepository.dto.CreateTaskDTO;
import com.example.firstinternrepository.dto.UpdateTaskDTO;
import com.example.firstinternrepository.dto.ViewTasksDTO;
import com.example.firstinternrepository.model.Task;
import com.example.firstinternrepository.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TaskService taskService;

    @PostMapping("/new-task")
    public ResponseEntity<Task> createNewTask(@RequestBody CreateTaskDTO task){
        Task newTask = taskService.createNewTask(task);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<ViewTasksDTO>> getAllTasksByUserId(@RequestParam String userId){
        List<ViewTasksDTO> tasks = taskService.findTasksByHandlerId(userId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PatchMapping("/update-title/{id}")
    public ResponseEntity<Task> updateTitleTask(@PathVariable String id, @RequestBody Map<String, String> title){
        Task task = taskService.UpdateTitleTask(id, title.get("title"));
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PatchMapping("/update-description/{id}")
    public ResponseEntity<Task> updateDescriptionTask(@PathVariable String id, @RequestBody Map<String, String> description){
        Task task = taskService.updateDescriptionTask(id, description.get("description"));
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PatchMapping("/update-task/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody UpdateTaskDTO updateTaskDTO){
        Task task = taskService.updateTask(id, updateTaskDTO);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable String id){
        String message = taskService.deleteTask(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
