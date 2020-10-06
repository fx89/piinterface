package com.desolatetimelines.piinterface.service.exception;

public class CorruptedRegistryException extends PiInterfaceServiceException {
	public CorruptedRegistryException() {
		super();
	}

	public CorruptedRegistryException(String message) {
		super(message);
	}

	public CorruptedRegistryException(String message, Exception cause) {
		super(message, cause);
	}
}
