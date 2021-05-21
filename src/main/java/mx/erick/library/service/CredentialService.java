package mx.erick.library.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.erick.library.domain.Credential;
import mx.erick.library.domain.User;
import mx.erick.library.domain.repository.CredentialR;
import mx.erick.library.dto.CredentialDto;
import mx.erick.library.service.interfaces.CredentialServiceI;
import mx.erick.library.service.interfaces.UserServiceI;

@Service
public class CredentialService implements CredentialServiceI{

	@Autowired
	CredentialR repository;
	
	@Autowired
	UserServiceI userService;
	
	@Override
	public List<Credential> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Credential save(CredentialDto dto) {
		User user = userService.get(dto.getUserId());
		UUID uuid = UUID.randomUUID();
		Credential item = Credential.builder()
				.accountNumber(uuid.toString())
				.user(user)
				.build();
		return repository.save(item);
	}

	@Override
	public Credential get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Credential update(Long id, CredentialDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
