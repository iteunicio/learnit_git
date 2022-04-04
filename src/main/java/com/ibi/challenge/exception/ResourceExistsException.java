package com.ibi.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ResourceExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ResourceExistsException(String resourceName, 
			String fieldName, String fieldValue) {
		super(String.format("%s com %s = '%s' existente!", 
				resourceName, fieldName, fieldValue));
	}
}