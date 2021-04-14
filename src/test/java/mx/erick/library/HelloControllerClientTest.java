package mx.erick.library;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
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

@ExtendWith(SpringExtension.class)
//@SpringBootTest
//Wrong config for wrong test, never use it
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) 
@AutoConfigureMockMvc
public class HelloControllerClientTest {
	
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
	
	@Test
	public void happyPath() throws Exception {
		
		when( restClient.get("/posts/1") ).thenReturn("Hello") ;
		
		MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get("/")
				).andExpect( status().isOk() )
				.andReturn();
		String content = result.getResponse().getContentAsString();
		assertEquals("Hello, World", content);
	}
	
	@Test
	public void errorByEmpty() throws Exception {
		
		when( restClient.get("/posts/1") ).thenReturn("") ;
		
		MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get("/")
				).andExpect( status().isBadRequest() )
				.andReturn();
		String content = result.getResponse().getContentAsString();
		assertEquals("Error", content);
	}
	
	@Test
	public void errorByNull() throws Exception {
		
		when( restClient.get("/posts/1") ).thenReturn(null) ;
		
		MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get("/")
				).andExpect( status().isBadRequest() )
				.andReturn();
		String content = result.getResponse().getContentAsString();
		assertEquals("Error", content);
	}
	
	/*
	@SuppressWarnings("unchecked")
	@Test
	public void errorByException() throws Exception {
		
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
	
	/* Wrong test, never use it
	  
	@LocalServerPort
	private int port;

	
	@Test
	public void retrieveError() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
				String.class)).contains("Error");
	}
	*/
	
	
}
