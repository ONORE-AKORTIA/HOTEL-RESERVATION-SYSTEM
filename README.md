# 🏨 Hotel Reservation Management System
A fully functional **GUI-based Hotel Reservation Management System** developed using **Java** and **JavaFX**. This project demonstrates the practical application of **Object-Oriented Programming (OOP)** principles in building a real-world software solution.
---
## 📌 Project Overview
The Hotel Reservation Management System is designed to automate and streamline hotel booking operations. It provides an intuitive interface for both **guests** and **administrators** to manage room reservations efficiently.
**Key functionalities include: **
- User authentication (Guest & Admin roles)
- Real-time room availability checking
- Room booking with date selection
- Reservation management (view, cancel)
- Admin dashboard with system statistics
- Room inventory management
---
## 🎯 Purpose
This project was developed as part of the **INF811D: Object Oriented Programming** course at the **University of Cape Coast**. It serves to demonstrate:
- Mastery of **Object-Oriented Programming** principles
- Proficiency in **Java** and **JavaFX** GUI development
- Implementation of **event-driven programming**
- **Exception handling** and **input validation**
- **Modular programming** and **code organization**


## 🛠️ Technology Stack
Technology	Purpose
**Java 17**	Programming Language
**JavaFX**	GUI Framework
**JDK 21 (Eclipse Temurin)**	Java Development Kit
**Git & GitHub**	Version Control

---

## 📂 Project Structure

```text
HotelReservationSystem/
│
├── src/
│   └── com/
│       └── hotel/
│           ├── Main.java                      # Application entry point
│           │
│           ├── model/                         # Data layer (Model)
│           │   ├── User.java                  # Abstract base class
│           │   ├── Guest.java                 # Guest subclass
│           │   ├── Admin.java                 # Admin subclass
│           │   ├── Room.java                  # Abstract room class
│           │   ├── SingleRoom.java            # Single room type
│           │   ├── DoubleRoom.java            # Double room type
│           │   ├── SuiteRoom.java             # Suite room type
│           │   ├── Reservation.java           # Reservation entity
│           │   └── Hotel.java                 # Central data management
│           │
│           ├── controller/                    # Business logic (Controller)
│           │   └── HotelController.java
│           │
│           └── view/                          # User Interface (View)
│               ├── LoginView.java
│               ├── GuestDashboardView.java
│               └── AdminDashboardView.java
│
├── screenshots/                               # Application screenshots
├── README.md                                  # Project documentation
└── .gitignore                                 # Git ignored files
```

---

## 📸 Screenshots
### Login Screen
![Login Screen](https://raw.githubusercontent.com/ONORE-AKORTIA/HOTEL-RESERVATION-SYSTEM/main/screenshot/login.png)

### Guest Dashboard
![Guest Dashboard](https://raw.githubusercontent.com/ONORE-AKORTIA/HOTEL-RESERVATION-SYSTEM/main/screenshot/guest-dashboard.png)

### Booking a Room
![Booking a Room](https://raw.githubusercontent.com/ONORE-AKORTIA/HOTEL-RESERVATION-SYSTEM/main/screenshot/booking-room.png)

### Guest Reservations
![Guest Reservations](https://raw.githubusercontent.com/ONORE-AKORTIA/HOTEL-RESERVATION-SYSTEM/main/screenshot/guest-reservations.png)

### Admin Dashboard
![Admin Dashboard](https://raw.githubusercontent.com/ONORE-AKORTIA/HOTEL-RESERVATION-SYSTEM/main/screenshot/admin-dashboard.png)

### Room Management
![Room Management](https://raw.githubusercontent.com/ONORE-AKORTIA/HOTEL-RESERVATION-SYSTEM/main/screenshot/room-management.png)

### All Reservations
![All Reservations](https://raw.githubusercontent.com/ONORE-AKORTIA/HOTEL-RESERVATION-SYSTEM/main/screenshot/all-reservations.png)

### Successful Booking
![Successful Booking](https://raw.githubusercontent.com/ONORE-AKORTIA/HOTEL-RESERVATION-SYSTEM/main/screenshot/successful-booking.png)

### Reservation Cancellation
![Reservation Cancellation](https://raw.githubusercontent.com/ONORE-AKORTIA/HOTEL-RESERVATION-SYSTEM/main/screenshot/reservation-cancellation.png)
---

## 🔑 Demo Credentials
Role	Username	Password
**Administrator**	`admin`	`admin123`
**Guest**	`john`	`john123`

## 🚀 How to Run the Application
### Prerequisites
- **Java 17 or higher** installed
- **JavaFX SDK** downloaded and extracted

### Step 1: Download JavaFX SDK
Download from: [https://gluonhq.com/products/javafx/](https://gluonhq.com/products/javafx/)
Extract to: `C:\\javafx-sdk-21
### Step 2: Clone or Download the Repository

```bash
git clone https://github.com/ONORE-AKORTIA/hotel-reservation-system.git

cd hotel-reservation-system

Step 3: Compile the Application

bash
javac -cp ".;C:\\javafx-sdk-21\\lib\\\*" src\\com\\hotel\\\*.java src\\com\\hotel\\model\\\*.java src\\com\\hotel\\controller\\\*.java src\\com\\hotel\\view\\\*.java

Step 4: Run the Application
bash
java -cp ".;C:\\javafx-sdk-21\\lib\\\*" --module-path C:\\javafx-sdk-21\\lib --add-modules javafx.controls,javafx.fxml com.hotel.Main

📚 OOP Concepts Implemented

Concept	Implementation
Encapsulation	Private fields with public getters/setters in all model classes
Inheritance	Guest and Admin extend User; SingleRoom, DoubleRoom, SuiteRoom extend Room
Polymorphism		Overridden methods (calculatePrice(), getAmenities(), getRole())
Abstraction	Abstract User and Room classes with abstract methods

🧪 Features

Guest Features

✅ View available rooms

✅ Book a room with date selection

✅ View personal reservation history

✅ Cancel reservations

✅ Loyalty points tracking



Admin Features

✅ View all rooms and availability status

✅ Manage room inventory

✅ View all reservations

✅ System statistics dashboard

✅ User management


⚠️ Exception Handling

The application includes comprehensive validation:



Login credential validation

Date validation (check-in not in past, check-out after check-in)

Room availability checks

Proper error messages for invalid inputs


👤 Author

ONORE AKORTIA

Student ID: MS/ITE/25/0035

MSc Information Technology

University of Cape Coast



📅 Course Information

Course: INF811D - Object Oriented Programming

Program: MSc Information Technology

Semester: 1, 2025/2026 Academic Year



📝 License

This project is developed for academic purposes as part of the course requirements for INF811D at the University of Cape Coast.



🙏 Acknowledgements

University of Cape Coast - College of Distance Education (CoDE)

Department of Science and Mathematics

Course Instructor(s) for INF811D


📬 Contact

For any questions or feedback, please contact:

Email: \[Your Email Address]


© 2026 ONORE AKORTIA | University of Cape Coast






