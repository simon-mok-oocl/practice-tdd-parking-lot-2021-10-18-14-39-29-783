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

    @Test
    public void should_return_null_when_partCar_given_full_parkingLot()
    {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car1 = new Car("plat 1");
        Car car2 = new Car("plate 2");

        Ticket ticket1 = parkingLot.parkCar(car1);
        Ticket ticket2 = parkingLot.parkCar(car2);

        assertNotNull(ticket1);
        assertEquals(ticket1.getPlate() , car1.getPlate());
        assertNull(ticket2);
    }

    @Test
    public void should_return_car_when_fetch_car_with_valid_ticket()
    {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("plat 1");

        Ticket ticket = parkingLot.parkCar(car);
        Car returnCar = parkingLot.fetchCar(ticket);

        assertEquals(car , returnCar);
    }

    @Test
    public void should_return_correct_car_when_fetchCar_given_two_ticket()
    {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car1 = new Car("plate 1");
        Car car2 = new Car("plate 2");

        Ticket ticket1 = parkingLot.parkCar(car1);
        Ticket ticket2 = parkingLot.parkCar(car2);
        Car returnCar1 = parkingLot.fetchCar(ticket1);
        Car returnCar2 = parkingLot.fetchCar(ticket2);

        assertEquals(car1 , returnCar1);
        assertEquals(car2 , returnCar2);
    }

    @Test
    public void should_return_no_car_when_fetchCar_given_incorrect_ticket()
    {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car1 = new Car("plate 1");
        Ticket wrongTicket = new Ticket("I am so wrong");

        Ticket ticket1 = parkingLot.parkCar(car1);
        Car returnCar = parkingLot.fetchCar(wrongTicket);

        assertNull(returnCar);

    }

    @Test
    public void should_return_no_car_when_fetchCar_given_invalid_ticket()
    {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car1 = new Car("plate 1");

        Ticket ticket = parkingLot.parkCar(car1);
        Car returnCar1 = parkingLot.fetchCar(ticket);
        Car returnCar2 = parkingLot.fetchCar(ticket);

        assertNull(returnCar2);

    }
}
