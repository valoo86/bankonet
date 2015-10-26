package com.bankonet;

import com.bankonet.exception.BankonetException;

public class ClientException extends BankonetException {

	public ClientException() {
		super();
	}

	public ClientException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ClientException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ClientException(String arg0) {
		super(arg0);
	}

	public ClientException(Throwable arg0) {
		super(arg0);
	}
	
}
