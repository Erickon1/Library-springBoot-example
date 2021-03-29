package mx.erick.library.service.interfaces;

import java.util.List;

import mx.erick.library.domain.Book;
import mx.erick.library.dto.BookDto;

public interface BookServiceI {

	public List<Book> getAll();

	public Book save(BookDto dto);
}
