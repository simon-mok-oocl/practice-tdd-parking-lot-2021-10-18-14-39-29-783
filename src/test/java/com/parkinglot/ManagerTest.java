package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManagerTest {

    ///////////////////////////Test manager as standard boy ////////////////////////////////////////////
    @Test
    public void should_return_lot1_ticket_when_parkCar_given_lot1_is_available()
    {
        // given
        ParkingLot lot1 = new ParkingLot();
        ParkingLot lot2 = new ParkingLot();
        ArrayList<ParkingLot> lots= new ArrayList();
        lots.add(lot1);
        lots.add(lot2);
        Manager manager= new Manager(lots , null);
        Car car1 = new Car("car1");

        // when
        Ticket ticket = manager.parkCar(car1);

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
        Manager manager = new Manager(lots , null);
        Car car1 = new Car("car1");

        // when
        Ticket ticket = manager.parkCar(car1);

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
        Manager manager = new Manager(lots , null);
        Car car1 = new Car("car1");
        Car car2 = new Car("car2");

        Ticket ticket1 = manager.parkCar(car1);
        Ticket ticket2 = manager.parkCar(car2);

        //when
        Car returnCar1 = manager.fetchCar(ticket1);
        Car returnCar2 = manager.fetchCar(ticket2);

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
        Manager manager = new Manager(lots , null);

        Car car1 = new Car("car1");
        Ticket tacket1 = manager.parkCar(car1);

        // when
        Ticket wrongTicket = new Ticket("wrong ticket");


        // than
        UnauthorizedCarFetch exception = assertThrows(UnauthorizedCarFetch.class , () -> {manager.fetchCar(wrongTicket);});
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
        Manager manager = new Manager(lots , null);

        Car car1 = new Car("car1");
        Ticket ticket1 = manager.parkCar(car1);

        // when
        manager.fetchCar(ticket1);

        // than
        UnauthorizedCarFetch exception = assertThrows(UnauthorizedCarFetch.class , () -> {manager.fetchCar(ticket1);});
        assertEquals("Unauthorized parking ticket (used)." , exception.getMessage());
    }

    @Test
    public void should_throuw_except_when_parkCar_given_all_lot_are_full()
    {
        //given
        ParkingLot lot1 = new ParkingLot(1);
        ParkingLot lot2 = new ParkingLot(1);
        ArrayList<ParkingLot> lots= new ArrayList();
        lots.add(lot1);
        lots.add(lot2);
        Manager manager = new Manager(lots , null);

        Car car1 = new Car("car1");
        Car car2 = new Car("car2");
        manager.parkCar(car1);
        manager.parkCar(car2);

        // when
        Car car3 = new Car("car3");
        NoSlotLeftException exception = assertThrows(NoSlotLeftException.class , () -> {manager.parkCar(car3);});
        assertEquals("No available position." , exception.getMessage());

    }

    //////////////////////// Test Manager command all 3 types of boys /////////////////////////

    @Test
    public void should_return_ticket_when_parkCarByBoys_given_boys_with_unused_lot()
    {
        // when
        ParkingLot standardBoyLot1 = new ParkingLot();
        ParkingLot standardBoyLot2 = new ParkingLot();
        StandardParkingBoy standardBoy = new StandardParkingBoy(Arrays.asList(standardBoyLot1 , standardBoyLot2));

        ParkingLot smartBoyLot1 = new ParkingLot();
        ParkingLot smartBoyLot2 = new ParkingLot();
        SmartParkingBoy smartBoy = new SmartParkingBoy(Arrays.asList(smartBoyLot1 , smartBoyLot2));


        ParkingLot superSmartBoyLot1 = new ParkingLot();
        ParkingLot superSmartBoyLot2 = new ParkingLot();
        SuperSmartParkingBoy superSmartBoy = new SuperSmartParkingBoy(Arrays.asList(superSmartBoyLot1 , superSmartBoyLot2));

        Car car1 = new Car("car1");
        Car car2 = new Car("car2");
        Car car3 = new Car("car3");

        Manager manager = new Manager(null , Arrays.asList(standardBoy , smartBoy , superSmartBoy));

        // when
        Ticket ticket1 = manager.parkCarByBoy(0 , car1);
        Ticket ticket2 = manager.parkCarByBoy(1 , car2);
        Ticket ticket3 = manager.parkCarByBoy(2 , car3);

        // then
        assertNotNull(ticket1);
        assertNotNull(ticket2);
        assertNotNull(ticket2);
    }

    @Test
    public void should_park_to_correct_lot_when_parkCarByBoys_with_multiple_boys()
    {
        // when
        ParkingLot standardBoyLot1 = new ParkingLot(1);
        ParkingLot standardBoyLot2 = new ParkingLot();
        StandardParkingBoy standardBoy = new StandardParkingBoy(Arrays.asList(standardBoyLot1 , standardBoyLot2));

        ParkingLot smartBoyLot1 = new ParkingLot();
        ParkingLot smartBoyLot2 = new ParkingLot(20);
        SmartParkingBoy smartBoy = new SmartParkingBoy(Arrays.asList(smartBoyLot1 , smartBoyLot2));


        ParkingLot superSmartBoyLot1 = new ParkingLot(30);
        ParkingLot superSmartBoyLot2 = new ParkingLot();
        SuperSmartParkingBoy superSmartBoy = new SuperSmartParkingBoy(Arrays.asList(superSmartBoyLot1 , superSmartBoyLot2));

        standardBoyLot1.parkCar(new Car("car"));
        for(int i = 0 ; i < 10 ; i++)
        {
            superSmartBoyLot1.parkCar(new Car("car"));
        }

        Manager manager = new Manager(null , Arrays.asList(standardBoy , smartBoy , superSmartBoy));

        Car car1 = new Car("car1");
        Car car2 = new Car("car2");
        Car car3 = new Car("car3");

        // when
        manager.parkCarByBoy(0 , car1);
        manager.parkCarByBoy(1 , car2);
        manager.parkCarByBoy(2 , car2);

        // than
        assertEquals(0 , standardBoyLot1.remainCapacity());
        assertEquals(19 , smartBoyLot2.remainCapacity());
        assertEquals(9 , superSmartBoyLot2.remainCapacity());
    }

    @Test
    public void should_return_correct_car_when_fetchCarByBoy_with_multiple_boys()
    {
        // given
        ParkingLot standardBoyLot1 = new ParkingLot(1);
        ParkingLot standardBoyLot2 = new ParkingLot();
        StandardParkingBoy standardBoy = new StandardParkingBoy(Arrays.asList(standardBoyLot1 , standardBoyLot2));

        ParkingLot smartBoyLot1 = new ParkingLot();
        ParkingLot smartBoyLot2 = new ParkingLot(20);
        SmartParkingBoy smartBoy = new SmartParkingBoy(Arrays.asList(smartBoyLot1 , smartBoyLot2));


        ParkingLot superSmartBoyLot1 = new ParkingLot(30);
        ParkingLot superSmartBoyLot2 = new ParkingLot();
        SuperSmartParkingBoy superSmartBoy = new SuperSmartParkingBoy(Arrays.asList(superSmartBoyLot1 , superSmartBoyLot2));

        Manager manager = new Manager(null , Arrays.asList(standardBoy , smartBoy , superSmartBoy));

        Car car1 = new Car("car1");
        Car car2 = new Car("car2");
        Car car3 = new Car("car3");
        Car car4 = new Car("car4");
        Car car5 = new Car("car5");
        Car car6 = new Car("car6");

	    Ticket ticket1 = standardBoyLot1.parkCar(car1);
	    Ticket ticket2 = standardBoyLot2.parkCar(car2);
	    Ticket ticket3 = smartBoyLot1.parkCar(car3);
	    Ticket ticket4 = smartBoyLot2.parkCar(car4);
    	Ticket ticket5 = superSmartBoyLot1.parkCar(car5);
    	Ticket ticket6 = superSmartBoyLot2.parkCar(car6);

    	// when
    	Car returnCar1 = manager.fetchCarByBoy(ticket1);
    	Car returnCar2 = manager.fetchCarByBoy(ticket2);
	    Car returnCar3 = manager.fetchCarByBoy(ticket3);
	    Car returnCar4 = manager.fetchCarByBoy(ticket4);
    	Car returnCar5 = manager.fetchCarByBoy(ticket5);
    	Car returnCar6 = manager.fetchCarByBoy(ticket6);


	    // then
	    assertEquals(car1 , returnCar1);
	    assertEquals(car2 , returnCar2);
	    assertEquals(car3 , returnCar3);
	    assertEquals(car4 , returnCar4);
	    assertEquals(car5 , returnCar5);
	    assertEquals(car6 , returnCar6);
    }

    @Test
    public void should_throw_exception_when_fetchCarByBoy_given_wrong_ticket()
    {
        // given
        ParkingLot standardBoyLot1 = new ParkingLot(1);
        ParkingLot standardBoyLot2 = new ParkingLot();
        StandardParkingBoy standardBoy = new StandardParkingBoy(Arrays.asList(standardBoyLot1 , standardBoyLot2));

        ParkingLot smartBoyLot1 = new ParkingLot();
        ParkingLot smartBoyLot2 = new ParkingLot(20);
        SmartParkingBoy smartBoy = new SmartParkingBoy(Arrays.asList(smartBoyLot1 , smartBoyLot2));


        ParkingLot superSmartBoyLot1 = new ParkingLot(30);
        ParkingLot superSmartBoyLot2 = new ParkingLot();
        SuperSmartParkingBoy superSmartBoy = new SuperSmartParkingBoy(Arrays.asList(superSmartBoyLot1 , superSmartBoyLot2));

        Manager manager = new Manager(null , Arrays.asList(standardBoy , smartBoy , superSmartBoy));

        Car car1 = new Car("car1");
        Car car2 = new Car("car2");
        Car car3 = new Car("car3");
        Car car4 = new Car("car4");
        Car car5 = new Car("car5");
        Car car6 = new Car("car6");

        Ticket ticket1 = standardBoyLot1.parkCar(car1);
        Ticket ticket2 = standardBoyLot2.parkCar(car2);
        Ticket ticket3 = smartBoyLot1.parkCar(car3);
        Ticket ticket4 = smartBoyLot2.parkCar(car4);
        Ticket ticket5 = superSmartBoyLot1.parkCar(car5);
        Ticket ticket6 = superSmartBoyLot2.parkCar(car6);


        // than
        Ticket wrongTicket = new Ticket("wrongTicket");
        UnauthorizedCarFetch exception = assertThrows(UnauthorizedCarFetch.class , () -> {manager.fetchCarByBoy(wrongTicket);});
        assertEquals("Unauthorized parking ticket (wrong)." , exception.getMessage());
    }

    @Test
    public void should_throw_exception_when_fetchCarByBoy_given_no_space()
    {
        // given
        ParkingLot standardBoyLot1 = new ParkingLot(1);
        ParkingLot standardBoyLot2 = new ParkingLot();
        StandardParkingBoy standardBoy = new StandardParkingBoy(Arrays.asList(standardBoyLot1 , standardBoyLot2));

        ParkingLot smartBoyLot1 = new ParkingLot();
        ParkingLot smartBoyLot2 = new ParkingLot(20);
        SmartParkingBoy smartBoy = new SmartParkingBoy(Arrays.asList(smartBoyLot1 , smartBoyLot2));


        ParkingLot superSmartBoyLot1 = new ParkingLot(30);
        ParkingLot superSmartBoyLot2 = new ParkingLot();
        SuperSmartParkingBoy superSmartBoy = new SuperSmartParkingBoy(Arrays.asList(superSmartBoyLot1 , superSmartBoyLot2));

        Manager manager = new Manager(null , Arrays.asList(standardBoy , smartBoy , superSmartBoy));

        Car car1 = new Car("car1");
        Car car2 = new Car("car2");
        Car car3 = new Car("car3");

        Ticket ticket1 = standardBoyLot1.parkCar(car1);
        Ticket ticket2 = smartBoyLot1.parkCar(car2);
        Ticket ticket3 = superSmartBoyLot1.parkCar(car3);

        manager.fetchCarByBoy(ticket1);
        manager.fetchCarByBoy(ticket2);
        manager.fetchCarByBoy(ticket3);

        // than
        UnauthorizedCarFetch exception = assertThrows(UnauthorizedCarFetch.class , () -> {manager.fetchCarByBoy(ticket1);});
        assertEquals("Unauthorized parking ticket (used)." , exception.getMessage());

       exception = assertThrows(UnauthorizedCarFetch.class , () -> {manager.fetchCarByBoy(ticket2);});
        assertEquals("Unauthorized parking ticket (used)." , exception.getMessage());

        exception = assertThrows(UnauthorizedCarFetch.class , () -> {manager.fetchCarByBoy(ticket2);});
        assertEquals("Unauthorized parking ticket (used)." , exception.getMessage());
    }

    @Test
    public void should_throw_exception_when_parkCarByBoy_given_all_lot_full()
    {
        // given
        ParkingLot standardBoyLot1 = new ParkingLot(1);
        ParkingLot standardBoyLot2 = new ParkingLot(1);
        StandardParkingBoy standardBoy = new StandardParkingBoy(Arrays.asList(standardBoyLot1 , standardBoyLot2));

        ParkingLot smartBoyLot1 = new ParkingLot(1);
        ParkingLot smartBoyLot2 = new ParkingLot(1);
        SmartParkingBoy smartBoy = new SmartParkingBoy(Arrays.asList(smartBoyLot1 , smartBoyLot2));


        ParkingLot superSmartBoyLot1 = new ParkingLot(1);
        ParkingLot superSmartBoyLot2 = new ParkingLot(1);
        SuperSmartParkingBoy superSmartBoy = new SuperSmartParkingBoy(Arrays.asList(superSmartBoyLot1 , superSmartBoyLot2));

        Manager manager = new Manager(null , Arrays.asList(standardBoy , smartBoy , superSmartBoy));

        Car car1 = new Car("car1");
        Car car2 = new Car("car2");
        Car car3 = new Car("car3");
        Car car4 = new Car("car4");
        Car car5 = new Car("car5");
        Car car6 = new Car("car6");

        Ticket ticket1 = standardBoyLot1.parkCar(car1);
        Ticket ticket2 = standardBoyLot2.parkCar(car2);
        Ticket ticket3 = smartBoyLot1.parkCar(car3);
        Ticket ticket4 = smartBoyLot2.parkCar(car4);
        Ticket ticket5 = superSmartBoyLot1.parkCar(car5);
        Ticket ticket6 = superSmartBoyLot2.parkCar(car6);

        // than
        Car car7 = new Car("car7");
        Car car8 = new Car("car8");
        Car car9 = new Car("car9");

        NoSlotLeftException exception = assertThrows(NoSlotLeftException.class , () -> {manager.parkCarByBoy(0 , car7);});
        assertEquals("No available position." , exception.getMessage());

        exception = assertThrows(NoSlotLeftException.class , () -> {manager.parkCarByBoy(1 , car8);});
        assertEquals("No available position." , exception.getMessage());

        exception = assertThrows(NoSlotLeftException.class , () -> {manager.parkCarByBoy(2 , car9);});
        assertEquals("No available position." , exception.getMessage());
    }
}
