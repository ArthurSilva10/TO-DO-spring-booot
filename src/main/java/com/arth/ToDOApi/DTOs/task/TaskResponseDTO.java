package com.arth.ToDOApi.DTOs.task;

import java.time.Instant;
import java.util.Date;

public record TaskResponseDTO(String title, String description, Date expiration, Instant creation,
 String responsibleName) 
{
    
}
