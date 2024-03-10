package com.arth.ToDOApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arth.ToDOApi.DTOs.user.CreateUserDTO;
import com.arth.ToDOApi.DTOs.user.UserDTO;
import com.arth.ToDOApi.DTOs.user.UserLoginRequest;
import com.arth.ToDOApi.DTOs.user.UserResponseDTO;
import com.arth.ToDOApi.services.TaskServices;
import com.arth.ToDOApi.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController 
{
    
    @Autowired
    private UserServices userServices;

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> allUsers()
    {
        List<UserDTO> users = userServices.allUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDTO> findUser(@PathVariable String email)
    {
        UserResponseDTO user = userServices.findById(email);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/add")
    public ResponseEntity<CreateUserDTO> addUser(@RequestBody @Valid CreateUserDTO userDTO)
    {
        CreateUserDTO user = userServices.addUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity authenticateUser(@RequestBody @Valid UserLoginRequest userLoginRequest)
    {
        var token = userServices.login(userLoginRequest);
        return ResponseEntity.ok(token);
    }

    @DeleteMapping("/erase/{id}")
    public ResponseEntity<String> delete(@PathVariable long id)
    {
        userServices.deleteUser(id);
        return ResponseEntity.ok("Usuário deletado com sucesso!");
    }
}