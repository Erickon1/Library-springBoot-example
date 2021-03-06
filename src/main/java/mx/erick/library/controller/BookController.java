package mx.erick.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.erick.library.domain.Book;
import mx.erick.library.dto.BookDto;
import mx.erick.library.service.interfaces.BookServiceI;

@RestController
@RequestMapping("book")
public class BookController {
	
	@Autowired
	BookServiceI bookService;

	@GetMapping()
	public ResponseEntity<List<Book>> index(){
		return new ResponseEntity<>( bookService.getAll(), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> show(
			@PathVariable Long id ){
		return new ResponseEntity<>( bookService.get(id), HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Book> save(@RequestBody BookDto dto){
		return new ResponseEntity<>( bookService.save(dto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Book> update(
			@PathVariable Long id, @RequestBody BookDto dto ){
		return new ResponseEntity<>( bookService.update(id, dto), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete( @PathVariable Long id ){
		bookService.delete(id);
		return new ResponseEntity<>( "Book was deleted.", HttpStatus.ACCEPTED);
	}
	
}
