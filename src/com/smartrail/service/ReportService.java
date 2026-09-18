package com.smartrail.service;

import com.smartrail.model.Passenger;
import com.smartrail.model.Ticket;
import com.smartrail.model.Train;
import com.smartrail.repository.DataStore;

import java.util.List;

public class ReportService {
    private DataStore dataStore;

    public ReportService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void generateBookingReport() {
        List<Ticket> tickets = dataStore.loadTickets();
        int totalBookings = tickets.size();
        int confirmed = 0;
        int cancelled = 0;
        double totalRevenue = 0.0;

        for (Ticket t : tickets) {
            if (t.getStatus().equalsIgnoreCase("CONFIRMED")) {
                confirmed++;
                totalRevenue += t.getFare();
            } else if (t.getStatus().equalsIgnoreCase("CANCELLED")) {
                cancelled++;
            }
        }

        System.out.println("\n========== BOOKING REPORT ==========");
        System.out.println("Total Bookings : " + totalBookings);
        System.out.println("Confirmed      : " + confirmed);
        System.out.println("Cancelled      : " + cancelled);
        System.out.println(String.format("Total Revenue  : ₹%.2f", totalRevenue));
        System.out.println("====================================");
    }

    public void generateTrainOccupancyReport() {
        List<Train> trains = dataStore.loadTrains();
        System.out.println("\n========= TRAIN OCCUPANCY =========");
        for (Train t : trains) {
            int bookedSeats = t.getTotalSeats() - t.getAvailableSeats();
            double occupancyPercentage = ((double) bookedSeats / t.getTotalSeats()) * 100;
            System.out.println(String.format("%s (%s) - %d/%d seats booked (%.2f%%)",
                    t.getTrainName(), t.getTrainNumber(), bookedSeats, t.getTotalSeats(), occupancyPercentage));
        }
        System.out.println("===================================");
    }
    
    public void generatePassengerReport() {
        List<Passenger> passengers = dataStore.loadPassengers();
        System.out.println("\n========= PASSENGER STATS =========");
        System.out.println("Total Registered Passengers: " + passengers.size());
        System.out.println("===================================");
    }
}
