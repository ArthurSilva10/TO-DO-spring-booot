package com.arth.ToDOApi.exeptions;

public class TokenNotValidExeption extends RuntimeException
{
    public TokenNotValidExeption(String message)
    {
        super(message);
    }
}
