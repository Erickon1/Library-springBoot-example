package mx.erick.library.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class AuthorDto {
	
	@NotNull(message = "NotNull.author.name")
	@NotEmpty(message = "NotEmpty.author.name")
	public String name;
	
	public AuthorDto(){}

}
