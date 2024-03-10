package com.arth.ToDOApi.DTOs.task;

import java.time.Instant;
import java.util.Date;

public record CreateTaskDTO(String Email, String title, String description, 
                            Date expiration, Instant creation, String responsibleName) 
{
    
} 