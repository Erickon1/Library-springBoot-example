package mx.erick.library;


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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestClientException;

import mx.erick.library.controller.HelloController;
import mx.erick.library.service.RestClient;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//Wrong config for wrong test, never use it
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) 
@AutoConfigureMockMvc
public class HelloControllerClientTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	RestClient restClient;
	
	@InjectMocks
	HelloController helloController;
	
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
	/* test this test 
	@Test
	public void errorByException() throws Exception {
		
		when( restClient.get("/posts/1") ).thenThrow(
				new RestClientException("client.error")) ;
		
		MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get("/")
				).andExpect( status().isBadRequest() )
				.andReturn();
		String content = result.getResponse().getContentAsString();
		assertEquals("Error", content);
	}*/
	
	/* Wrong test, never use it
	  
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void retrieveError() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
				String.class)).contains("Error");
	}
	*/
	
	
}
