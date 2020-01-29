package api;


import java.io.Serializable;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionConfig extends ResponseEntityExceptionHandler {

	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity errorNotFound(Exception e) {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity errorBadRequest(Exception e) {
		return ResponseEntity.badRequest().build();
	}
	 @Override
	    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	        return new ResponseEntity<>(new ExceptionError("Operação não permitida"), HttpStatus.METHOD_NOT_ALLOWED);
	    }
	}

	class ExceptionError implements Serializable {
	    private String error;
	    ExceptionError(String error) {
	        this.error = error;
	    }
	    public String getError() {
	        return error;
	    }
	}

