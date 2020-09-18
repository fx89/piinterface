package com.desolatetimelines.piinterface.exception;

public class PiInterfaceRuntimeException extends RuntimeException {
	public PiInterfaceRuntimeException() {
		super();
	}
	
	public PiInterfaceRuntimeException(String message) {
		super(message);
	}
	
	public PiInterfaceRuntimeException(String message, Exception cause) {
		super(cause);
	}
}
