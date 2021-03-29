package mx.erick.library.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class BookDto {
	
	public String name;
	
	public BookDto(){}

}
