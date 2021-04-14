package mx.erick.library.exeption;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {
	

	@Autowired
	private MessageSource messageSource;


	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ErrorDetails> invalidParamDto(
			MethodArgumentNotValidException exception, Locale locale) {
		
		return new ResponseEntity<>(new ErrorDetails(exception.getAllErrors()
				.stream().map(e -> {
			String message = this.messageSource.getMessage(e.getDefaultMessage(), null, locale);
			return new MessageError(message, e.getDefaultMessage());
		}).collect(Collectors.toList())), HttpStatus.BAD_REQUEST);

	}


	@ExceptionHandler(InvalidParamsException.class)
	protected ResponseEntity<ErrorDetails> invalidParams(
			InvalidParamsException exception, Locale locale) {
		String debugMessage = exception.getMessage();
		String message = this.messageSource.getMessage(debugMessage, null, locale);
		List<MessageError> messages = Arrays.asList(new MessageError(message, debugMessage));
		ErrorDetails errorDetails = new ErrorDetails(messages, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(errorDetails, errorDetails.getStatus());
	}


	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorDetails> globalException(
			Exception exception, WebRequest request, Locale locale) {
		String debugMessage = exception.getMessage();
		String message = "errorGeneral";
		List<MessageError> messages = Arrays.asList(new MessageError(message, debugMessage));
		ErrorDetails errorDetails = new ErrorDetails(messages, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(errorDetails, errorDetails.getStatus());
	}

	
}
