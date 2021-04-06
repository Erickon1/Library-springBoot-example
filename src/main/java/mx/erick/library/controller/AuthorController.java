package mx.erick.library.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.erick.library.domain.Author;
import mx.erick.library.dto.AuthorDto;
import mx.erick.library.service.interfaces.AuthorServiceI;

@RestController
@RequestMapping("author")
public class AuthorController {
	
	@Autowired
	AuthorServiceI service;
	
	@GetMapping()
	public ResponseEntity<List<Author>> index(){
		return new ResponseEntity<>( service.getAll(), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Author> show(
			@PathVariable Long id ){
		return new ResponseEntity<>( service.get(id), HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Author> save(@Valid @RequestBody AuthorDto dto){
		return new ResponseEntity<>( service.save(dto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Author> update(
			@PathVariable Long id, @RequestBody AuthorDto dto ){
		return new ResponseEntity<>( service.update(id, dto), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete( @PathVariable Long id ){
		service.delete(id);
		return new ResponseEntity<>( "Author was deleted.", HttpStatus.ACCEPTED);
	}

}
