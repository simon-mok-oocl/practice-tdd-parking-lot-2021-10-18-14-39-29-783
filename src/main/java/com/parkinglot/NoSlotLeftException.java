package com.parkinglot;

public class NoSlotLeftException extends RuntimeException{
    public NoSlotLeftException(String s) {
        super(s);
    }
}
