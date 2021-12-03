package com.parkinglot;

public class ParkingLot {
    private int capacity;

    public ParkingLot(int maxCapacity)
    {
        this.capacity = maxCapacity;
    }

    public Ticket parkCar(Car car)
    {
        return new Ticket(car.getPlate());
    }
}
