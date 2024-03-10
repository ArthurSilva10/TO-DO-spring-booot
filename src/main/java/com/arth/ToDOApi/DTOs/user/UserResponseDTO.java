package com.arth.ToDOApi.DTOs.user;

import java.util.List;

import com.arth.ToDOApi.DTOs.task.TaskResponseDTO;
import com.arth.ToDOApi.entities.UserRole;

public record UserResponseDTO(long id, String userName, String email, String password, UserRole role, List<TaskResponseDTO> tasks) {
    
}
