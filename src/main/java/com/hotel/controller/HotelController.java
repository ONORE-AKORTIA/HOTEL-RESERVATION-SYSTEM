package com.hotel.controller;

import com.hotel.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

public class HotelController {
    private Hotel hotel;
    private User currentUser;

    public HotelController() {
        this.hotel = new Hotel("Grand Palace Hotel", "123 Main Street, City");
    }

    // Authentication
    public boolean login(String username, String password) {
        if (hotel.validateUser(username, password)) {
            currentUser = hotel.findUserByUsername(username);
            return true;
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() { return currentUser; }
    
    public boolean isAdmin() {
        return currentUser instanceof Admin;
    }

    public boolean isGuest() {
        return currentUser instanceof Guest;
    }

    // Room Operations
    public ObservableList<Room> getAvailableRooms() {
        return FXCollections.observableArrayList(hotel.getAvailableRooms());
    }

    public ObservableList<Room> getAllRooms() {
        return FXCollections.observableArrayList(hotel.getAllRooms());
    }

    public Room getRoomByNumber(int roomNumber) {
        return hotel.findRoomByNumber(roomNumber);
    }

    public void addRoom(Room room) {
        hotel.addRoom(room);
    }

    // Reservation Operations
    public Reservation bookRoom(int roomNumber, LocalDate checkIn, LocalDate checkOut) {
        if (!isGuest()) {
            throw new IllegalStateException("Only guests can book rooms");
        }
        
        Room room = hotel.findRoomByNumber(roomNumber);
        if (room == null) {
            throw new IllegalArgumentException("Room not found");
        }
        
        Guest guest = (Guest) currentUser;
        return hotel.createReservation(guest, room, checkIn, checkOut);
    }

    public void cancelReservation(int reservationId) {
        hotel.cancelReservation(reservationId);
    }

    public ObservableList<Reservation> getCurrentUserReservations() {
        if (isGuest()) {
            Guest guest = (Guest) currentUser;
            return FXCollections.observableArrayList(hotel.getGuestReservations(guest));
        }
        return FXCollections.observableArrayList(hotel.getAllReservations());
    }

    // Statistics
    public int getTotalRooms() { return hotel.getTotalRooms(); }
    public int getAvailableRoomsCount() { return hotel.getAvailableRoomsCount(); }
    public int getBookedRoomsCount() { return hotel.getBookedRoomsCount(); }
    public int getTotalReservations() { return hotel.getTotalReservations(); }
    public int getActiveReservations() { return hotel.getActiveReservationsCount(); }
}