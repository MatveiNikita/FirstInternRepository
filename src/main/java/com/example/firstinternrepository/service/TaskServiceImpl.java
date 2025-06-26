package com.example.firstinternrepository.service;

import com.example.firstinternrepository.dto.CreateTaskDTO;
import com.example.firstinternrepository.dto.UpdateTaskDTO;
import com.example.firstinternrepository.dto.ViewTasksDTO;
import com.example.firstinternrepository.mapper.TaskMapper;
import com.example.firstinternrepository.model.Task;
import com.example.firstinternrepository.model.User;
import com.example.firstinternrepository.repository.TaskRepository;
import com.example.firstinternrepository.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{

    private final TaskMapper taskMapper;
    private final UserService userService;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Override
    public Task createNewTask(CreateTaskDTO taskDTO) {
        Task newTask = taskMapper.toTaskEntity(taskDTO);
        User user = userService.getUserById(taskDTO.handlerId());
        newTask.setHandler(user);

        return taskRepository.save(newTask);
    }

    @Override
    public List<ViewTasksDTO> findTasksByHandlerId(String id){
        if (!userRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user with id: " + id + " doesn't exist");
        }

        List<Task> tasks = taskRepository.findTasksByHandler_Id(id);
        List<ViewTasksDTO> viewTasksList = tasks.stream()
                .map(task -> taskMapper.toViewTaskDto(task))
                .collect(Collectors.toList());
        return viewTasksList;
    }

    @Override
    public Task getTaskById(String id) {
        return taskRepository.getTasksById(id).orElseThrow(
                (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with id: " + id + " doesn't exist"))
        );
    }

    @Override
    public Task UpdateTitleTask(String id, String title) {
        Task task = getTaskById(id);
        task.setTitle(title);
        taskRepository.save(task);
        return task;
    }

    @Override
    public Task updateDescriptionTask(String id, String description) {
        Task task = taskRepository.getTasksById(id).orElseThrow(
                (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with id: " + id + " doesn't exist"))
        );
         task.setDescription(description);
         taskRepository.save(task);
        return task;
    }

    @Override
    public Task updateTask(String id, UpdateTaskDTO updateTask) {
        Task task = taskRepository.getTasksById(id).orElseThrow(
                (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with id: " + id + " doesn't exist"))
        );
        task.setTitle(updateTask.title());
        task.setDescription(updateTask.Description());
        taskRepository.save(task);
        return task;
    }

    @Override
    public String deleteTask(String taskId) {
        String message;
        if (!taskRepository.existsById(taskId)){
            message = "task with id: " + taskId + " doesn't exist";
            return message;
        }
        taskRepository.deleteById(taskId);
        message = "Successfully deleted";
        return message;
    }
}
