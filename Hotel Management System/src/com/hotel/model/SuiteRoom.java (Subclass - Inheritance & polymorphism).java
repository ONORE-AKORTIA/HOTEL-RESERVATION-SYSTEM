package com.hotel.model;

public class SuiteRoom extends Room {
    private boolean hasJacuzzi;
    private boolean hasKitchenette;
    private int numberOfRooms;

    public SuiteRoom(int roomNumber, double basePrice, boolean hasJacuzzi, 
                     boolean hasKitchenette, int numberOfRooms) {
        super(roomNumber, "Suite", basePrice, 4);
        this.hasJacuzzi = hasJacuzzi;
        this.hasKitchenette = hasKitchenette;
        this.numberOfRooms = numberOfRooms;
    }

    public boolean isHasJacuzzi() { return hasJacuzzi; }
    public void setHasJacuzzi(boolean hasJacuzzi) { this.hasJacuzzi = hasJacuzzi; }
    
    public boolean isHasKitchenette() { return hasKitchenette; }
    public void setHasKitchenette(boolean hasKitchenette) { this.hasKitchenette = hasKitchenette; }
    
    public int getNumberOfRooms() { return numberOfRooms; }
    public void setNumberOfRooms(int numberOfRooms) { this.numberOfRooms = numberOfRooms; }

    @Override
    public double calculatePrice(int numberOfNights) {
        double price = getBasePrice() * numberOfNights;
        if (hasJacuzzi) {
            price += 50 * numberOfNights;
        }
        if (hasKitchenette) {
            price += 30 * numberOfNights;
        }
        price += (numberOfRooms - 1) * 25 * numberOfNights; // Extra rooms charge
        return price;
    }

    @Override
    public String getAmenities() {
        String amenities = "Suite with " + numberOfRooms + " rooms, Premium AC, Luxury Bathroom";
        if (hasJacuzzi) amenities += ", Jacuzzi";
        if (hasKitchenette) amenities += ", Kitchenette";
        return amenities;
    }
}