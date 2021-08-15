package com.hotel.bookingApp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.model.BookingBO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingAppApplicationTests {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Before
	public void setUp()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@org.junit.Test
	public void bookRoomTest() throws Exception {
		BookingBO bo = new BookingBO();
		bo.setBookingDate("15-08-2021");
		bo.setCustName("Renga");
	
	String JsonRequest = objectMapper.writeValueAsString(bo);
	
	MvcResult result = mockMvc.perform(post("/user/addUser").content(JsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk()).andReturn();
	
	String resultContext = result.getResponse().getContentAsString();
	
	Response response = objectMapper.readValue(resultContext, Response.class);
	
	Assert.assertTrue(response.isStatus() == Boolean.TRUE);
		
	}
	
	@org.junit.Test
	public void getUserTest() throws Exception {
	
	MvcResult result = mockMvc.perform(get("/user/getUsers").contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk()).andReturn();
	
	String resultContext = result.getResponse().getContentAsString();
	
	Response response = objectMapper.readValue(resultContext, Response.class);
	
	Assert.assertTrue(response.isStatus() == Boolean.TRUE);
		
	}	
	

}