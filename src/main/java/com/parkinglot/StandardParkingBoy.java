package com.parkinglot;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StandardParkingBoy extends GenericParkingBoy {

    public StandardParkingBoy(ArrayList<ParkingLot> lots)
    {
        super(lots);
    }

    @Override
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

}
