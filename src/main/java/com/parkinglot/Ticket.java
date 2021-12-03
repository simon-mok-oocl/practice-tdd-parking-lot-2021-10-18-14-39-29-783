package com.parkinglot;

public class Ticket {
    String plate;
    private boolean valid;

    public Ticket(String plate)
    {
        this.plate = plate;
        this.valid = true;
    }

    public String getPlate()
    {
        return this.plate;
    }

    public void makeInvalid()
    {
        this.valid = false;
    }

    public boolean isValid()
    {
        return this.valid;
    }
}
