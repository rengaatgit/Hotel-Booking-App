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

import com.hotel.constants.BookingConstants;
import com.hotel.model.BookingBO;
import com.hotel.model.NoOfRooms;
import com.hotel.utility.BookingUtil;

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

	public String setNoOfRooms(String cnt) {
		String errorMsg = BookingConstants.NO_ERROR;
		try {
			int roomCnt = Integer.parseInt(cnt);
			synchronized (this.noOfRooms) {
				this.noOfRooms.setRoomCount(roomCnt);
			}
		} catch (NumberFormatException e) {
			errorMsg = BookingConstants.E0001;
			e.printStackTrace();
		} catch (Exception e) {
			errorMsg = BookingConstants.E0002;
			e.printStackTrace();
		}
		System.out.println("setNoOfRooms() returnVal: "+errorMsg);
		return errorMsg;

	}

	public int getNoOfRooms() {
		return noOfRooms.getRoomCount();
	}

	public List<String> bookRoom(String custName, String bookingDate) {
		List<String> responseList = new ArrayList<String>();
		String dateCheckStr = BookingUtil.validateDate(bookingDate);
		String nameCheckStr = BookingUtil.validateName(custName);
		if (BookingConstants.NO_ERROR.equals(dateCheckStr) && BookingConstants.NO_ERROR.equals(nameCheckStr)){
			synchronized (this) {
				int currentBookingCount = checkBookedRooms(bookingDate);
				if (currentBookingCount < getNoOfRooms()) {
					BookingBO bo = new BookingBO();
					bo.setBookingDate(bookingDate);
					bo.setCustName(custName);
					bo.setRoomNo(currentBookingCount + 1);
					String bno = bookingDate + "-" + bo.getRoomNo();
					bo.setBookingNo(bno);
					bookList.add(bo);
					responseList.add(bno);
				}else {
					responseList.add(BookingConstants.NO_ERROR);
				}
			}
		}else {
			if(!BookingConstants.NO_ERROR.equals(dateCheckStr))
				responseList.add(dateCheckStr);
			if(!BookingConstants.NO_ERROR.equals(nameCheckStr))
				responseList.add(nameCheckStr);
		}
		return responseList;
	}

	public int checkBookedRooms(String date) {
		return (int) bookList.stream().filter(b -> date.equals(b.getBookingDate())).count();
	}

	public String checkAvailableRooms(String bookingDate) {
		String returnVal = BookingUtil.validateDate(bookingDate);
		if (BookingConstants.NO_ERROR.equals(returnVal)) {
			returnVal = String.valueOf(getNoOfRooms()-checkBookedRooms(bookingDate));
		}
		return returnVal;
	}

	public List checkGuest(String name) {
		List returnVal = new ArrayList();
		String res = BookingUtil.validateName(name);
		if (BookingConstants.NO_ERROR.equals(res)) {
			List filterVal= bookList.stream().filter(b -> name.equals(b.getCustName())).collect(Collectors.toList());
			if(filterVal == null || filterVal.isEmpty()) {
				returnVal.add(BookingConstants.I0002 + name);
			}else {
				returnVal.addAll(filterVal);
			}
		}else {
			returnVal.add(res);
		}
		return returnVal;
	}

}
