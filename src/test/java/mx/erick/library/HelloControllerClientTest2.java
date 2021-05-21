package mx.erick.library;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestClientException;

import mx.erick.library.controller.HelloController;
import mx.erick.library.exeption.ErrorDetails;
import mx.erick.library.service.RestClient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@ExtendWith(SpringExtension.class)
//@SpringBootTest
//Wrong config for wrong test, never use it
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) 
@AutoConfigureMockMvc
public class HelloControllerClientTest2 {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	RestClient restClient;
	
	@InjectMocks
	HelloController helloController;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@BeforeEach
	public void setup() {
		Mockito.reset(restClient);
		this.mockMvc = MockMvcBuilders.standaloneSetup(
				helloController).build();
		ReflectionTestUtils.setField(
				helloController, "restClient", restClient);
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void errorByException() throws Exception {
	
		Object res = this.restTemplate.getForObject("/?uri=exception",
				Object.class);
		System.out.println(res);
		
		//assertThat(res.getBody().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
	
	/*
		 when( restClient.get("/?uri=exception") ).thenThrow(
					new RestClientException("client.error")) ;
		 
		mockMvc.perform(get("/?uri=exception")
		      .contentType(MediaType.APPLICATION_JSON))
			  .andDo(print())
		      .andExpect(status().isBadRequest())
		      .andExpect(result -> assertTrue(
		    		  result.getResolvedException() instanceof RestClientException))
		      .andExpect(result -> assertEquals("resource not found",
		    		  result.getResolvedException().getMessage()));
	}
	*/
	/*
		//init comment
		 when( restClient.get("/posts/1") ).thenThrow(
				new RestClientException("client.error")) ;
		
		MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get("/")
				).andExpect( status().isBadRequest() )
				.andReturn();
		String content = result.getResponse().getContentAsString();
		assertEquals("Error", content);
		
		
		//end comment
		ResponseEntity<ErrorDetails> res = this.restTemplate.getForObject("/?uri=exception",
				ResponseEntity.class);
		System.out.println(res);
		
		assertThat(res.getBody().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
	*/
	
	
}
