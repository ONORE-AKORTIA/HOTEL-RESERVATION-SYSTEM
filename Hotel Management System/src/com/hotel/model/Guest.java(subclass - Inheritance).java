package com.hotel.model;

import java.util.ArrayList;
import java.util.List;

public class Guest extends User {
    private List<Reservation> reservations;
    private double loyaltyPoints;

    public Guest(int userId, String username, String password, String fullName, String email, String phoneNumber) {
        super(userId, username, password, fullName, email, phoneNumber);
        this.reservations = new ArrayList<>();
        this.loyaltyPoints = 0.0;
    }

    public List<Reservation> getReservations() { return reservations; }
    public void setReservations(List<Reservation> reservations) { this.reservations = reservations; }
    
    public double getLoyaltyPoints() { return loyaltyPoints; }
    public void setLoyaltyPoints(double loyaltyPoints) { this.loyaltyPoints = loyaltyPoints; }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        loyaltyPoints += reservation.getTotalPrice() * 0.05; // 5% loyalty points
    }

    public void cancelReservation(Reservation reservation) {
        reservations.remove(reservation);
        loyaltyPoints -= reservation.getTotalPrice() * 0.05;
    }

    @Override
    public String getRole() {
        return "Guest";
    }

    @Override
    public String getDashboardInfo() {
        return "Guest: " + getFullName() + " | Reservations: " + reservations.size() + 
               " | Loyalty Points: " + loyaltyPoints;
    }
}