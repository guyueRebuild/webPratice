package com.iTeam.exception;

public class DuplicationException extends RuntimeException{
 
	private static final long serialVersionUID = 1L;
	private String username;
	public DuplicationException(String username) {
		this.username=username;
	}
	
	public String getuserName() {
		return this.username;
	}

}
