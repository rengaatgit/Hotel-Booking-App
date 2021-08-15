package com.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.dto.BookingDto;
import com.hotel.model.BookingBO;
import com.hotel.service.BookingService;

@RestController
@RequestMapping("api/v1.0/hotel/booking")
public class BookingController {
	
	 @Autowired
	 private BookingService services;
	 
	 @GetMapping("/")
		public String checkApiCall() {
			return "This is Booking App testing API !";
		}
	 
	    @ResponseStatus(HttpStatus.OK)
	    @PostMapping(value="/setRoomCount", consumes = MediaType.APPLICATION_JSON_VALUE)
	    public String setNoOfRooms(@RequestBody String roomCount) {
	    	this.services.setNoOfRooms(Integer.parseInt(roomCount));
	        return "Now "+services.getNoOfRooms()+" rooms/day are available for bookings";
	    }
	     
	     @ResponseStatus(HttpStatus.OK)
		 @GetMapping("/getroomcount")
		 public String getNoOfRooms() {
			 return this.services.getNoOfRooms()+" rooms/day are available for bookings";
		 }
		 
	     @ResponseStatus(HttpStatus.OK)
		 @GetMapping("/checkdate/{date}")
		 public String checkDate(@PathVariable String date) {
			 return this.services.checkAvailableRooms(date)+" rooms are available for bookings on "+date;
		 }
		 
	     @ResponseStatus(HttpStatus.OK)
		 @GetMapping("/checkguest/{name}")
		 public List<BookingBO> checkGuest(@PathVariable String name) {
			 return this.services.checkGuest(name);
		 }
		 
	    @ResponseStatus(HttpStatus.OK)
	    @PostMapping(value="/bookroom", consumes = MediaType.APPLICATION_JSON_VALUE)
	    public String bookRoom(@RequestBody BookingDto bookingDto) {
	        return this.services.bookRoom(bookingDto.getCustName(), bookingDto.getBookingDate());
	    }
		 
}
