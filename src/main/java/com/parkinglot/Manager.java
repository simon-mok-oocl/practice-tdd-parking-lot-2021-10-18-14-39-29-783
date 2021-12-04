package com.parkinglot;


import java.util.ArrayList;
import java.util.List;

class Manager extends StandardParkingBoy {
    List<GenericParkingBoy> boys;

    public Manager(ArrayList<ParkingLot> lots , List<GenericParkingBoy> boys)
    {
        super(lots);
        this.boys = boys;
    }

    public Ticket parkCarByBoy(int boyId , Car car)
    {
        return boys.get(boyId).parkCar(car);
    }
}
