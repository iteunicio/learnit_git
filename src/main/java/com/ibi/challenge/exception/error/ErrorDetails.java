package com.ibi.challenge.exception.error;

public class ErrorDetails {

	private String titulo;
	private int estado;
	private String detalhe;
	private String hora;
	private String developerMessage;
	private String home;
	
	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public int getEstado() {
		return estado;
	}
	
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	public String getDetalhe() {
		return detalhe;
	}
	
	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}
	
	public String getHora() {
		return hora;
	}
	
	public void setHora(String hora) {
		this.hora = hora;
	}
	
	public String getDeveloperMessage() {
		return developerMessage;
	}
	
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
}
