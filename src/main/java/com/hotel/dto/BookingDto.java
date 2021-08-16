/*
 * BookingDto
 * Developer: Rengaraj Arumugam
 * 
 */
package com.hotel.dto;

import org.springframework.stereotype.Component;

@Component
public class BookingDto {
	
	private String custName;
	private String bookingDate;
	
	public BookingDto(String custName, String bookingDate) {
		super();
		this.custName = custName;
		this.bookingDate = bookingDate;
	}
	
	public BookingDto() {
		super();
	}
	
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
}