package com.bismark.blogic.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bismark.blogic.interfaces.IParkingService;
import com.bismark.exceptions.NotEnoughParkingPlace;
import com.bismark.models.Car;
import com.bismark.models.ParkingPlace;
import com.bismark.models.SizeType;

class ParkingServiceImpl implements IParkingService {

	private List<ParkingPlace> places = new ArrayList<>();

	@Override
	public void addParkingPlace(ParkingPlace place) {
		if(null == place) {
			return;
		}
		this.places.add(place);
		Collections.sort(this.places,
				(ParkingPlace p1, ParkingPlace p2) -> 
					p1.getSizeType().getWeight() - p2.getSizeType().getWeight());

	}

	@Override
	public void addParkingPlaces(List<ParkingPlace> places) {
		if(null == places) {
			return;
		}
		this.places.addAll(places);
		Collections.sort(this.places,
				(ParkingPlace p1, ParkingPlace p2) -> 
					p1.getSizeType().getWeight() - p2.getSizeType().getWeight());

	}

	@Override
	public void addCars(List<Car> cars) {
		if(null == cars) {
			return;
		}
		for (Car car : cars) {
			try {
				addCar(car);
			} catch (NotEnoughParkingPlace e) {
				printMessage(e.getMessage());
			}
		}

	}

	@Override
	public void showInformationAboutParking() {
		printMessage("Total places: " + places.size());
		int freePlaces = 0;
		int usedPlaces = 0;
		for (ParkingPlace parkingPlace : places) {
			if (null == parkingPlace.getCar()) {
				freePlaces++;
			} else {
				usedPlaces++;
			}
		}

		printMessage("Used places:" + usedPlaces);
		printMessage("Free places:" + freePlaces);
		printMessage("");

		int smallPlacesUsed = 0;
		int mediumPlacesUsed = 0;
		int bigPlacesUsed = 0;

		int smallPlacesFree = 0;
		int mediumPlacesFree = 0;
		int bigPlacesFree = 0;

		for (ParkingPlace parkingPlace : places) {
			switch (parkingPlace.getSizeType()) {
			case SMALL:
				smallPlacesFree = smallPlacesFree + getParameterByPlace(parkingPlace, SizeType.SMALL, true);
				smallPlacesUsed = smallPlacesUsed + getParameterByPlace(parkingPlace, SizeType.SMALL, false);
				break;
			case MEDIUM:
				mediumPlacesFree = mediumPlacesFree + getParameterByPlace(parkingPlace, SizeType.MEDIUM, true);
				mediumPlacesUsed = mediumPlacesUsed + getParameterByPlace(parkingPlace, SizeType.MEDIUM, false);
				break;
			case BIG:
				bigPlacesFree = bigPlacesFree + getParameterByPlace(parkingPlace, SizeType.BIG, true);
				bigPlacesUsed = bigPlacesUsed + getParameterByPlace(parkingPlace, SizeType.BIG, false);
				break;

			default:
				break;
			}
		}

		printMessage("Small total:" + (smallPlacesFree + smallPlacesUsed));
		printMessage("Small free:" + smallPlacesFree);
		printMessage("Small used:" + smallPlacesUsed);
		printMessage("");

		printMessage("Medium total:" + (mediumPlacesFree + mediumPlacesUsed));
		printMessage("Medium free:" + mediumPlacesFree);
		printMessage("Medium used:" + mediumPlacesUsed);
		printMessage("");

		printMessage("Big total:" + (bigPlacesFree + bigPlacesUsed));
		printMessage("Big free:" + bigPlacesFree);
		printMessage("Big used:" + bigPlacesUsed);
		printMessage("");

	}

	synchronized void addCar(Car car) throws NotEnoughParkingPlace {
		SizeType sType = null;
		for (ParkingPlace parkingPlace : places) {
			if (canAddCar(parkingPlace, car)) {
				parkingPlace.setCar(car);
				sType = parkingPlace.getSizeType();
				break;
			}
		}
		if (null == sType) {
			throw new NotEnoughParkingPlace("Sorry we can't find place for car: " + car.getSizeType());
		} else {
			printMessage(String.format("Car %s placed on place %s", car.getSizeType(), sType));
		}
	}

	private boolean canAddCar(ParkingPlace parkingPlace, Car car) {
		return parkingPlace.getCar() == null && parkingPlace.getSizeType().getWeight() >= car.getSizeType().getWeight();
	}

	private int getParameterByPlace(ParkingPlace pl, SizeType sizeType, boolean isFree) {
		if (isFree) {
			return null == pl.getCar() && sizeType.equals(pl.getSizeType()) ? 1 : 0;
		} else {
			return null != pl.getCar() && sizeType.equals(pl.getSizeType()) ? 1 : 0;
		}
	}
	
	private static void printMessage(String message){
		System.out.println(message);
	}

}
