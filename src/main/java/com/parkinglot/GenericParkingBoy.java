package com.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

abstract class GenericParkingBoy {
    List<ParkingLot> lots;

    public GenericParkingBoy(List<ParkingLot> lots)
    {
        this.lots = lots;
    }

    abstract public Ticket parkCar(Car car);

    public Car fetchCar(Ticket ticket)
    {
        if(! ticket.isValid())
        {
            throw new UnauthorizedCarFetch("Unauthorized parking ticket (used).");
        }

        List<ParkingLot> useLot = lots
                .stream()
                .filter(lot -> lot.isCarExists(ticket))
                .collect(Collectors.toList());

        if( useLot.size() > 0)  // lots may be null for manager.
        {
            return useLot.get(0).fetchCar(ticket);
        }

        throw new UnauthorizedCarFetch("Unauthorized parking ticket (wrong).");
    }

    public boolean haveCar(Ticket ticket)
    {
        return lots.stream().filter(lot -> lot.isCarExists(ticket)).collect(Collectors.toList()).size() > 0;
    }
}
