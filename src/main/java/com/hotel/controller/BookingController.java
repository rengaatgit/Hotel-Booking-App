package com.hotel.controller;

import java.util.ArrayList;
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

import com.hotel.constants.BookingConstants;
import com.hotel.dto.BookingDto;
import com.hotel.service.BookingService;

@RestController
@RequestMapping("api/v1.0/hotel/booking")
public class BookingController {
	
	 @Autowired
	 private BookingService services;
	 
		/*
		 * @GetMapping("/") 
		 * public String checkApiCall() {
		 *  return "This is Booking App testing API !"; 
		 *  }
		 */
	 
	    @ResponseStatus(HttpStatus.OK)
	    @PostMapping(value="/setRoomCount", consumes = MediaType.APPLICATION_JSON_VALUE)
	    public String setNoOfRooms(@RequestBody String roomCount) {
	    	String errorMsg = this.services.setNoOfRooms(roomCount);
	    	if(BookingConstants.NO_ERROR.equals(errorMsg)) {
	    		return "Now "+services.getNoOfRooms()+BookingConstants.I0001;
	    	}else {
	    		return errorMsg;
	    	}
	    }
	     
	     @ResponseStatus(HttpStatus.OK)
		 @GetMapping("/getroomcount")
		 public String getNoOfRooms() {
			 return this.services.getNoOfRooms()+BookingConstants.I0001;
		 }
		 
	    @ResponseStatus(HttpStatus.OK)
	    @PostMapping(value="/bookroom", consumes = MediaType.APPLICATION_JSON_VALUE)
	    public List<String> bookRoom(@RequestBody BookingDto bookingDto) {
	    	List<String> responseList = new ArrayList<String>();
	        List<String> responseMsg = this.services.bookRoom(bookingDto.getCustName(), bookingDto.getBookingDate());
	     if(responseMsg.get(0).startsWith(BookingConstants.ERROR_CHECK)) {
	    	 responseList.addAll(responseMsg);
	     }else if(BookingConstants.NO_ERROR.equals(responseMsg.get(0))){	 
	    	 responseList.add("Room is not available on "+bookingDto.getBookingDate());
		 }else {
			 responseList.add("Here is your booking ref no "+responseMsg);
		 }
	     
	     return responseList;
	    }
	     
		@ResponseStatus(HttpStatus.OK)
		@GetMapping("/checkdate/{date}")
		public String checkDate(@PathVariable String date) {
			String responseMsg = this.services.checkAvailableRooms(date);
			if (responseMsg.startsWith(BookingConstants.ERROR_CHECK)) {
				return responseMsg;
			} else {
				return responseMsg + " rooms are available for bookings on " + date;
			}
		}
		 
	     @ResponseStatus(HttpStatus.OK)
		 @GetMapping("/checkguest/{name}")
		 public List checkGuest(@PathVariable String name) {
	    	 return this.services.checkGuest(name);

		 }
	     

}
