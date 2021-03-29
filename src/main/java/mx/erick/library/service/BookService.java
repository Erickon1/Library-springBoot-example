package mx.erick.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.erick.library.domain.Book;
import mx.erick.library.domain.repository.BookR;
import mx.erick.library.dto.BookDto;
import mx.erick.library.service.interfaces.BookServiceI;

@Service
public class BookService implements BookServiceI {
	
	@Autowired
	BookR repository;

	@Override
	public List<Book> getAll() {
		return repository.findAll();
	}

	@Override
	public Book save(BookDto dto) {
		Book item = Book.builder()
				.name( (dto.getName() !=null && !dto.getName().isEmpty() )?dto.getName():"empty" )
				.build();
		return repository.save(item);
	}

}
