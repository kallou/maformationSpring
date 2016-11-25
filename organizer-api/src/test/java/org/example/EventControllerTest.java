package org.example;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.json.Json;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

public class EventControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	
   @Test
   public void test() throws Exception {
	   String request  = Json.createObjectBuilder()
			   	.add("description", 	"description dans test")
			   	.add("beginDateTime", 	"2016-11-20T15:00:00")
			   	.add("endDateTime", 	"2016-11-20T16:00:00")
			   	.build()
			   	.toString();
			   	
	  
	   ResultActions result = mockMvc.perform(
			   post("/events")
			   .content(request)
			   .contentType(MediaType.APPLICATION_JSON));
	   
	   result.andDo(print());
	   result.andExpect(status().is2xxSuccessful());
	  // result.andExpect(jsonPath("$.description", is("description dans test"));	   
			   
	   
    }
}
