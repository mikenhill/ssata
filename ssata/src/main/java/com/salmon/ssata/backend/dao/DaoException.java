package com.salmon.ssata.backend.dao;

import com.salmon.ssata.backend.BackendException;


/**
 * @author mhill
 *
 */
public class DaoException extends BackendException {

	private static final long serialVersionUID = 899878074499611218L;

	private String errorCode = "DAO_EXCEPTION";

	public DaoException(String message) {
		super(message);
	}

	public DaoException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

}
