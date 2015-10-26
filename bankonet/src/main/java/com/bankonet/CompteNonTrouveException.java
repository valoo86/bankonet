package com.bankonet;

public class CompteNonTrouveException extends Exception {

	public static final String COMPTE_NON_TROUVE_EXCEPTION_MESSAGE = "Aucun compte n'a été trouvé";

	public CompteNonTrouveException() {
		super();
	}

	public CompteNonTrouveException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CompteNonTrouveException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompteNonTrouveException(String message) {
		super(message);
	}

	public CompteNonTrouveException(Throwable cause) {
		super(cause);
	}

}
