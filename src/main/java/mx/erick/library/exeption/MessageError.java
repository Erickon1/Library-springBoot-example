package mx.erick.library.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageError {
	
	private String message;
	private String debugMessage;

}
