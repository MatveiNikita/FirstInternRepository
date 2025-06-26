package com.example.firstinternrepository.mapper;

import com.example.firstinternrepository.dto.CreateTaskDTO;
import com.example.firstinternrepository.dto.ViewTasksDTO;
import com.example.firstinternrepository.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toTaskEntity(CreateTaskDTO createTaskDTO);
    ViewTasksDTO toViewTaskDto(Task task);
}
