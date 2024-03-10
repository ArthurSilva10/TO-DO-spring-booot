package com.arth.ToDOApi.DTOs.user;
import java.util.List;

import com.arth.ToDOApi.entities.TaskEntity;
import com.arth.ToDOApi.entities.UserRole;

public record CreateUserDTO(long id, String userName, String email, String password, UserRole role, List<TaskEntity> tasks) {
    
}
