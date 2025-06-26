package com.example.firstinternrepository.service;

import com.example.firstinternrepository.dto.CreateUserDTO;
import com.example.firstinternrepository.dto.UpdateUserDTO;
import com.example.firstinternrepository.dto.ViewUserDTO;
import com.example.firstinternrepository.model.User;

public interface UserService {

    User createUser(CreateUserDTO createUserDTO);
    ViewUserDTO getViewUserById(String id);
    User getUserById(String userId);
    User updateUsername(String id, String string);
    User updateEmail(String id, String email);
    User updateUser(String id, UpdateUserDTO updateUserDTO);
    String deleteUserById(String id);
}
