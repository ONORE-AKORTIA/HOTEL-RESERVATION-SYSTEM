package com.hotel.model;

public class SingleRoom extends Room {
    private boolean hasWorkDesk;
    private boolean hasTV;

    public SingleRoom(int roomNumber, double basePrice, boolean hasWorkDesk, boolean hasTV) {
        super(roomNumber, "Single", basePrice, 1);
        this.hasWorkDesk = hasWorkDesk;
        this.hasTV = hasTV;
    }

    public boolean isHasWorkDesk() { return hasWorkDesk; }
    public void setHasWorkDesk(boolean hasWorkDesk) { this.hasWorkDesk = hasWorkDesk; }
    
    public boolean isHasTV() { return hasTV; }
    public void setHasTV(boolean hasTV) { this.hasTV = hasTV; }

    @Override
    public double calculatePrice(int numberOfNights) {
        double price = getBasePrice() * numberOfNights;
        if (hasWorkDesk) {
            price += 10 * numberOfNights; // Additional charge for work desk
        }
        return price;
    }

    @Override
    public String getAmenities() {
        String amenities = "Single Bed, AC, Attached Bathroom";
        if (hasWorkDesk) amenities += ", Work Desk";
        if (hasTV) amenities += ", TV";
        return amenities;
    }
}