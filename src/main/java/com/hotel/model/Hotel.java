package com.hotel.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Hotel {
    private String hotelName;
    private String address;
    private List<Room> rooms;
    private List<User> users;
    private List<Reservation> reservations;
    private Map<Integer, Guest> guestMap;
    private int nextReservationId;

    public Hotel(String hotelName, String address) {
        this.hotelName = hotelName;
        this.address = address;
        this.rooms = new ArrayList<>();
        this.users = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.guestMap = new HashMap<>();
        this.nextReservationId = 1000;
        initializeRooms();
        initializeUsers();
    }

    private void initializeRooms() {
        // Single Rooms (101-110)
        for (int i = 101; i <= 110; i++) {
            rooms.add(new SingleRoom(i, 80.0, i % 2 == 0, true));
        }
        // Double Rooms (201-210)
        for (int i = 201; i <= 210; i++) {
            rooms.add(new DoubleRoom(i, 120.0, i % 3 == 0, i % 2 == 0 ? 2 : 1));
        }
        // Suite Rooms (301-305)
        for (int i = 301; i <= 305; i++) {
            rooms.add(new SuiteRoom(i, 200.0, i % 2 == 0, i % 3 == 0, i % 3 + 2));
        }
    }

    private void initializeUsers() {
        // Admin user
        users.add(new Admin(1, "admin", "admin123", "System Admin", 
                           "admin@hotel.com", "+1234567890", "Super", "Management"));
        
        // Sample Guest
        Guest guest = new Guest(2, "john", "john123", "John Doe", 
                               "john@email.com", "+9876543210");
        users.add(guest);
        guestMap.put(2, guest);
    }

    // Room Management
    public List<Room> getAllRooms() { return rooms; }
    
    public List<Room> getAvailableRooms() {
        return rooms.stream().filter(Room::isAvailable).collect(Collectors.toList());
    }

    public Room findRoomByNumber(int roomNumber) {
        return rooms.stream().filter(r -> r.getRoomNumber() == roomNumber).findFirst().orElse(null);
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void removeRoom(int roomNumber) {
        Room room = findRoomByNumber(roomNumber);
        if (room != null) {
            if (room.isAvailable()) {
                rooms.remove(room);
            } else {
                throw new IllegalStateException("Cannot remove booked room");
            }
        }
    }

    // User Management
    public List<User> getAllUsers() { return users; }
    
    public User findUserByUsername(String username) {
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
    }

    public Guest findGuestByUsername(String username) {
        User user = findUserByUsername(username);
        if (user instanceof Guest) {
            return (Guest) user;
        }
        return null;
    }

    public void addUser(User user) {
        users.add(user);
        if (user instanceof Guest) {
            guestMap.put(user.getUserId(), (Guest) user);
        }
    }

    public boolean validateUser(String username, String password) {
        User user = findUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    // Reservation Management
    public List<Reservation> getAllReservations() { return reservations; }

    public Reservation createReservation(Guest guest, Room room, LocalDate checkIn, LocalDate checkOut) {
        if (!room.isAvailable()) {
            throw new IllegalStateException("Room is not available");
        }
        if (checkIn.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Check-in date cannot be in the past");
        }
        if (checkOut.isBefore(checkIn) || checkOut.equals(checkIn)) {
            throw new IllegalArgumentException("Invalid check-out date");
        }

        Reservation reservation = new Reservation(nextReservationId++, guest, room, checkIn, checkOut);
        room.setAvailable(false);
        guest.addReservation(reservation);
        reservations.add(reservation);
        return reservation;
    }

    public void cancelReservation(int reservationId) {
        Reservation reservation = reservations.stream()
            .filter(r -> r.getReservationId() == reservationId)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        reservation.cancelReservation();
    }

    public List<Reservation> getGuestReservations(Guest guest) {
        return reservations.stream()
            .filter(r -> r.getGuest().getUserId() == guest.getUserId())
            .collect(Collectors.toList());
    }

    public List<Reservation> getActiveReservations() {
        return reservations.stream()
            .filter(r -> "Confirmed".equals(r.getStatus()) || "Checked-in".equals(r.getStatus()))
            .collect(Collectors.toList());
    }

    // Hotel Info
    public String getHotelName() { return hotelName; }
    public void setHotelName(String hotelName) { this.hotelName = hotelName; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    // Statistics
    public int getTotalRooms() { return rooms.size(); }
    public int getAvailableRoomsCount() { return getAvailableRooms().size(); }
    public int getBookedRoomsCount() { return rooms.size() - getAvailableRoomsCount(); }
    public int getTotalReservations() { return reservations.size(); }
    public int getActiveReservationsCount() { return getActiveReservations().size(); }
}