package com.arth.ToDOApi.controller;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.arth.ToDOApi.entities.StandardError;
import com.arth.ToDOApi.exeptions.DataNotFoundExeption;
import com.arth.ToDOApi.exeptions.EntityAlreadyExistsExeption;

@ControllerAdvice
public class ExeptionHandler 
{
    
    @ExceptionHandler(DataNotFoundExeption.class)
    public ResponseEntity<StandardError> hadleDataNotFound(DataNotFoundExeption exeption)
    {
        StandardError error = new StandardError();
        error.setMessage(exeption.getMessage());
        error.setTimestamp(Instant.now());
        error.setCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(error.getCode()).body(error);
    }

    @ExceptionHandler(EntityAlreadyExistsExeption.class)
    public ResponseEntity<StandardError> handleEntityAlreadyExists(EntityAlreadyExistsExeption exeption)
    {
        StandardError error = new StandardError();
        error.setMessage(exeption.getMessage());
        error.setTimestamp(Instant.now());
        error.setCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(error.getCode()).body(error);
    }
}
