package mx.erick.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.erick.library.domain.User;
import mx.erick.library.domain.repository.UserR;
import mx.erick.library.dto.UserDto;
import mx.erick.library.service.interfaces.UserServiceI;

@Service
public class UserService implements UserServiceI{

	@Autowired
	UserR repository;
	
	
	@Override
	public List<User> getAll() {
		return repository.findAll();
	}

	@Override
	public User save(UserDto dto) {
		User user = User.builder()
				.name(dto.getName())
				.email(dto.getEmail())
				.build();
		return repository.save(user);
	}

	@Override
	public User get(Long id) {
		Optional<User> op = repository.findById(id);
		if (op.isPresent()) {
			return op.get();
		}
		return null;
	}

	@Override
	public User update(Long id, UserDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
