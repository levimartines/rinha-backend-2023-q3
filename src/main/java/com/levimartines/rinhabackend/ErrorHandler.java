package com.levimartines.rinhabackend;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> dataIntegrity() {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
	}

	@ExceptionHandler(UnprocessableEntityException.class)
	public ResponseEntity<?> UnprocessableEntity() {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> badRequest() {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> notFound() {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
