package com.hotel.model;

public abstract class Room {
    private int roomNumber;
    private String roomType;
    private double basePrice;
    private boolean isAvailable;
    private int capacity;

    public Room(int roomNumber, String roomType, double basePrice, int capacity) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.basePrice = basePrice;
        this.isAvailable = true;
        this.capacity = capacity;
    }

    // Getters and Setters (Encapsulation)
    public int getRoomNumber() { return roomNumber; }
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }
    
    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }
    
    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }
    
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    // Abstract method - must be implemented by subclasses
    public abstract double calculatePrice(int numberOfNights);
    
    public abstract String getAmenities();

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + roomType + ") - $ " + basePrice + "/night" + 
               (isAvailable ? " [Available]" : " [Booked]");
    }
}