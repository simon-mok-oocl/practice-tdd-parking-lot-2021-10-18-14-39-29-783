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

        return lots.get(0).parkCar(car);
    }

}
