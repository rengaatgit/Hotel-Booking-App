package com.hotel.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "singleton")
public class NoOfRooms {

	private int noOfRooms; 
	
	public NoOfRooms(int noOfRooms) {
		super();
		this.noOfRooms = noOfRooms;
	}

	public NoOfRooms() {
		super();
	}

	public void setRoomCount(int cnt) {
		this.noOfRooms = cnt;
	}
	
	public int getRoomCount() {
		return this.noOfRooms;
	}
	
	
}
