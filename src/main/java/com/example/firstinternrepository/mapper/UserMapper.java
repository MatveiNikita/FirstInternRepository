package com.example.firstinternrepository.mapper;

import com.example.firstinternrepository.dto.CreateUserDTO;
import com.example.firstinternrepository.dto.UpdateUserDTO;
import com.example.firstinternrepository.dto.ViewTasksDTO;
import com.example.firstinternrepository.dto.ViewUserDTO;
import com.example.firstinternrepository.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUserEntity(CreateUserDTO userDTO);
    ViewUserDTO toViewUserDTO(User user, List<ViewTasksDTO> tasks);
    User toUserEntity(UpdateUserDTO updateUserDTO);
}

