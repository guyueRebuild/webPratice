package com.iTeam.exception;
public class SqlRollbackException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SqlRollbackException() {
		super();
	}

	public SqlRollbackException(String message) {
		super(message);
	}
}
