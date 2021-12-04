package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SuperSmartParkingBoyTest {
    @Test
    public void should_return_lot1_ticket_when_parkCar_given_two_lot_with_same_remaining_slot()
    {
        // given
        ParkingLot lot1 = new ParkingLot();
        ParkingLot lot2 = new ParkingLot();
        ArrayList<ParkingLot> lots= new ArrayList();
        lots.add(lot1);
        lots.add(lot2);

        SuperSmartParkingBoy boy = new SuperSmartParkingBoy(lots);
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
        ParkingLot lot1 = new ParkingLot(30);
        ParkingLot lot2 = new ParkingLot();
        ArrayList<ParkingLot> lots= new ArrayList();
        lots.add(lot1);
        lots.add(lot2);

        SuperSmartParkingBoy boy = new SuperSmartParkingBoy(lots);
        Car car1 = new Car("car1");

        for(int i = 0 ; i < 10 ; i++)
        {
            lot1.parkCar(new Car("car"));
        }

        // when
        Ticket ticket = boy.parkCar(car1);

        // than
        assertNotNull(ticket);
        assertEquals(20 , lot1.remainCapacity());
        assertEquals(9 , lot2.remainCapacity());
    }

    @Test
    public void should_return_correct_car_when_fetchCar_given_two_ticket()
    {
        // given
        ParkingLot lot1 = new ParkingLot();
        ParkingLot lot2 = new ParkingLot(11);
        ArrayList<ParkingLot> lots= new ArrayList();
        lots.add(lot1);
        lots.add(lot2);

        SuperSmartParkingBoy boy = new SuperSmartParkingBoy(lots);
        Car car1 = new Car("car1");
        Car car2 = new Car("car2");

        Ticket ticket1 = boy.parkCar(car1);
        Ticket ticket2 = boy.parkCar(car2);

        // when
        Car returnCar1 = boy.fetchCar(ticket1);
        Car returnCar2 = boy.fetchCar(ticket2);


        // than
        assertEquals(car1 , returnCar1);
        assertEquals(car2 , returnCar2);
    }
}
