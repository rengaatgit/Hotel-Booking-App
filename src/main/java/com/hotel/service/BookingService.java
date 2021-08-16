/*
 * BookingService
 * Developer: Rengaraj Arumugam
 * 
 */
package com.hotel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hotel.model.BookingBO;
import com.hotel.model.NoOfRooms;

@Service
@Scope(value = "singleton")
public class BookingService {
	@Autowired
	private NoOfRooms noOfRooms;

	private List<BookingBO> bookList = new ArrayList<BookingBO>();
	
	public BookingService() {
		super();
	}

	public BookingService(List<BookingBO> bookList, NoOfRooms noOfRooms) {
		super();
		this.bookList = bookList;
		this.noOfRooms = noOfRooms;
	}
	
	public synchronized void setNoOfRooms(String cnt) {
		this.noOfRooms.setRoomCount(Integer.parseInt(cnt));
	}
	
	public int getNoOfRooms() {
		return noOfRooms.getRoomCount();
	}
	
	public synchronized String bookRoom(String custName, String bookingDate){
		 int currentBookingCount = checkBookedRooms(bookingDate);
		 String bno = "null";
		 if(currentBookingCount < getNoOfRooms() ) {
		    BookingBO bo = new BookingBO();
			bo.setBookingDate(bookingDate);
	        bo.setCustName(custName);
	        bo.setRoomNo(currentBookingCount+1);
	        bno= bookingDate+"-"+bo.getRoomNo();
			bo.setBookingNo(bno);
	        bookList.add(bo);
		 }
		 return bno;
	}
	
	public int checkBookedRooms(String date) {
		return (int)bookList.stream().filter(b -> date.equals(b.getBookingDate())).count();
	}
	
	public int checkAvailableRooms(String date) {
		return getNoOfRooms()-checkBookedRooms(date);
		
	}
	
	public List<BookingBO> checkGuest(String name) {
	  return bookList.stream().filter(b -> name.equals(b.getCustName())).collect(Collectors.toList());
		
	}
	

}
