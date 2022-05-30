package com.uca.spring.jasper;

import org.springframework.dao.DataAccessException;

public class JasperDataAccessException extends DataAccessException {

	/**
	 *
	 */
	private static final long serialVersionUID = 9146854685655966132L;

	public JasperDataAccessException(String msg, Throwable cause) {
		super(msg, cause);
		// TODO Auto-generated constructor stub
	}

}
