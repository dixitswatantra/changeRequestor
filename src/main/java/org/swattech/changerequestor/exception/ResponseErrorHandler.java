package org.swattech.changerequestor.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings({ "unchecked", "rawtypes" })
@ControllerAdvice
public class ResponseErrorHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ChangeRequestorException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(ChangeRequestorException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(ex.getMessage(), details);
		return new ResponseEntity(error, HttpStatus.NOT_FOUND);
	}

}