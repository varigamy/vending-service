package com.vending.machine.exception;

/**
 * @author a.zherdetski
 *
 */
public class InsufficientCoinageException extends RuntimeException {
	private static final long serialVersionUID = -218745591901745318L;

	public InsufficientCoinageException() {
		super();
	}

	public InsufficientCoinageException(String message) {
		super(message);
	}

}
