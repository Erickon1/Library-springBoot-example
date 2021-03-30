package mx.erick.library.service.interfaces;

import java.util.List;

import mx.erick.library.domain.Book;
import mx.erick.library.dto.BookDto;

public interface BookServiceI {

	public List<Book> getAll();

	public Book save(BookDto dto);

	public Book get(Long id);

	public Book update(Long id, BookDto dto);

	public void delete(Long id);
}
