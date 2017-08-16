package com.bismark.exceptions;

public class NotEnoughParkingPlace extends Exception {

	private static final long serialVersionUID = 1L;

	public NotEnoughParkingPlace(String message) {
		super(message);
	}

}
