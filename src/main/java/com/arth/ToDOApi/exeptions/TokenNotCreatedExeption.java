package com.arth.ToDOApi.exeptions;

public class TokenNotCreatedExeption extends RuntimeException
{
    public TokenNotCreatedExeption(String message)
    {
        super(message);
    }
}
