/*
 * BookingControllerTests
 * Developer: Rengaraj Arumugam
 * 
 */
package com.hotel.bookingApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotel.constants.BookingConstants;
import com.hotel.controller.BookingController;
import com.hotel.dto.BookingDto;
import com.hotel.model.BookingBO;
import com.hotel.service.BookingService;


@ExtendWith(MockitoExtension.class)
class BookingControllerTests {
	
	@InjectMocks
	private BookingController bookingController;
	
	@Mock
	 private BookingService services;

	@Test
	void testSetNoOfRooms() {
		when(services.setNoOfRooms("2")).thenReturn(BookingConstants.NO_ERROR);
		when(services.getNoOfRooms()).thenReturn(2);
		String res = bookingController.setNoOfRooms("2");	
		assertEquals("Now 2 rooms/day are available for bookings",res);
	}
	
	@Test
	void testSetNoOfRoomsFails() {
		when(services.setNoOfRooms("A")).thenReturn(BookingConstants.E0001);
		//when(services.getNoOfRooms()).thenReturn(2);
		String res = bookingController.setNoOfRooms("A");	
		assertEquals(BookingConstants.E0001,res);
	}
	
	@Test
	void testSetNoOfRoomsFails2() {
		when(services.setNoOfRooms("2 ")).thenReturn(BookingConstants.E0001);
		//when(services.getNoOfRooms()).thenReturn(2);
		String res = bookingController.setNoOfRooms("2 ");	
		assertEquals(BookingConstants.E0001,res);
	}
	
	@Test
	void testSetNoOfRoomsFails3() {
		when(services.setNoOfRooms("0.2")).thenReturn(BookingConstants.E0001);
		//when(services.getNoOfRooms()).thenReturn(2);
		String res = bookingController.setNoOfRooms("0.2");	
		assertEquals(BookingConstants.E0001,res);
	}
	
	@Test
	void testSetNoOfRoomsFails4() {
		when(services.setNoOfRooms("-4")).thenReturn(BookingConstants.E0001);
		//when(services.getNoOfRooms()).thenReturn(2);
		String res = bookingController.setNoOfRooms("-4");	
		assertEquals(BookingConstants.E0001,res);
	}
	
	@Test
	void testGetNoOfRooms() {
		when(services.getNoOfRooms()).thenReturn(Integer.parseInt("2"));
		String res = bookingController.getNoOfRooms();	
		assertEquals("2 rooms/day are available for bookings",res);
	}
	
	
	@Test
	void testCheckDate() {
		String date = "15-08-2021";
		when(services.checkAvailableRooms(date)).thenReturn("2");
		String res = bookingController.checkDate(date);	
		assertEquals("2 rooms are available for bookings on "+date,res);
	}
	
	@Test
	void testCheckDateFails() {
		String date = null;
		when(services.checkAvailableRooms(date)).thenReturn(BookingConstants.E0003);
		String res = bookingController.checkDate(date);	
		assertEquals(BookingConstants.E0003,res);
	}
	
	@Test
	void testCheckDateFails1() {
		String date = "";
		when(services.checkAvailableRooms(date)).thenReturn(BookingConstants.E0003);
		String res = bookingController.checkDate(date);	
		assertEquals(BookingConstants.E0003,res);
	}
	
	@Test
	void testCheckDateFails2() {
		String date = "34-09-2021";
		when(services.checkAvailableRooms(date)).thenReturn(BookingConstants.E0003);
		String res = bookingController.checkDate(date);	
		assertEquals(BookingConstants.E0003,res);
	}
	
	@Test
	void testCheckGuest() {
		String name = "renga";
		String date = "15-08-2021";
		BookingBO bo = new BookingBO(name, date);
		List<BookingBO> expectedlist = new ArrayList<>();
		expectedlist.add(bo);
		
		when(services.checkGuest(name)).thenReturn(expectedlist );
		List<BookingBO> res = bookingController.checkGuest(name);	
		assertEquals(expectedlist,res);
	}
	
	@Test
	void testCheckGuestFails1() {
		String name = null;
		List expectedList = new ArrayList();
		expectedList.add(BookingConstants.E0004);
		when(services.checkGuest(name)).thenReturn(expectedList );
		List res = bookingController.checkGuest(name);	
		assertEquals(expectedList,res);
	}
	
	@Test
	void testBookRoom() {
		String name = "renga";
		String date = "16-08-2021";
		BookingDto bo = new BookingDto(name, date);
		String expectedRes = "16-08-2021-1";
		List<String> list = new ArrayList<String>();
		list.add(expectedRes);
		when(services.bookRoom(name, date)).thenReturn(list);
		List<String> res = bookingController.bookRoom(bo);	
		assertEquals("Here is your booking ref no ["+expectedRes+"]",res.get(0));
	}
	
	
	@Test
	void testBookRoomFail1() { 
		String name = "renga1";  //invalid name
		String date = "16-08-2021";
		String expectedRes = BookingConstants.E0006;
		List<String> expectedlist = new ArrayList<String>();
		expectedlist.add(expectedRes);
		when(services.bookRoom(name, date)).thenReturn(expectedlist);
		BookingDto bo = new BookingDto(name, date);
		List<String> res = bookingController.bookRoom(bo);	
		assertEquals(expectedlist,res);
	}
	
	@Test
	void testBookRoomFail2() {
		String name = "renga";
		String date = "36-08-2021"; //invalid date
		String expectedRes = BookingConstants.E0003;
		List<String> expectedlist = new ArrayList<String>();
		expectedlist.add(expectedRes);
		when(services.bookRoom(name, date)).thenReturn(expectedlist);
		BookingDto bo = new BookingDto(name, date);
		List<String> res = bookingController.bookRoom(bo);	
		assertEquals(expectedlist,res);
	}
	
	@Test
	void testBookRoomFail3() {
		String name = "renga@"; //invalid name
		String date = "36-08-2021"; //invalid date
		String expectedRes1 = BookingConstants.E0003;
		String expectedRes2 = BookingConstants.E0006;
		List<String> expectedlist = new ArrayList<String>();
		expectedlist.add(expectedRes1);
		expectedlist.add(expectedRes2);
		when(services.bookRoom(name, date)).thenReturn(expectedlist);
		BookingDto bo = new BookingDto(name, date);
		List<String> res = bookingController.bookRoom(bo);	
		assertEquals(expectedlist,res);
	}

}
