package com.parkinglot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

public class SmartParkingBoy {
    ArrayList<ParkingLot> lots;
    public SmartParkingBoy(ArrayList<ParkingLot> lots)
    {
        this.lots = lots;
    }

    public Ticket parkCar(Car car)
    {
        ParkingLot useLot = lots
                .stream()
                .max(Comparator.comparing(lot -> lot.remainCapacity()))
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
