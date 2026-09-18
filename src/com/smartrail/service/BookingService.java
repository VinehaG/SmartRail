package com.smartrail.service;

import com.smartrail.exception.InvalidPassengerException;
import com.smartrail.exception.InvalidTrainException;
import com.smartrail.exception.SeatUnavailableException;
import com.smartrail.model.Ticket;
import com.smartrail.model.Train;
import com.smartrail.payment.PaymentMethod;
import com.smartrail.repository.DataStore;
import com.smartrail.util.PNRGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BookingService {
    private DataStore dataStore;
    private TrainService trainService;
    private PassengerService passengerService;

    public BookingService(DataStore dataStore, TrainService trainService, PassengerService passengerService) {
        this.dataStore = dataStore;
        this.trainService = trainService;
        this.passengerService = passengerService;
    }

    public Ticket bookTicket(String passengerId, String trainNumber, PaymentMethod paymentMethod) 
            throws InvalidPassengerException, InvalidTrainException, SeatUnavailableException {
        
        // Validate passenger
        passengerService.getPassenger(passengerId);
        
        // Validate train
        Train train = trainService.getTrain(trainNumber);
        
        if (train.getAvailableSeats() <= 0) {
            throw new SeatUnavailableException("No seats available on train: " + trainNumber);
        }

        // Process simulated payment
        boolean paymentSuccess = paymentMethod.processPayment(train.getFare());
        if (!paymentSuccess) {
            System.out.println("Payment failed. Booking cannot be completed.");
            return null;
        }

        // Generate Ticket
        String pnr = PNRGenerator.generatePNR();
        int seatNumber = train.getTotalSeats() - train.getAvailableSeats() + 1;
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        Ticket ticket = new Ticket(pnr, passengerId, trainNumber, seatNumber, date, train.getFare(), "CONFIRMED");
        
        // Save Ticket
        List<Ticket> tickets = dataStore.loadTickets();
        tickets.add(ticket);
        dataStore.saveTickets(tickets);

        // Update train seats
        trainService.updateTrainSeats(trainNumber, 1);
        
        return ticket;
    }

    public List<Ticket> getAllBookings() {
        return dataStore.loadTickets();
    }
}
