package com.parkinglot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SuperSmartParkingBoy extends GenericParkingBoy{
    public SuperSmartParkingBoy(ArrayList<ParkingLot> lots)
    {
        super(lots);
    }

    @Override
    public Ticket parkCar(Car car)
    {
        ParkingLot useLot = lots
                .stream()
                .max(Comparator.comparing(lot -> lot.remainPercentage()))
                .get();

        if(!useLot.haveCapacity())
        {
            throw new NoSlotLeftException("No available position.");
        }

        return useLot.parkCar(car);
    }
}
