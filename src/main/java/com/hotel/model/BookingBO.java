/*
 * BookingBO
 * Developer: Rengaraj Arumugam
 * 
 */
package com.hotel.model;

import org.springframework.stereotype.Component;

@Component
public class BookingBO {
	
	private String bookingNo;
	private String custName;
	private int roomNo;
	private String bookingDate;
	
	public BookingBO(String custName, String bookingDate) {
		super();
		this.custName = custName;
		this.bookingDate = bookingDate;
	}
	public BookingBO(String bookingNo, String custName, int roomNo, String bookingDate) {
		super();
		this.bookingNo = bookingNo;
		this.custName = custName;
		this.roomNo = roomNo;
		this.bookingDate = bookingDate;
	}

	public BookingBO() {
		super();
	}

	
	public String getBookingNo() {
		return bookingNo;
	}
	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    BookingBO that = (BookingBO) o;
	    return bookingDate.equals(that.bookingDate) && roomNo==that.roomNo && custName.equals(that.custName) && bookingNo.equals(that.bookingNo);
	}
}