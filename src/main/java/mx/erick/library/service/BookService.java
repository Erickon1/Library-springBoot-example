package mx.erick.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.erick.library.domain.Author;
import mx.erick.library.domain.Book;
import mx.erick.library.domain.repository.BookR;
import mx.erick.library.dto.BookDto;
import mx.erick.library.service.interfaces.AuthorServiceI;
import mx.erick.library.service.interfaces.BookServiceI;

@Service
public class BookService implements BookServiceI {
	
	@Autowired
	BookR repository;
	
	@Autowired
	AuthorServiceI authorService;

	@Override
	public List<Book> getAll() {
		return repository.findAll();
	}

	@Override
	public Book save(BookDto dto) {
		Author author = authorService.get(dto.getAuthorId());
		Book item = Book.builder()
				.author(author)
				.name( (dto.getName() !=null && !dto.getName().isEmpty() )?dto.getName():"empty" )
				.build();
		return repository.save(item);
	}

	@Override
	public Book get(Long id) {
		
		Optional<Book> opt = repository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
		
	}

	@Override
	public Book update(Long id, BookDto dto) {
		Optional<Book> opt = repository.findById(id);
		if( opt.isPresent() ) {
			Book item = opt.get();
			item.setName( (dto.getName() !=null && 
					!dto.getName().isEmpty() )?dto.getName(): item.getName()  );
			return repository.save(item);
		}	
		return null;
	}

	@Override
	public void delete(Long id) {
		Optional<Book> opt = repository.findById(id);
		if( opt.isPresent() ) {
			repository.delete( opt.get() );
		}		
	}

}
