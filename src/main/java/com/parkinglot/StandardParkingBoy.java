package com.parkinglot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StandardParkingBoy {
    List<ParkingLot> lots;

    public StandardParkingBoy(ArrayList<ParkingLot> lots)
    {
        this.lots = lots;
    }

    public Ticket parkCar(Car car)
    {

        for(ParkingLot lot : lots)
        {
            if(lot.haveCapacity())
            {
                return lot.parkCar(car);
            }
        }

        throw new NoSlotLeftException("No available position.");
    }

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

        if(useLot.size() > 0)
        {
            return useLot.get(0).fetchCar(ticket);
        }

        throw new UnauthorizedCarFetch("Unauthorized parking ticket (wrong).");
    }

}
