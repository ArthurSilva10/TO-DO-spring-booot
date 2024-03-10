package com.arth.ToDOApi.exeptions;

public class DataNotFoundExeption extends RuntimeException
{
    public DataNotFoundExeption(String message)
    {
        super(message);
    }
}