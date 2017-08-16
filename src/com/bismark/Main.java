package com.bismark;

import java.util.Arrays;
import java.util.List;

import com.bismark.blogic.services.ParkingDevice;
import com.bismark.models.Car;
import com.bismark.models.ParkingPlace;
import com.bismark.models.SizeType;

public class Main {
	
	private Main() {
		
	}
	
	public static void main(String[] args) throws Exception {
		
		initParking();
		
		ParkingDevice.getImplementation().showInformationAboutParking();
		
		Runnable task1 = () ->  
			ParkingDevice.getImplementation().addCars(
					Arrays.asList(
					new Car(SizeType.MEDIUM),
					new Car(SizeType.MEDIUM),
					new Car(SizeType.MEDIUM),
					new Car(SizeType.MEDIUM)));
		
		
		Runnable task2 = () -> 
			ParkingDevice.getImplementation().addCars(
					Arrays.asList(
					new Car(SizeType.SMALL),
					new Car(SizeType.MEDIUM),
					new Car(SizeType.MEDIUM),
					new Car(SizeType.MEDIUM)));
		
		 
		// start the thread
		Thread t1 =  new Thread(task1);
		Thread t2 = new Thread(task2);
		
		t1.start();
		t2.start();
		
		Thread.sleep(3000);
		System.out.println();
		System.out.println("FINAL REPORT");
		ParkingDevice.getImplementation().showInformationAboutParking();
	}
	
	public static void initParking() {
		
		ParkingPlace p1 = new ParkingPlace(SizeType.SMALL);
		
		List<ParkingPlace> places = Arrays.asList(
				
				new ParkingPlace(SizeType.BIG),
				new ParkingPlace(SizeType.BIG),
				new ParkingPlace(SizeType.SMALL),
				new ParkingPlace(SizeType.SMALL),
				new ParkingPlace(SizeType.MEDIUM));
		
		ParkingDevice.getImplementation().addParkingPlace(p1);
		ParkingDevice.getImplementation().addParkingPlaces(places);
		
	}

}
