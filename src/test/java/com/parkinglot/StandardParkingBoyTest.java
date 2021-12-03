package com.parkinglot;

import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StandardParkingBoyTest {
    @Test
    public void should_return_lot1_ticket_when_parkCar_given_lot1_is_available()
    {
        ParkingLot lot1 = new ParkingLot();
        ParkingLot lot2 = new ParkingLot();
        ArrayList<ParkingLot> lots= new ArrayList();
        lots.add(lot1);
        lots.add(lot2);
        StandardParkingBoy boy = new StandardParkingBoy(lots);
        Car car1 = new Car("car1");

        Ticket ticket = boy.parkCar(car1);

        assertNotNull(ticket);
        assertEquals(9 , lot1.remainCapacity());
    }

}
