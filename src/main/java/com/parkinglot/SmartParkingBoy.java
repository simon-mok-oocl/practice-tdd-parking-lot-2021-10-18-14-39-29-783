package com.parkinglot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

        List<ParkingLot> useLot = lots
                .stream()
                .filter(lot -> lot.isCarExists(ticket))
                .collect(Collectors.toList());

        if(useLot.size() > 0)
        {
            return useLot.get(0).fetchCar(ticket);
        }

        throw new UnauthorizedCarFetch("Smart Boy: Unauthorized parking ticket (wrong).");
    }

}
