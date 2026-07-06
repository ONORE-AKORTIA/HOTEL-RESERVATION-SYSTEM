package com.hotel.model;

public abstract class User {
    private int userId;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;

    // Constructor
    public User(int userId, String username, String password, String fullName, String email, String phoneNumber) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters (Encapsulation)
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    // Abstract method - Abstraction
    public abstract String getRole();
    
    // Polymorphism - can be overridden
    public String getDashboardInfo() {
        return "Welcome " + fullName + " (" + getRole() + ")";
    }
}