package com.hotel.dto;

import org.springframework.stereotype.Component;

@Component
public class NoOfRoomsDto {
	
	public NoOfRoomsDto(String noOfRooms) {
		super();
		this.noOfRooms = noOfRooms;
	}
	
	public NoOfRoomsDto() {
		super();
		
	}

private String noOfRooms;
	
	public void setRoomCount(String noOfRooms) {
		this.noOfRooms = noOfRooms;
	}
	
	public String getRoomCount() {
		return this.noOfRooms;
	}

}
