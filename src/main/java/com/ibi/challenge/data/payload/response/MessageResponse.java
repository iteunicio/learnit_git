package com.ibi.challenge.data.payload.response;

public class MessageResponse {

	private String message;
	
	private String self;
	
	private String home;
	
	public MessageResponse(String message) {
		this.message = message;
	}
	
	public MessageResponse(String message, String self) {
		this.message = message;
		this.self = self;
	}
	
	public MessageResponse(String message, String self, String home) {
		this.message = message;
		this.self = self;
		this.home = home;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSelf() {
		return self;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}
}

