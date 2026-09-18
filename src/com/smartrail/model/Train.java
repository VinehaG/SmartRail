package com.smartrail.model;

public class Train {
    private String trainNumber;
    private String trainName;
    private String source;
    private String destination;
    private int totalSeats;
    private int availableSeats;
    private double fare;

    public Train(String trainNumber, String trainName, String source, String destination, int totalSeats, int availableSeats, double fare) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.fare = fare;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getFare() {
        return fare;
    }

    public String toCsv() {
        return trainNumber + "," + trainName + "," + source + "," + destination + "," + totalSeats + "," + availableSeats + "," + fare;
    }

    public static Train fromCsv(String csv) {
        String[] parts = csv.split(",");
        if (parts.length >= 7) {
            return new Train(parts[0], parts[1], parts[2], parts[3], 
                    Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), Double.parseDouble(parts[6]));
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-20s | %-15s to %-15s | Seats: %-3d/%-3d | Fare: ₹%.2f",
                trainNumber, trainName, source, destination, availableSeats, totalSeats, fare);
    }
}
