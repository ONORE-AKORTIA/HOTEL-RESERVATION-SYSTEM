package com.hotel.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private int reservationId;
    private Guest guest;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double totalPrice;
    private String status; // "Confirmed", "Checked-in", "Checked-out", "Cancelled"

    public Reservation(int reservationId, Guest guest, Room room, 
                       LocalDate checkInDate, LocalDate checkOutDate) {
        this.reservationId = reservationId;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = "Confirmed";
        calculateTotalPrice();
    }

    // Getters and Setters (Encapsulation)
    public int getReservationId() { return reservationId; }
    public void setReservationId(int reservationId) { this.reservationId = reservationId; }
    
    public Guest getGuest() { return guest; }
    public void setGuest(Guest guest) { this.guest = guest; }
    
    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
    
    public LocalDate getCheckInDate() { return checkInDate; }
    public void setCheckInDate(LocalDate checkInDate) { this.checkInDate = checkInDate; }
    
    public LocalDate getCheckOutDate() { return checkOutDate; }
    public void setCheckOutDate(LocalDate checkOutDate) { this.checkOutDate = checkOutDate; }
    
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getNumberOfNights() {
        return (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }

    public void calculateTotalPrice() {
        int nights = getNumberOfNights();
        if (nights <= 0) {
            throw new IllegalArgumentException("Check-out date must be after check-in date");
        }
        this.totalPrice = room.calculatePrice(nights);
    }

    public void cancelReservation() {
        if ("Confirmed".equals(status) || "Checked-in".equals(status)) {
            this.status = "Cancelled";
            room.setAvailable(true);
            guest.cancelReservation(this);
        }
    }

    @Override
    public String toString() {
        return "Reservation #" + reservationId + " | Guest: " + guest.getFullName() + 
               " | Room: " + room.getRoomNumber() + " | " + checkInDate + " to " + checkOutDate + 
               " | Total: $" + totalPrice + " | Status: " + status;
    }
}