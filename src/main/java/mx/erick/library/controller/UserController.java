package mx.erick.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.erick.library.domain.User;
import mx.erick.library.dto.UserDto;
import mx.erick.library.service.interfaces.UserServiceI;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserServiceI userService;
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody UserDto dto){
		return new ResponseEntity<>( userService.save(dto), HttpStatus.CREATED);
	}
	
}
