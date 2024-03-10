package com.arth.ToDOApi.DTOs.user;

import com.arth.ToDOApi.entities.UserRole;

public record UserDTO(long id, String userName, String email, String password, UserRole role) 
{
    
}
