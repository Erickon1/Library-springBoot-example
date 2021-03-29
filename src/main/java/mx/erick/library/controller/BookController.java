package mx.erick.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mx.erick.library.domain.Book;
import mx.erick.library.dto.BookDto;
import mx.erick.library.service.interfaces.BookServiceI;

@RestController
public class BookController {
	
	@Autowired
	BookServiceI bookService;

	@GetMapping("/book")
	public ResponseEntity<List<Book>> index(){
		return new ResponseEntity<>( bookService.getAll(), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/book")
	public ResponseEntity<Book> save(@RequestBody BookDto dto){
		return new ResponseEntity<>( bookService.save(dto), HttpStatus.CREATED);
	}
	
	
}
