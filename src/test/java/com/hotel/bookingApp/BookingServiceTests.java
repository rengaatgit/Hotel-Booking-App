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

import com.hotel.constants.BookingConstants;
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
	void testBookRooms() {
		List<String> expectedlist = new ArrayList<>(); //empty
		expectedlist.add("15-08-2021-1");
		List<String> res = services.bookRoom("renga","15-08-2021");
		assertEquals(expectedlist,res);
	}
	
	@Test
	void testBookRoomsFails() {
		List<String> expectedlist = new ArrayList<>(); //empty
		expectedlist.add(BookingConstants.NO_ERROR);
		services.bookRoom("renga","15-08-2021");
		List<String> res = services.bookRoom("renga","15-08-2021");
		assertEquals(expectedlist,res);
	}

	
	@Test
	void testBookRoomsFails2() {
		List<String> expectedlist = new ArrayList<>(); //empty
		expectedlist.add(BookingConstants.E0003);
		List<String> res = services.bookRoom("renga","35-08-2021"); //invalid date
		assertEquals(expectedlist,res);
	}
	
	@Test
	void testBookRoomsFails3() {
		List<String> expectedlist = new ArrayList<>(); //empty
		expectedlist.add(BookingConstants.E0006);
		List<String> res = services.bookRoom("SC@BANK","30-08-2021"); //invalid name
		assertEquals(expectedlist,res);
	}
	
	
	@Test
	void testBookRoomsFails4() {
		List<String> expectedlist = new ArrayList<>(); //empty
		expectedlist.add(BookingConstants.E0003);
		expectedlist.add(BookingConstants.E0004);
		List<String> res = services.bookRoom(null,null); //invalid name & date
		assertEquals(expectedlist,res);
	}
	
	
	@Test
	void testBookRoomsFails5() {
		List<String> expectedlist = new ArrayList<>(); //empty
		expectedlist.add(BookingConstants.E0003);
		expectedlist.add(BookingConstants.E0004);
		List<String> res = services.bookRoom("",""); //invalid name & date
		assertEquals(expectedlist,res);
	}
	
	@Test
	void testCheckBookedRooms() {
		int res = services.checkBookedRooms("15-08-2021");
		assertEquals(0,res);
	}
	
	
	@Test
	void testCheckAvailableRooms() {
		String res = services.checkAvailableRooms("15-08-2021");
		assertEquals("1",res);
	}
	
	
	@Test
	void testCheckAvailableRoomsFails() {
		String res = services.checkAvailableRooms("35-08-2021"); //invalid date
		assertEquals(BookingConstants.E0003,res);
	}
	
	
	@Test
	void testCheckAvailableRoomsFails2() {
		String res = services.checkAvailableRooms(""); //invalid date
		assertEquals(BookingConstants.E0003,res);
	}
	
	@Test
	void testCheckAvailableRoomsFails3() {
		String res = services.checkAvailableRooms("ABCD "); //invalid date
		assertEquals(BookingConstants.E0003,res);
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
		String testName = "rengaaaaa";
		List expectedlist = new ArrayList<>(); //empty
		expectedlist.add(BookingConstants.I0002 + testName);
		services.bookRoom("renga","15-08-2021");
		List res = services.checkGuest(testName); //should be empty
		assert(expectedlist.equals(res));
	}
	
	@Test
	void testCheckGuestFails1() {
		String testName = null;
		List expectedlist = new ArrayList<>(); //empty
		expectedlist.add(BookingConstants. E0004);
		services.bookRoom("renga","15-08-2021");
		List res = services.checkGuest(testName); //should be empty
		assert(expectedlist.equals(res));
	}
	
	@Test
	void testCheckGuestFails2() {
		String testName = "scb1";
		List expectedlist = new ArrayList<>(); //empty
		expectedlist.add(BookingConstants. E0006);
		services.bookRoom("renga","15-08-2021");
		List res = services.checkGuest(testName); //should be empty
		assert(expectedlist.equals(res));
	}
	
}
