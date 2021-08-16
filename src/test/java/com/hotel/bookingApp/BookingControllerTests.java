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
		when(services.getNoOfRooms()).thenReturn(Integer.parseInt("2"));
		String res = bookingController.setNoOfRooms("2");	
		assertEquals("Now 2 rooms/day are available for bookings",res);
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
		when(services.checkAvailableRooms(date)).thenReturn(Integer.parseInt("2"));
		String res = bookingController.checkDate(date);	
		assertEquals("2 rooms are available for bookings on "+date,res);
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
	void testBookRoom() {
		String name = "renga";
		String date = "16-08-2021";
		BookingDto bo = new BookingDto(name, date);
		String expectedRes = "16-08-2021-1";
		when(services.bookRoom(name, date)).thenReturn(expectedRes);
		String res = bookingController.bookRoom(bo);	
		assertEquals("Here is your booking ref no "+expectedRes,res);
	}

}
