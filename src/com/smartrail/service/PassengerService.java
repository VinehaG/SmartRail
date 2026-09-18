package com.smartrail.service;

import com.smartrail.exception.InvalidPassengerException;
import com.smartrail.model.Passenger;
import com.smartrail.repository.DataStore;

import java.util.List;

public class PassengerService {
    private DataStore dataStore;

    public PassengerService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void addPassenger(Passenger passenger) throws InvalidPassengerException {
        List<Passenger> passengers = dataStore.loadPassengers();
        
        // Check for duplicate ID
        for (Passenger p : passengers) {
            if (p.getPassengerId().equalsIgnoreCase(passenger.getPassengerId())) {
                throw new InvalidPassengerException("Passenger with ID " + passenger.getPassengerId() + " already exists.");
            }
        }
        
        passengers.add(passenger);
        dataStore.savePassengers(passengers);
    }

    public List<Passenger> getAllPassengers() {
        return dataStore.loadPassengers();
    }

    public Passenger getPassenger(String passengerId) throws InvalidPassengerException {
        List<Passenger> passengers = dataStore.loadPassengers();
        for (Passenger p : passengers) {
            if (p.getPassengerId().equalsIgnoreCase(passengerId)) {
                return p;
            }
        }
        throw new InvalidPassengerException("Passenger not found with ID: " + passengerId);
    }

    public void updatePassenger(String passengerId, String newName, String newPhone, String newEmail) throws InvalidPassengerException {
        List<Passenger> passengers = dataStore.loadPassengers();
        boolean found = false;
        
        for (Passenger p : passengers) {
            if (p.getPassengerId().equalsIgnoreCase(passengerId)) {
                p.setName(newName);
                p.setPhone(newPhone);
                p.setEmail(newEmail);
                found = true;
                break;
            }
        }
        
        if (!found) {
            throw new InvalidPassengerException("Cannot update. Passenger not found with ID: " + passengerId);
        }
        dataStore.savePassengers(passengers);
    }

    public void deletePassenger(String passengerId) throws InvalidPassengerException {
        List<Passenger> passengers = dataStore.loadPassengers();
        boolean removed = passengers.removeIf(p -> p.getPassengerId().equalsIgnoreCase(passengerId));
        
        if (!removed) {
            throw new InvalidPassengerException("Cannot delete. Passenger not found with ID: " + passengerId);
        }
        dataStore.savePassengers(passengers);
    }
}
