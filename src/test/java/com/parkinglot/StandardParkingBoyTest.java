package com.parkinglot;

import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StandardParkingBoyTest {
    @Test
    public void should_return_lot1_ticket_when_parkCar_given_lot1_is_available()
    {
        // given
        ParkingLot lot1 = new ParkingLot();
        ParkingLot lot2 = new ParkingLot();
        ArrayList<ParkingLot> lots= new ArrayList();
        lots.add(lot1);
        lots.add(lot2);
        StandardParkingBoy boy = new StandardParkingBoy(lots);
        Car car1 = new Car("car1");

        // when
        Ticket ticket = boy.parkCar(car1);

        // than
        assertNotNull(ticket);
        assertEquals(9 , lot1.remainCapacity());
    }

    @Test
    public void should_return_lot2_ticket_when_parkCar_given_lot1_is_full()
    {
        // given
        ParkingLot lot1 = new ParkingLot(2);
        ParkingLot lot2 = new ParkingLot();
        ArrayList<ParkingLot> lots= new ArrayList();
        lot1.parkCar(new Car("lot 1 car 1"));
        lot1.parkCar(new Car("lot 2 car 2"));
        lots.add(lot1);
        lots.add(lot2);
        StandardParkingBoy boy = new StandardParkingBoy(lots);
        Car car1 = new Car("car1");

        // when
        Ticket ticket = boy.parkCar(car1);

        // than
        assertNotNull(ticket);
        assertEquals(9 , lot2.remainCapacity());
        assertEquals(0 , lot1.remainCapacity());
    }

    @Test
    public void should_return_correct_when_fetchCar_given_two_ticket()
    {
        //given
        ParkingLot lot1 = new ParkingLot(2);
        ParkingLot lot2 = new ParkingLot();
        ArrayList<ParkingLot> lots= new ArrayList();
        lots.add(lot1);
        lots.add(lot2);
        StandardParkingBoy boy = new StandardParkingBoy(lots);
        Car car1 = new Car("car1");
        Car car2 = new Car("car2");

        Ticket ticket1 = boy.parkCar(car1);
        Ticket ticket2 = boy.parkCar(car2);

        //when
        Car returnCar1 = boy.fetchCar(ticket1);
        Car returnCar2 = boy.fetchCar(ticket2);

        //than
        assertEquals(car1 , returnCar1);
        assertEquals(car2 , returnCar2);
    }

    @Test
    public void should_throw_exception_when_fetchCar_given_wrong_ticket()
    {
        //given
        ParkingLot lot1 = new ParkingLot(2);
        ParkingLot lot2 = new ParkingLot();
        ArrayList<ParkingLot> lots= new ArrayList();
        lots.add(lot1);
        lots.add(lot2);
        StandardParkingBoy boy = new StandardParkingBoy(lots);

        Car car1 = new Car("car1");
        Ticket tacket1 = boy.parkCar(car1);

        // when
        Ticket wrongTicket = new Ticket("wrong ticket");


        // than
        UnauthorizedCarFetch exception = assertThrows(UnauthorizedCarFetch.class , () -> {boy.fetchCar(wrongTicket);});
        assertEquals("Unauthorized parking ticket (wrong)." , exception.getMessage());
    }

    @Test
    public void should_throw_exception_when_fetchCar_given_used_ticket()
    {
        //given
        ParkingLot lot1 = new ParkingLot(2);
        ParkingLot lot2 = new ParkingLot();
        ArrayList<ParkingLot> lots= new ArrayList();
        lots.add(lot1);
        lots.add(lot2);
        StandardParkingBoy boy = new StandardParkingBoy(lots);

        Car car1 = new Car("car1");
        Ticket ticket1 = boy.parkCar(car1);

        // when
        boy.fetchCar(ticket1);


        // than
        UnauthorizedCarFetch exception = assertThrows(UnauthorizedCarFetch.class , () -> {boy.fetchCar(ticket1);});
        assertEquals("Unauthorized parking ticket (used)." , exception.getMessage());
    }
}
