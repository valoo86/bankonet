package com.bankonet;

import com.bankonet.exception.BankonetException;

public class CompteException extends BankonetException {

	public CompteException() {
		super();
	}

	public CompteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CompteException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompteException(String message) {
		super(message);
	}

	public CompteException(Throwable cause) {
		super(cause);
	}

}
