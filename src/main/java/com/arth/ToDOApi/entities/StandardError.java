package com.arth.ToDOApi.entities;

import java.time.Instant;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StandardError 
{
    private String message;
    private Instant timestamp;
    private int code;
}
