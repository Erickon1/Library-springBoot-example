package mx.erick.library.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.erick.library.domain.Author;

@Repository
public interface AuthorR extends JpaRepository<Author, Long> {

	public List<Author> findAll();

}
