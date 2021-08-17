/*
 * BookingUtilTests
 * Developer: Rengaraj Arumugam
 * 
 */
package com.hotel.bookingApp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotel.constants.BookingConstants;
import com.hotel.model.BookingBO;
import com.hotel.utility.BookingUtil;


@ExtendWith(MockitoExtension.class)
class BookingUtilTests {

   @Test
	void testValidateDate() {
		String date = "15-08-2021";		
		String res =  BookingUtil.validateDate(date);
		assertEquals(BookingConstants.NO_ERROR,res);
	}
   
   @Test
	void testValidateDate2() {
		String date = "31-12-2030";		
		String res =  BookingUtil.validateDate(date);
		assertEquals(BookingConstants.NO_ERROR,res);
	}
	
   @Test
	void testValidateDate3() {
		String date = "29-02-2024";		
		String res =  BookingUtil.validateDate(date);
		assertEquals(BookingConstants.NO_ERROR,res);
	}
   
   @Test
	void testValidateDateFails1() {
		String date = "35-08-2021";		
		String res =  BookingUtil.validateDate(date);
		assertEquals(BookingConstants.E0003,res);
	}
   
   @Test
  	void testValidateDateFails2() {
  		String date = "ABCD";		
  		String res =  BookingUtil.validateDate(date);
  		assertEquals(BookingConstants.E0003,res);
  	}
   
   @Test
 	void testValidateDateFails3() {
 		String date = "15-13-2021";		
 		String res =  BookingUtil.validateDate(date);
 		assertEquals(BookingConstants.E0003,res);
 	}
   
   @Test
	void testValidateDateFails4() {
		String date = " 15-13-2021";		
		String res =  BookingUtil.validateDate(date);
		assertEquals(BookingConstants.E0003,res);
	}
   
   
   @Test
  	void testvalidateName2() {
  		String name = "scb";		
  		String res =  BookingUtil.validateName(name);
  		assertEquals(BookingConstants.NO_ERROR,res);
  	}
  	
    @Test
  	void testvalidateName1() {
  		String name = "sc bank";		
  		String res =  BookingUtil.validateName(name);
  		assertEquals(BookingConstants.NO_ERROR,res);
  	}
    
    @Test
   	void testvalidateName3() {
   		String name = "SC Bank";		
   		String res =  BookingUtil.validateName(name);
   		assertEquals(BookingConstants.NO_ERROR,res);
   	}
    
    @Test
  	void testvalidateName4() {
  		String name = "SC BANK";		
  		String res =  BookingUtil.validateName(name);
  		assertEquals(BookingConstants.NO_ERROR,res);
  	}
    
    @Test
  	void testvalidateName5() {
  		String name = "S C Bank";		
  		String res =  BookingUtil.validateName(name);
  		assertEquals(BookingConstants.NO_ERROR,res);
  	}
    
    @Test
  	void testvalidateNameFails1() {
  		String name = "SCB1";		
  		String res =  BookingUtil.validateName(name);
  		assertEquals(BookingConstants.E0006,res);
  	}
    
    @Test
  	void testvalidateNameFails2() {
  		String name = "SCB@";		
  		String res =  BookingUtil.validateName(name);
  		assertEquals(BookingConstants.E0006,res);
  	}
    
    @Test
  	void testvalidateNameFails3() {
  		String name = "ABCDABCDABCDABCDABCDABCDABCDABCDABCDABCDABCDABCDABCD";	//52 char	
  		String res =  BookingUtil.validateName(name);
  		assertEquals(BookingConstants.E0005,res);
  	}
    
    @Test
  	void testvalidateNameFails4() {
  		String name = " ";		//space
  		String res =  BookingUtil.validateName(name);
  		assertEquals(BookingConstants.E0004,res);
  	}
}
