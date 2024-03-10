package com.arth.ToDOApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arth.ToDOApi.DTOs.task.CreateTaskDTO;
import com.arth.ToDOApi.DTOs.task.TaskResponseDTO;
import com.arth.ToDOApi.services.TaskServices;

@RestController
@RequestMapping("/task")
public class TaskController 
{
    
    @Autowired
    private TaskServices taskServices;

    @PostMapping("/add")
    public ResponseEntity<TaskResponseDTO> addTaskToUser(@RequestBody CreateTaskDTO dto)
    {
        TaskResponseDTO data = taskServices.addTaskToUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(data);
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<TaskResponseDTO>> taskOfUser(@PathVariable String email)
    {
        List<TaskResponseDTO> data = taskServices.getAllTaskOfUser(email);
        return ResponseEntity.ok(data);
    }
}
