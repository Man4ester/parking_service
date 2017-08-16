package com.bismark.models;

public class ParkingPlace {

	private SizeType sizeType;

	private Car car;

	public ParkingPlace(SizeType sizeType) {
		if (null == sizeType) {
			throw new NullPointerException("SizeType is required");
		}
		this.sizeType = sizeType;
	}

	public SizeType getSizeType() {
		return sizeType;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

}
