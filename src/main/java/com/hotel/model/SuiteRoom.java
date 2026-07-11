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

    public double getJacuzziPrice(int numberOfNights){
        if (this.isHasJacuzzi()) {
            return 50 * numberOfNights;
        }
        return 0;
    }

    public double getKitchenettePrice(int numberOfNights){
        if (this.isHasKitchenette()){
            return 30 * numberOfNights;
        }
        return 0;
    }

    public double getRoomCountPrice(int numberOfNights, int numberOfRooms){
        return (numberOfRooms - 1) * 25 * numberOfNights;
    }

    @Override
    public double calculatePrice(int numberOfNights) {
        double price = getBasePrice() * numberOfNights;
        price += getJacuzziPrice(numberOfNights);
        price += getKitchenettePrice(numberOfNights);
        price +=  getRoomCountPrice(numberOfNights, this.getNumberOfRooms()); // Extra rooms charge
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