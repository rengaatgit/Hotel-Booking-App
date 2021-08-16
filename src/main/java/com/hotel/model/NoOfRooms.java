/*
 * NoOfRooms
 * Developer: Rengaraj Arumugam
 * 
 */
package com.hotel.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "singleton")
public class NoOfRooms {
	
	@Value("${DEFAULT_NO_OF_ROOMS}")
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
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    NoOfRooms that = (NoOfRooms) o;
	    return noOfRooms==that.noOfRooms;
	}
	
}
