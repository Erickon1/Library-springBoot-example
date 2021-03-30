package mx.erick.library.service.interfaces;

import java.util.List;

import mx.erick.library.domain.Author;
import mx.erick.library.dto.AuthorDto;

public interface AuthorServiceI {

	public List<Author> getAll();

	public Author save(AuthorDto dto);

	public Author get(Long id);

	public Author update(Long id, AuthorDto dto);

	public void delete(Long id);
	
}
