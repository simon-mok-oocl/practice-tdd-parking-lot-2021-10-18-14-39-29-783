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

        return useLot.parkCar(car);
    }
}
