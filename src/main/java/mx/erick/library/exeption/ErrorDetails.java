package mx.erick.library.exeption;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ErrorDetails {
	
	private HttpStatus status;
	private Date timestamp = new Date();
	private List<MessageError> messages = new ArrayList<MessageError>();

	public ErrorDetails(List<MessageError> messages) {
		this(messages, HttpStatus.BAD_REQUEST);

	}
	public ErrorDetails(List<MessageError> messages, HttpStatus status) {
		this.status = status;
		this.messages = messages;
	}
	
}
