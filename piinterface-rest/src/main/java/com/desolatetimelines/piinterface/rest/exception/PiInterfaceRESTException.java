package com.desolatetimelines.piinterface.rest.exception;

import com.desolatetimelines.piinterface.exception.PiInterfaceRuntimeException;

public class PiInterfaceRESTException extends PiInterfaceRuntimeException {
	public PiInterfaceRESTException() {

	}

	public PiInterfaceRESTException(String message) {
		super(message);
	}

	public PiInterfaceRESTException(String message, Exception cause) {
		super(message, cause);
	}
}
