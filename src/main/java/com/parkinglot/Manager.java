package com.parkinglot;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public Car fetchCarByBoy(Ticket ticket)
    {
        if(!ticket.isValid())
        {
            throw new UnauthorizedCarFetch("Unauthorized parking ticket (used).");
        }

        List<GenericParkingBoy> useBoy = boys.stream().filter(boy -> boy.haveCar(ticket)).collect(Collectors.toList());

        if(useBoy.size() > 0)
        {
            return useBoy.get(0).fetchCar(ticket);
        }

        throw new UnauthorizedCarFetch("Unauthorized parking ticket (wrong).");
    }
}
