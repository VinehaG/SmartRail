package com.smartrail.service;

import com.smartrail.exception.InvalidTrainException;
import com.smartrail.model.Train;
import com.smartrail.repository.DataStore;

import java.util.ArrayList;
import java.util.List;

public class TrainService {
    private DataStore dataStore;

    public TrainService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void addTrain(Train train) throws InvalidTrainException {
        List<Train> trains = dataStore.loadTrains();
        for (Train t : trains) {
            if (t.getTrainNumber().equalsIgnoreCase(train.getTrainNumber())) {
                throw new InvalidTrainException("Train with number " + train.getTrainNumber() + " already exists.");
            }
        }
        trains.add(train);
        dataStore.saveTrains(trains);
    }

    public List<Train> getAllTrains() {
        return dataStore.loadTrains();
    }

    public Train getTrain(String trainNumber) throws InvalidTrainException {
        List<Train> trains = dataStore.loadTrains();
        for (Train t : trains) {
            if (t.getTrainNumber().equalsIgnoreCase(trainNumber)) {
                return t;
            }
        }
        throw new InvalidTrainException("Train not found with number: " + trainNumber);
    }

    public List<Train> searchTrains(String source, String destination) {
        List<Train> allTrains = dataStore.loadTrains();
        List<Train> result = new ArrayList<>();
        
        for (Train t : allTrains) {
            if (t.getSource().equalsIgnoreCase(source) && t.getDestination().equalsIgnoreCase(destination)) {
                result.add(t);
            }
        }
        return result;
    }

    public void updateTrainSeats(String trainNumber, int bookedSeats) throws InvalidTrainException {
        List<Train> trains = dataStore.loadTrains();
        boolean found = false;
        
        for (Train t : trains) {
            if (t.getTrainNumber().equalsIgnoreCase(trainNumber)) {
                t.setAvailableSeats(t.getAvailableSeats() - bookedSeats); // negative to decrease, positive to increase
                found = true;
                break;
            }
        }
        
        if (!found) {
            throw new InvalidTrainException("Train not found for updating seats: " + trainNumber);
        }
        dataStore.saveTrains(trains);
    }
}
