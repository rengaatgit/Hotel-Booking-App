/*
 * BookingServiceTests
 * Developer: Rengaraj Arumugam
 * 
 */
package com.hotel.bookingApp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.hotel.model.BookingBO;
import com.hotel.model.NoOfRooms;
import com.hotel.service.BookingService;


@ExtendWith(MockitoExtension.class)
class BookingServiceTests {

	@InjectMocks
	 private BookingService services = new BookingService();
	
	@BeforeEach
	public void setUp() {
	    MockitoAnnotations.openMocks(this);
	    ReflectionTestUtils.setField(services, "bookList", new ArrayList<BookingBO>()); 
	    ReflectionTestUtils.setField(services, "noOfRooms", new NoOfRooms(1)); 
	}
	
	@Test
	void testSetNoOfRooms() {
		String res = services.bookRoom("renga","15-08-2021");
		assertEquals("15-08-2021-1",res);
	}
	
	@Test
	void testSetNoOfRoomsFails() {
		services.bookRoom("renga","15-08-2021");
		String res = services.bookRoom("renga","15-08-2021");
		assertEquals("null",res);
	}

	
	@Test
	void testCheckBookedRooms() {
		int res = services.checkBookedRooms("15-08-2021");
		assertEquals(0,res);
	}
	
	@Test
	void testCheckAvailableRooms() {
		int res = services.checkAvailableRooms("15-08-2021");
		assertEquals(1,res);
	}
	
	
	@Test
	void testCheckGuest() {
		String name = "renga";
		String date = "15-08-2021";
		String bno = "15-08-2021-1";
		int  roomNo = 1;
		//public BookingBO(String bookingNo, String custName, int roomNo, String bookingDate) {
		BookingBO bo = new BookingBO(bno, name,roomNo,date);
		List<BookingBO> expectedlist = new ArrayList<>();
		expectedlist.add(bo);
		
		services.bookRoom("renga","15-08-2021");
		List<BookingBO> res = services.checkGuest("renga");
		assert(expectedlist.equals(res));
	}
	
	
	@Test
	void testCheckGuestFails() {
		List<BookingBO> expectedlist = new ArrayList<>(); //empty
		services.bookRoom("renga","15-08-2021");
		List<BookingBO> res = services.checkGuest("rengaaaaa"); //should be empty
		assert(expectedlist.equals(res));
	}
	
}
