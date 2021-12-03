package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParkingLotTest {
    @Test
    public void shold_return_ticket_when_partCar_given_car_and_parkingLot()
    {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("plat 1");

        Ticket ticket = parkingLot.parkCar(car);

        assertNotNull(ticket);
        assertEquals(ticket.getPlate() , car.getPlate());
    }


}
