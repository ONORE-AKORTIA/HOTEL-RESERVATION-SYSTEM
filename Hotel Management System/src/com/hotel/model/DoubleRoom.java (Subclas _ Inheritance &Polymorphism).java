package com.hotel.model;

public class DoubleRoom extends Room {
    private boolean hasBalcony;
    private int bedCount;

    public DoubleRoom(int roomNumber, double basePrice, boolean hasBalcony, int bedCount) {
        super(roomNumber, "Double", basePrice, 2);
        this.hasBalcony = hasBalcony;
        this.bedCount = bedCount;
    }

    public boolean isHasBalcony() { return hasBalcony; }
    public void setHasBalcony(boolean hasBalcony) { this.hasBalcony = hasBalcony; }
    
    public int getBedCount() { return bedCount; }
    public void setBedCount(int bedCount) { this.bedCount = bedCount; }

    @Override
    public double calculatePrice(int numberOfNights) {
        double price = getBasePrice() * numberOfNights;
        if (hasBalcony) {
            price += 20 * numberOfNights; // Additional charge for balcony
        }
        if (bedCount > 1) {
            price += 15 * numberOfNights; // Additional charge for extra beds
        }
        return price;
    }

    @Override
    public String getAmenities() {
        String amenities = "Double Bed(s) x" + bedCount + ", AC, Attached Bathroom";
        if (hasBalcony) amenities += ", Balcony";
        return amenities;
    }
}