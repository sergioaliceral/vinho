package br.com.digio.vinho.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.digio.vinho.exception.ExternalServiceException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ExternalServiceException.class)
	public ResponseEntity<String> handleExternalServiceException(ExternalServiceException exception) {
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(exception.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneric(Exception exception) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado.");
	}

}
