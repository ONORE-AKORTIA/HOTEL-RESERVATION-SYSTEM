package com.hotel.model;

public class Admin extends User {
    private String adminLevel;
    private String department;

    public Admin(int userId, String username, String password, String fullName, 
                 String email, String phoneNumber, String adminLevel, String department) {
        super(userId, username, password, fullName, email, phoneNumber);
        this.adminLevel = adminLevel;
        this.department = department;
    }

    public String getAdminLevel() { return adminLevel; }
    public void setAdminLevel(String adminLevel) { this.adminLevel = adminLevel; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public String getRole() {
        return "Administrator";
    }

    @Override
    public String getDashboardInfo() {
        return "Admin: " + getFullName() + " | Department: " + department + 
               " | Level: " + adminLevel;
    }
}