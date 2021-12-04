package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SmartParkingBoyTest {
    @Test
    public void should_return_lot1_ticket_when_parkCar_given_two_lot_with_same_remaining_slot()
    {
        // given
        ParkingLot lot1 = new ParkingLot();
        ParkingLot lot2 = new ParkingLot();
        ArrayList<ParkingLot> lots= new ArrayList();
        lots.add(lot1);
        lots.add(lot2);

        SmartParkingBoy boy = new SmartParkingBoy(lots);
        Car car1 = new Car("car1");

        // when
        Ticket ticket = boy.parkCar(car1);

        // than
        assertNotNull(ticket);
        assertEquals(9 , lot1.remainCapacity());
        assertEquals(10 , lot2.remainCapacity());
    }

    @Test
    public void should_return_lot2_ticket_when_parkCar_given_lot2_have_more_space()
    {
        // given
        ParkingLot lot1 = new ParkingLot();
        ParkingLot lot2 = new ParkingLot(11);
        ArrayList<ParkingLot> lots= new ArrayList();
        lots.add(lot1);
        lots.add(lot2);

        SmartParkingBoy boy = new SmartParkingBoy(lots);
        Car car1 = new Car("car1");

        // when
        Ticket ticket = boy.parkCar(car1);

        // than
        assertNotNull(ticket);
        assertEquals(10 , lot1.remainCapacity());
        assertEquals(10 , lot2.remainCapacity());
    }
}
