package mx.erick.library.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.erick.library.domain.User;

@Repository
public interface UserR extends JpaRepository<User, Long>{
	
}
