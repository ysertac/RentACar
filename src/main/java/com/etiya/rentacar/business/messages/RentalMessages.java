package com.etiya.rentacar.business.messages;

public class RentalMessages {
    public static final String rentalNotFound = "Rental not found";
    public static final String deletedRental = "This rental is deleted";
    public static final String checkIfCarState2 = "This vehicle has been rented before";
    public static final String checkIfCarState3 = "This vehicle is under maintenance";
    public static final String checkCustomerHasRented = "This customer has already rented a vehicle. Please return the vehicle.";
    public static final String checkDates = "Rental start date can not exceed end date";
    public static final String checkKilometers = "Rental start kilometer can not exceed end kilometer";
}
