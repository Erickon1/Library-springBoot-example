package mx.erick.library.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.erick.library.domain.Book;

@Repository
public interface BookR extends JpaRepository<Book, Long> {
	
	public List<Book> findAll();

}
