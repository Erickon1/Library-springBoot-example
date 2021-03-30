package mx.erick.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.erick.library.domain.Author;
import mx.erick.library.domain.repository.AuthorR;
import mx.erick.library.dto.AuthorDto;
import mx.erick.library.service.interfaces.AuthorServiceI;

@Service
public class AuthorService implements AuthorServiceI {
	
	@Autowired
	AuthorR repository;

	
	@Override
	public List<Author> getAll() {
		return repository.findAll();
	}

	@Override
	public Author save(AuthorDto dto) {
		Author item = Author.builder()
				.name( (dto.getName() !=null && !dto.getName().isEmpty() )?dto.getName():"empty" )
				.build();
		return repository.save(item);
	}

	@Override
	public Author get(Long id) {
		
		Optional<Author> opt = repository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

	@Override
	public Author update(Long id, AuthorDto dto) {
		Optional<Author> opt = repository.findById(id);
		if( opt.isPresent() ) {
			Author item = opt.get();
			item.setName( (dto.getName() !=null && 
					!dto.getName().isEmpty() )?dto.getName(): item.getName()  );
			return repository.save(item);
		}	
		return null;
	}

	@Override
	public void delete(Long id) {
		Optional<Author> opt = repository.findById(id);
		if( opt.isPresent() ) {
			repository.delete( opt.get() );
		}		
	}

}
