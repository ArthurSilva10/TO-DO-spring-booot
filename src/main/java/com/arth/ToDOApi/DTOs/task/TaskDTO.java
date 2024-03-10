package com.arth.ToDOApi.DTOs.task;

import java.time.Instant;
import java.util.Date;

import com.arth.ToDOApi.entities.UserEntity;

public record TaskDTO(String title, String description, Date expiration, Instant creation, UserEntity responsibleName) 
{
}
