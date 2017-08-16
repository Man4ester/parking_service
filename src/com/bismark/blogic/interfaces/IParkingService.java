package com.bismark.blogic.interfaces;

import java.util.List;

import com.bismark.models.Car;
import com.bismark.models.ParkingPlace;

public interface IParkingService {

	/**
	 * Method to add new parking place
	 * @param place - required
	 */
	void addParkingPlace(ParkingPlace place);

	/**
	 * Method for batch adding parking places
	 * @param places - required
	 */
	void addParkingPlaces(List<ParkingPlace> places);

	/**
	 * Method for adding car
	 * @param cars - required
	 */
	void addCars(List<Car> cars);

	/**
	 * To show information related to parking places
	 */
	void showInformationAboutParking();

}
