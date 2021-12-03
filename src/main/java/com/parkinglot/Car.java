package com.parkinglot;

public class Car {
    private String plate;

    public Car(String plate) {
        this.plate = plate;
    }

    public String getPlate()
    {
        return this.plate;
    }

    @Override
    public boolean equals(Object obj)
    {
        return ((Car)obj).getPlate() == this.plate;
    }
}
