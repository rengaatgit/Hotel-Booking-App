
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
	
	List<BookingBO> bookList = new ArrayList<BookingBO>();
	
	@Autowired
	private NoOfRooms noOfRooms;
	
	public synchronized void setNoOfRooms(int cnt) {
		this.noOfRooms.setRoomCount(cnt);
	}
	
	public int getNoOfRooms() {
		return noOfRooms.getRoomCount();
	}
	
	public synchronized String bookRoom(String custName, String bookingDate){
		
		 int currentBookingCount = checkBookedRooms(bookingDate);
		 String bno = "";
		 if(currentBookingCount < getNoOfRooms() ) {
		    BookingBO bo = new BookingBO();
			
			bo.setBookingDate(bookingDate);
	        bo.setCustName(custName);
	        bo.setRoomNo(currentBookingCount+1);
	        bno= bookingDate+"-"+bo.getRoomNo();
			bo.setBookingNo(bno);
	        bookList.add(bo);
	        
	        System.out.println(bookList.get(0).toString());
	        return "Here is your booking ref no "+bno;
		 }else {
			return "Room is not available on "+bookingDate;
		 }
		 
		 
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
