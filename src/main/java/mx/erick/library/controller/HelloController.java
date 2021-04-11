package mx.erick.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.erick.library.service.RestClient;
import mx.erick.library.service.interfaces.HelloServiceI;

@RestController
public class HelloController {
	
	@Autowired
	HelloServiceI helloService;
	
	@Autowired
	RestClient restClient;
	
	
	@GetMapping(value = "/{age}")
	public ResponseEntity<String> grettings(
			@PathVariable("age") int age,
			@RequestParam(value = "name", required = true) String name ) 
					throws Exception {
		return new ResponseEntity<>( helloService.grettings(name,age), HttpStatus.ACCEPTED );
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<String> client() {
		String response = restClient.get("/posts/1");
		System.out.println(response);
		
		if (response == null || response.isEmpty()) {
			return new ResponseEntity<>( "Error" , HttpStatus.BAD_REQUEST );
		}else {
			return new ResponseEntity<>( "Hello, World" , HttpStatus.OK );
		}
	}

}
