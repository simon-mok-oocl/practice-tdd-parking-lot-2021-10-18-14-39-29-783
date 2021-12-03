package com.parkinglot;

public class UnauthorizedCarFetch extends RuntimeException{
    public UnauthorizedCarFetch(String message)
    {
        super(message);
    }
}
