package com.bismark.blogic.services;

import com.bismark.blogic.interfaces.IParkingService;

public class ParkingDevice {

	private static IParkingService parkingService;

	private ParkingDevice() {

	}

	public static IParkingService getImplementation() {
		if (null == parkingService) {
			parkingService = new ParkingServiceImpl();
		}
		return parkingService;
	}

}
