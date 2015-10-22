package com.bankonet;

public class CompteNonTrouveException extends Exception {

	public static final String COMPTE_NON_TROUVE_EXCEPTION_MESSAGE = "Aucun compte n'a été trouvé";

	public CompteNonTrouveException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompteNonTrouveException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CompteNonTrouveException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CompteNonTrouveException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CompteNonTrouveException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
