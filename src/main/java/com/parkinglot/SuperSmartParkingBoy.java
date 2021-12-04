package com.parkinglot;

import java.util.ArrayList;
import java.util.Comparator;

public class SuperSmartParkingBoy {
    ArrayList<ParkingLot> lots;
    public SuperSmartParkingBoy(ArrayList<ParkingLot> lots)
    {
        this.lots = lots;
    }

    public Ticket parkCar(Car car)
    {
        ParkingLot useLot = lots
                .stream()
                .max(Comparator.comparing(lot -> lot.remainPercentage()))
                .get();

        if(!useLot.haveCapacity())
        {
            throw new NoSlotLeftException("Smart Boy: No available position.");
        }

        return useLot.parkCar(car);
    }

    public Car fetchCar(Ticket ticket)
    {
        if(! ticket.isValid())
        {
            throw new UnauthorizedCarFetch("Smart Boy: Unauthorized parking ticket (used).");
        }
        for(ParkingLot lot : lots)
        {
            if(lot.isCarExists(ticket))
            {
                return lot.fetchCar(ticket);
            }
        }

        throw new UnauthorizedCarFetch("Smart Boy: Unauthorized parking ticket (wrong).");
    }
}
