package com.example.firstinternrepository.service;

import com.example.firstinternrepository.dto.CreateUserDTO;
import com.example.firstinternrepository.dto.UpdateUserDTO;
import com.example.firstinternrepository.dto.ViewTasksDTO;
import com.example.firstinternrepository.dto.ViewUserDTO;
import com.example.firstinternrepository.mapper.TaskMapper;
import com.example.firstinternrepository.mapper.UserMapper;
import com.example.firstinternrepository.model.User;
import com.example.firstinternrepository.repository.TaskRepository;
import com.example.firstinternrepository.repository.UserRepository;
import com.mongodb.DuplicateKeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;


    @Override
    public User createUser(CreateUserDTO createUserDTO) {
        User newuser = userMapper.toUserEntity(createUserDTO);
        try{
            return userRepository.save(newuser);
        }catch (DuplicateKeyException exception){
            throw new RuntimeException("User with this name: " + createUserDTO.username() + " is exit");
        }
    }

    @Override
    public ViewUserDTO getViewUserById(String id) {
        User user = userRepository.findUserById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this id not founded: " + id)
        );
        List<ViewTasksDTO> viewTasksDTOList = taskRepository.findTasksByHandler_Id(user.getId())
                .stream()
                .map(taskMapper::toViewTaskDto)
                .collect(Collectors.toList());
        return userMapper.toViewUserDTO(user, viewTasksDTOList);
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findUserById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this id not founded: " + id)
        );
    }

    @Override
    public User updateUsername(String id, String username) {
        User user = getUserById(id);
        user.setUsername(username);
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateEmail(String id, String email) {
        User user = getUserById(id);
        user.setEmail(email);
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(String id, UpdateUserDTO updateUserDTO) {
        User user = getUserById(id);
        user.setUsername(updateUserDTO.username());
        user.setEmail(updateUserDTO.email());
        return userRepository.save(user);
    }

    @Override
    public String deleteUserById(String id) {
        String message;
        if (!userRepository.existsById(id)){
            return "uuuuppssss..... User with id: " + id + " doesn't exists";
        }
        User deletedUser = getUserById(id);
        userRepository.delete(deletedUser);

        message = "Get out here";
        return message;
    }
}
