package mx.erick.library.service.interfaces;

import java.util.List;

import mx.erick.library.domain.User;
import mx.erick.library.dto.UserDto;

public interface UserServiceI {
	
	public List<User> getAll();

	public User save(UserDto dto);

	public User get(Long id);

	public User update(Long id, UserDto dto);

	public void delete(Long id);
	
}
