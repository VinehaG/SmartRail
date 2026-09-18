package com.smartrail.model;

public class Ticket {
    private String pnr;
    private String passengerId;
    private String trainNumber;
    private int seatNumber;
    private String bookingDate;
    private double fare;
    private String status; // CONFIRMED or CANCELLED

    public Ticket(String pnr, String passengerId, String trainNumber, int seatNumber, String bookingDate, double fare, String status) {
        this.pnr = pnr;
        this.passengerId = passengerId;
        this.trainNumber = trainNumber;
        this.seatNumber = seatNumber;
        this.bookingDate = bookingDate;
        this.fare = fare;
        this.status = status;
    }

    public String getPnr() {
        return pnr;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public double getFare() {
        return fare;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toCsv() {
        return pnr + "," + passengerId + "," + trainNumber + "," + seatNumber + "," + bookingDate + "," + fare + "," + status;
    }

    public static Ticket fromCsv(String csv) {
        String[] parts = csv.split(",");
        if (parts.length >= 7) {
            return new Ticket(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), 
                    parts[4], Double.parseDouble(parts[5]), parts[6]);
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("PNR: %s | Passenger: %s | Train: %s | Seat: %d | Date: %s | Fare: ₹%.2f | Status: %s",
                pnr, passengerId, trainNumber, seatNumber, bookingDate, fare, status);
    }
}
