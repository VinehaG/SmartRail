package com.smartrail.repository;

import com.smartrail.model.Passenger;
import com.smartrail.model.Ticket;
import com.smartrail.model.Train;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataStore {
    private static final String DATA_DIR = "data";
    private static final String PASSENGERS_FILE = DATA_DIR + "/passengers.csv";
    private static final String TRAINS_FILE = DATA_DIR + "/trains.csv";
    private static final String TICKETS_FILE = DATA_DIR + "/tickets.csv";

    public DataStore() {
        initDirectory();
        initSampleTrainsIfNeeded();
    }

    private void initDirectory() {
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
            if (!Files.exists(Paths.get(PASSENGERS_FILE))) Files.createFile(Paths.get(PASSENGERS_FILE));
            if (!Files.exists(Paths.get(TRAINS_FILE))) Files.createFile(Paths.get(TRAINS_FILE));
            if (!Files.exists(Paths.get(TICKETS_FILE))) Files.createFile(Paths.get(TICKETS_FILE));
        } catch (IOException e) {
            System.err.println("Error initializing data directories/files: " + e.getMessage());
        }
    }

    private void initSampleTrainsIfNeeded() {
        List<Train> trains = loadTrains();
        if (trains.isEmpty()) {
            trains.add(new Train("12001", "Shatabdi Express", "Delhi", "Bhopal", 500, 500, 1500.0));
            trains.add(new Train("12951", "Rajdhani Express", "Mumbai", "Delhi", 800, 800, 2500.0));
            trains.add(new Train("12810", "Howrah Mail", "Mumbai", "Kolkata", 600, 600, 1800.0));
            trains.add(new Train("12627", "Karnataka Express", "Bangalore", "Delhi", 700, 700, 2100.0));
            saveTrains(trains);
        }
    }

    // --- Passenger Operations ---
    public List<Passenger> loadPassengers() {
        List<Passenger> passengers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PASSENGERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                Passenger p = Passenger.fromCsv(line);
                if (p != null) passengers.add(p);
            }
        } catch (IOException e) {
            System.err.println("Error reading passengers: " + e.getMessage());
        }
        return passengers;
    }

    public void savePassengers(List<Passenger> passengers) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PASSENGERS_FILE))) {
            for (Passenger p : passengers) {
                bw.write(p.toCsv());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving passengers: " + e.getMessage());
        }
    }

    // --- Train Operations ---
    public List<Train> loadTrains() {
        List<Train> trains = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(TRAINS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                Train t = Train.fromCsv(line);
                if (t != null) trains.add(t);
            }
        } catch (IOException e) {
            System.err.println("Error reading trains: " + e.getMessage());
        }
        return trains;
    }

    public void saveTrains(List<Train> trains) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TRAINS_FILE))) {
            for (Train t : trains) {
                bw.write(t.toCsv());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving trains: " + e.getMessage());
        }
    }

    // --- Ticket Operations ---
    public List<Ticket> loadTickets() {
        List<Ticket> tickets = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(TICKETS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                Ticket t = Ticket.fromCsv(line);
                if (t != null) tickets.add(t);
            }
        } catch (IOException e) {
            System.err.println("Error reading tickets: " + e.getMessage());
        }
        return tickets;
    }

    public void saveTickets(List<Ticket> tickets) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TICKETS_FILE))) {
            for (Ticket t : tickets) {
                bw.write(t.toCsv());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving tickets: " + e.getMessage());
        }
    }
}
