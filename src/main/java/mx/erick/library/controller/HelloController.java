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
			@RequestParam(value = "name", required = true) String name ) {
		return new ResponseEntity<>( helloService.grettings(name,age), HttpStatus.ACCEPTED );
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<String> client() {
		return new ResponseEntity<>( restClient.get("/posts/1"), HttpStatus.OK );
	}

}
