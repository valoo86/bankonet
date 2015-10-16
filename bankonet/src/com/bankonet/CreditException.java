package com.bankonet;

public class CreditException extends CompteException {

	public static final String CREDIT_EXCEPTION_MESSAGE = "Le montant est supérieur au plafond autorisé !";
	
	public CreditException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreditException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CreditException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CreditException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CreditException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
