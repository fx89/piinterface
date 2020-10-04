package com.desolatetimelines.piinterface.service.exception;

import com.desolatetimelines.piinterface.exception.PiInterfaceRuntimeException;

public class PiInterfaceServiceException extends PiInterfaceRuntimeException {
	public PiInterfaceServiceException() {
		super();
	}

	public PiInterfaceServiceException(String message) {
		super(message);
	}

	public PiInterfaceServiceException(String message, Exception cause) {
		super(message, cause);
	}
}
