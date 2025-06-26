package com.example.firstinternrepository.service;

import com.example.firstinternrepository.dto.CreateTaskDTO;
import com.example.firstinternrepository.dto.UpdateTaskDTO;
import com.example.firstinternrepository.dto.ViewTasksDTO;
import com.example.firstinternrepository.model.Task;

import java.util.List;

public interface TaskService {
    List<ViewTasksDTO> findTasksByHandlerId(String Id);
    Task createNewTask(CreateTaskDTO newTask);
    Task getTaskById(String id);
    Task UpdateTitleTask(String id, String title);
    Task updateDescriptionTask(String id, String description);
    Task updateTask(String id, UpdateTaskDTO updateTask);
    String deleteTask(String taskId);
}
