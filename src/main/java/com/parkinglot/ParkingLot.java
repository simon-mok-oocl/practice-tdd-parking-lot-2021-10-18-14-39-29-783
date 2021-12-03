package com.parkinglot;

import java.util.ArrayList;

public class ParkingLot {
    private int capacity;
    private ArrayList<Car> cars;

    public ParkingLot(int maxCapacity)
    {
        this.capacity = maxCapacity;
        cars = new ArrayList<Car>();
    }

    private boolean checkCapacity()
    {
        return this.cars.size() < capacity;
    }

    public Ticket parkCar(Car car)
    {
        if(this.checkCapacity())
        {
            this.cars.add(car);
            return new Ticket(car.getPlate());
        }
        else
        {
            return null;
        }
    }

    public Car fetchCar(Ticket ticket)
    {
        return null;
    }
}
