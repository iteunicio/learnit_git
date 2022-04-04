package com.ibi.challenge.exception.handler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.ibi.challenge.exception.ResourceExistsException;
import com.ibi.challenge.exception.ResourceNFException;
import com.ibi.challenge.exception.error.ErrorDetails;

@ControllerAdvice
public class RestExceptionHandler { 
	
	@ExceptionHandler(ResourceNFException.class)
	public ResponseEntity<?> handlerResourceNFException(
			ResourceNFException rnfe, HttpServletRequest request) {
		ErrorDetails err = new ErrorDetails();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		err.setHora(dtf.format(now));
		err.setEstado(HttpStatus.NOT_FOUND.value());
		err.setTitulo("Recurso não encontrado");
		err.setDetalhe(rnfe.getMessage());
		err.setDeveloperMessage(rnfe.getClass().getName());
		err.setHome("http://localhost:8181/paises");
		
		return new ResponseEntity<>(err, null, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handlerValidExceptionError(
			MethodArgumentNotValidException manve, HttpServletRequest request) {
		ErrorDetails err = new ErrorDetails();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		err.setHora(dtf.format(now));
		err.setEstado(HttpStatus.BAD_REQUEST.value());
		err.setTitulo("Recurso inválido");
		err.setDetalhe(manve.getMessage());
		err.setDeveloperMessage(manve.getClass().getName());
		err.setHome("http://localhost:8181/paises");
		
		return new ResponseEntity<>(err, null, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceExistsException.class)
	public ResponseEntity<?> handlerResourceExistsException(
			ResourceExistsException re, HttpServletRequest request) {
		ErrorDetails err = new ErrorDetails();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		err.setHora(dtf.format(now));
		err.setEstado(HttpStatus.FORBIDDEN.value());
		err.setTitulo("Recurso existente");
		err.setDetalhe(re.getMessage());
		err.setDeveloperMessage(re.getClass().getName());
		err.setHome("http://localhost:8181/paises");
		
		return new ResponseEntity<>(err, null, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handlerConstraintViolationException(
			ConstraintViolationException cve, HttpServletRequest request) {
		ErrorDetails err = new ErrorDetails();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		err.setHora(dtf.format(now));
		err.setEstado(HttpStatus.NOT_ACCEPTABLE.value());
		err.setTitulo("Erro na validação");
		err.setDetalhe(cve.getMessage());
		err.setDeveloperMessage(cve.getClass().getName());
		err.setHome("http://localhost:8181/paises");
		
		return new ResponseEntity<>(err, null, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handlerConstraintViolationException(
			HttpMessageNotReadableException mnr, HttpServletRequest request) {
		ErrorDetails err = new ErrorDetails();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		err.setHora(dtf.format(now));
		err.setEstado(HttpStatus.FORBIDDEN.value());
		err.setTitulo("Dados mal introduzidos");
		err.setDetalhe(mnr.getMessage());
		err.setDeveloperMessage(mnr.getClass().getName());
		err.setHome("http://localhost:8181/paises");
		
		return new ResponseEntity<>(err, null, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<?> handlerHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException mns, HttpServletRequest request) {
		ErrorDetails err = new ErrorDetails();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		err.setHora(dtf.format(now));
		err.setEstado(HttpStatus.METHOD_NOT_ALLOWED.value());
		err.setTitulo("Método não permitido");
		err.setDetalhe(mns.getMessage());
		err.setDeveloperMessage(mns.getClass().getName());
		err.setHome("http://localhost:8181/paises");
		
		return new ResponseEntity<>(err, null, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> handlerMethodArgumentTypeMismatchException(
			MethodArgumentTypeMismatchException mtm, HttpServletRequest request) {
		ErrorDetails err = new ErrorDetails();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		err.setHora(dtf.format(now));
		err.setEstado(HttpStatus.BAD_REQUEST.value());
		err.setTitulo("Erro na especificação de parâmetros");
		err.setDetalhe(mtm.getMessage());
		err.setDeveloperMessage(mtm.getClass().getName());
		err.setHome("http://localhost:8181/paises");
		
		return new ResponseEntity<>(err, null, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PropertyReferenceException.class)
	public ResponseEntity<?> handlerPropertyReferenceException(
			PropertyReferenceException pre, HttpServletRequest request) {
		ErrorDetails err = new ErrorDetails();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		err.setHora(dtf.format(now));
		err.setEstado(HttpStatus.INTERNAL_SERVER_ERROR.value());
		err.setTitulo("Propriedade desconhecida (Internal Server Error)");
		err.setDetalhe(pre.getMessage());
		err.setDeveloperMessage(pre.getClass().getName());
		err.setHome("http://localhost:8181/paises");
		
		return new ResponseEntity<>(err, null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}