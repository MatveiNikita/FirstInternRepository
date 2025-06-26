package com.example.firstinternrepository.controller;

import com.example.firstinternrepository.dto.CreateUserDTO;
import com.example.firstinternrepository.dto.UpdateUserDTO;
import com.example.firstinternrepository.dto.ViewUserDTO;
import com.example.firstinternrepository.model.User;
import com.example.firstinternrepository.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("users")
@RequiredArgsConstructor
public class UsersController {


    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createNewUser(@RequestBody CreateUserDTO createUserDTO){
        User newUser = userService.createUser(createUserDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<ViewUserDTO> getUserById(@RequestParam String id){
        ViewUserDTO viewUser = userService.getViewUserById(id);
        return ResponseEntity.ok(viewUser);
    }

    @PatchMapping("/{id}/update-username")
    public ResponseEntity<User> updateUserUsername(@PathVariable String id, @RequestParam String username){
        User user = userService.updateUsername(id, username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/{id}/update-email")
    public ResponseEntity<User> updateUserEmail(@PathVariable String id, @RequestParam String email){
        User user = userService.updateEmail(id, email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/{id}/update-user")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody UpdateUserDTO updateUserDTO){
        User user = userService.updateUser(id, updateUserDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteByUsername(@RequestParam String id){
        String message = userService.deleteUserById(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
