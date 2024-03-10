package com.arth.ToDOApi.exeptions;

public class EntityAlreadyExistsExeption extends RuntimeException
{
    public EntityAlreadyExistsExeption(String message)
    {
        super(message);
    }
}
