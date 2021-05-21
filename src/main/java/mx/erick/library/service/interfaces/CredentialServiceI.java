package mx.erick.library.service.interfaces;

import java.util.List;

import mx.erick.library.domain.Credential;
import mx.erick.library.dto.CredentialDto;

public interface CredentialServiceI {
	
	public List<Credential> getAll();

	public Credential save(CredentialDto dto);

	public Credential get(Long id);

	public Credential update(Long id, CredentialDto dto);

	public void delete(Long id);
	
}
