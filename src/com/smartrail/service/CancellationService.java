package com.smartrail.service;

import com.smartrail.exception.BookingNotFoundException;
import com.smartrail.exception.InvalidTrainException;
import com.smartrail.model.Ticket;
import com.smartrail.repository.DataStore;

import java.util.List;

public class CancellationService {
    private DataStore dataStore;
    private TrainService trainService;

    public CancellationService(DataStore dataStore, TrainService trainService) {
        this.dataStore = dataStore;
        this.trainService = trainService;
    }

    public void cancelTicket(String pnr) throws BookingNotFoundException, InvalidTrainException {
        List<Ticket> tickets = dataStore.loadTickets();
        Ticket targetTicket = null;
        
        for (Ticket t : tickets) {
            if (t.getPnr().equalsIgnoreCase(pnr)) {
                targetTicket = t;
                break;
            }
        }
        
        if (targetTicket == null) {
            throw new BookingNotFoundException("No booking found with PNR: " + pnr);
        }

        if (targetTicket.getStatus().equalsIgnoreCase("CANCELLED")) {
            System.out.println("Ticket with PNR " + pnr + " is already cancelled.");
            return;
        }

        // Update status
        targetTicket.setStatus("CANCELLED");
        dataStore.saveTickets(tickets);

        // Restore seat
        trainService.updateTrainSeats(targetTicket.getTrainNumber(), -1);
        
        System.out.println("Ticket cancelled successfully. Seat restored.");
    }

    public Ticket getTicket(String pnr) throws BookingNotFoundException {
        List<Ticket> tickets = dataStore.loadTickets();
        for (Ticket t : tickets) {
            if (t.getPnr().equalsIgnoreCase(pnr)) {
                return t;
            }
        }
        throw new BookingNotFoundException("No booking found with PNR: " + pnr);
    }
}
