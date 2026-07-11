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

    public double getBalconyPrice(int numberOfNights){
        if (!this.isHasBalcony()) return 0;
        return 20 * numberOfNights;
    }

    public double getBedCountPrice(int numberOfNights){
        if (this.getBedCount() > 1) {
            return 15 * (this.getBedCount() - 1) * numberOfNights; // Additional charge for extra beds
        } else {
            return 0;
        }
    }

    @Override
    public double calculatePrice(int numberOfNights) {
        double price = getBasePrice() * numberOfNights;
        price += getBalconyPrice(numberOfNights);
        price += getBedCountPrice(numberOfNights);
        return price;
    }

    @Override
    public String getAmenities() {
        String amenities = "Double Bed(s) x" + bedCount + ", AC, Attached Bathroom";
        if (hasBalcony) amenities += ", Balcony";
        return amenities;
    }
}