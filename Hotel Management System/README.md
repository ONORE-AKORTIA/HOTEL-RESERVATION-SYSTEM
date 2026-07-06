# Hotel Reservation Management System

## Project Overview
A fully functional GUI-based Hotel Reservation Management System developed using JavaFX. This application demonstrates Object-Oriented Programming principles including Encapsulation, Inheritance, Polymorphism, and Abstraction.

## Features
- **User Authentication**: Login system for Guests and Administrators
- **Room Management**: View, add, and manage room inventory
- **Reservation System**: Book, view, and cancel reservations
- **Role-based Dashboards**: Separate interfaces for Guests and Admins
- **Real-time Updates**: Availability updates on booking/cancellation
- **Date Validation**: Ensures valid check-in/check-out dates
- **Price Calculation**: Automatic total price calculation based on room type and amenities

## Technology Stack
- **Language**: Java 17
- **GUI Framework**: JavaFX
- **Build Tool**: Maven/Gradle (optional)
- **Version Control**: Git & GitHub

## OOP Concepts Implemented
1. **Encapsulation**: Private fields with public getters/setters in all model classes
2. **Inheritance**: Guest and Admin extend User; SingleRoom, DoubleRoom, SuiteRoom extend Room
3. **Polymorphism**: Method overriding in subclasses (calculatePrice, getAmenities, getDashboardInfo)
4. **Abstraction**: Abstract classes (User, Room) with abstract methods

## How to Run
### Prerequisites
- Java 17 or higher
- JavaFX SDK (if not bundled with JDK)

### Steps
1. Clone the repository: