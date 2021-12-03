package com.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Car> searchCar = cars.stream().filter(car -> car.getPlate() == ticket.getPlate()).collect(Collectors.toList());

        if(searchCar.size() > 0)
        {
            cars.remove(searchCar.get(0));
            ticket.makeInvalid();
            return searchCar.get(0);
        }
        return null;
    }
}
