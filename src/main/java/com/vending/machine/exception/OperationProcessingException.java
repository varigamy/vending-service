package com.vending.machine.exception;

/**
 * @author a.zherdetski
 *
 */
public class OperationProcessingException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6865316594640230140L;

	public OperationProcessingException() {
		super();
	}

	public OperationProcessingException(String message) {
		super(message);
	}
}
