package com.travelzen.framework.core.exception;

import java.sql.SQLException;

public class DAOException extends SQLException {
	private static final long serialVersionUID = 7450480424769236320L;

	public DAOException() {
	}

	public DAOException(String message) {
		super(message);
	}
	
	public DAOException(Throwable throwable) {
		super(throwable);
	}


	public DAOException(String message, Throwable throwable){
		super(message, throwable);
	}

}