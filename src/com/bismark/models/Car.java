package com.bismark.models;

public class Car {

	private SizeType sizeType;

	public Car(SizeType sizeType) {
		if(null == sizeType) {
			throw new NullPointerException("SizeType is required");
		}
		this.sizeType = sizeType;
	}

	public SizeType getSizeType() {
		return sizeType;
	}

}
