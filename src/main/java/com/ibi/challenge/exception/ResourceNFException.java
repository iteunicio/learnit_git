package com.ibi.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNFException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ResourceNFException(String resourceName, 
			String fieldName, Object fieldValue) {
		super(String.format("%s com %s = '%s' não encontrado!", 
			resourceName, fieldName, fieldValue));
	}
}