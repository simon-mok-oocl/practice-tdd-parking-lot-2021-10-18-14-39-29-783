package com.parkinglot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
        return null;
    }

    public Car fetchCar(Ticket ticket)
    {


        return null;
    }

}
