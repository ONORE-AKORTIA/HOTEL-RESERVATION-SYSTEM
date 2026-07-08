package com.hotel.view;

import com.hotel.Main;
import com.hotel.controller.HotelController;
import com.hotel.model.*;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDate;

public class GuestDashboardView {
    private HotelController controller;
    private VBox view;
    private TableView<Reservation> reservationTable;
    private ComboBox<Room> roomComboBox;
    private DatePicker checkInPicker;
    private DatePicker checkOutPicker;

    public GuestDashboardView(HotelController controller) {
        this.controller = controller;
        buildView();
    }

    private void buildView() {
        view = new VBox(10);
        view.setPadding(new Insets(20));

        // Header
        HBox header = createHeader();
        
        // Main content
        TabPane tabPane = new TabPane();
        
        // Tab 1: Available Rooms & Booking
        Tab bookTab = new Tab("Book Room", createBookingTab());
        bookTab.setClosable(false);
        
        // Tab 2: My Reservations
        Tab reservationsTab = new Tab("My Reservations", createReservationsTab());
        reservationsTab.setClosable(false);
        
        tabPane.getTabs().addAll(bookTab, reservationsTab);
        
        view.getChildren().addAll(header, tabPane);
    }

    private HBox createHeader() {
        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(0, 0, 20, 0));

        Label title = new Label("Guest Dashboard");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Guest guest = (Guest) controller.getCurrentUser();
        Label welcome = new Label("Welcome, " + guest.getFullName());
        welcome.setFont(Font.font("Arial", 14));

        Button logoutBtn = new Button("Logout");
        logoutBtn.setStyle("-fx-background-color: #E74C3C; -fx-text-fill: white;");
        logoutBtn.setOnAction(e -> {
            controller.logout();
            Main.showLoginScreen();
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        header.getChildren().addAll(title, welcome, spacer, logoutBtn);
        return header;
    }

    private VBox createBookingTab() {
        VBox bookingView = new VBox(15);
        bookingView.setPadding(new Insets(20));

        // Room selection
        Label roomLabel = new Label("Select Room:");
        roomComboBox = new ComboBox<>();
        roomComboBox.setItems(controller.getAvailableRooms());
        roomComboBox.setCellFactory(lv -> new ListCell<Room>() {
            @Override
            protected void updateItem(Room room, boolean empty) {
                super.updateItem(room, empty);
                setText(empty || room == null ? "" : room.toString());
            }
        });
        roomComboBox.setButtonCell(new ListCell<Room>() {
            @Override
            protected void updateItem(Room room, boolean empty) {
                super.updateItem(room, empty);
                setText(empty || room == null ? "" : room.getRoomNumber() + " - " + room.getRoomType());
            }
        });
        roomComboBox.setPrefWidth(300);

        // Date selection
        Label checkInLabel = new Label("Check-in Date:");
        checkInPicker = new DatePicker(LocalDate.now().plusDays(1));
        checkInPicker.setPrefWidth(200);

        Label checkOutLabel = new Label("Check-out Date:");
        checkOutPicker = new DatePicker(LocalDate.now().plusDays(3));
        checkOutPicker.setPrefWidth(200);

        // Room details display
        TextArea roomDetails = new TextArea();
        roomDetails.setPrefHeight(100);
        roomDetails.setEditable(false);
        roomDetails.setPromptText("Select a room to view details");

        roomComboBox.setOnAction(e -> {
            Room selected = roomComboBox.getSelectionModel().getSelectedItem();
            if (selected != null) {
                roomDetails.setText("Room: " + selected.getRoomNumber() + 
                                  "\nType: " + selected.getRoomType() + 
                                  "\nPrice: $" + selected.getBasePrice() + "/night" +
                                  "\nCapacity: " + selected.getCapacity() + " guests" +
                                  "\nAmenities: " + selected.getAmenities());
                
                // Calculate total price
                if (checkInPicker.getValue() != null && checkOutPicker.getValue() != null) {
                    int nights = (int) java.time.temporal.ChronoUnit.DAYS.between(
                        checkInPicker.getValue(), checkOutPicker.getValue());
                    if (nights > 0) {
                        double total = selected.calculatePrice(nights);
                        roomDetails.appendText("\n\nTotal for " + nights + " nights: $" + total);
                    }
                }
            }
        });

        // Book button
        Button bookButton = new Button("Book Now");
        bookButton.setStyle("-fx-background-color: #27AE60; -fx-text-fill: white; -fx-font-weight: bold;");
        bookButton.setPrefWidth(200);
        bookButton.setOnAction(e -> bookRoom());

        // Layout
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.add(roomLabel, 0, 0);
        grid.add(roomComboBox, 1, 0);
        grid.add(checkInLabel, 0, 1);
        grid.add(checkInPicker, 1, 1);
        grid.add(checkOutLabel, 0, 2);
        grid.add(checkOutPicker, 1, 2);
        grid.add(bookButton, 1, 3);

        bookingView.getChildren().addAll(grid, roomDetails);

        return bookingView;
    }

    private VBox createReservationsTab() {
        VBox reservationsView = new VBox(15);
        reservationsView.setPadding(new Insets(20));

        Label title = new Label("My Reservations");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        reservationTable = new TableView<>();
        reservationTable.setPrefHeight(300);

        // Columns
        TableColumn<Reservation, Integer> idCol = new TableColumn<>("Reservation ID");
        idCol.setCellValueFactory(cell -> 
            new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getReservationId()).asObject());

        TableColumn<Reservation, Integer> roomCol = new TableColumn<>("Room");
        roomCol.setCellValueFactory(cell -> 
            new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getRoom().getRoomNumber()).asObject());

        TableColumn<Reservation, String> checkInCol = new TableColumn<>("Check-in");
        checkInCol.setCellValueFactory(cell -> 
            new javafx.beans.property.SimpleStringProperty(cell.getValue().getCheckInDate().toString()));

        TableColumn<Reservation, String> checkOutCol = new TableColumn<>("Check-out");
        checkOutCol.setCellValueFactory(cell -> 
            new javafx.beans.property.SimpleStringProperty(cell.getValue().getCheckOutDate().toString()));

        TableColumn<Reservation, Double> totalCol = new TableColumn<>("Total Price");
        totalCol.setCellValueFactory(cell -> 
            new javafx.beans.property.SimpleDoubleProperty(cell.getValue().getTotalPrice()).asObject());

        TableColumn<Reservation, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(cell -> 
            new javafx.beans.property.SimpleStringProperty(cell.getValue().getStatus()));

        reservationTable.getColumns().addAll(idCol, roomCol, checkInCol, checkOutCol, totalCol, statusCol);

        // Cancel button
        Button cancelButton = new Button("Cancel Selected Reservation");
        cancelButton.setStyle("-fx-background-color: #E74C3C; -fx-text-fill: white;");
        cancelButton.setOnAction(e -> cancelReservation());

        // Refresh button
        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> {
       reservationTable.getItems().clear();
       loadReservations();
       reservationTable.refresh();
     });

        HBox buttonBox = new HBox(10, refreshButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER_LEFT);

        reservationsView.getChildren().addAll(title, reservationTable, buttonBox);

        loadReservations();
        return reservationsView;
    }

    private void loadReservations() {
        reservationTable.setItems(controller.getCurrentUserReservations());
    }

    private void bookRoom() {
        try {
            Room selected = roomComboBox.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showAlert("Error", "Please select a room");
                return;
            }

            LocalDate checkIn = checkInPicker.getValue();
            LocalDate checkOut = checkOutPicker.getValue();

            if (checkIn == null || checkOut == null) {
                showAlert("Error", "Please select check-in and check-out dates");
                return;
            }

            if (checkIn.isBefore(LocalDate.now())) {
                showAlert("Error", "Check-in date cannot be in the past");
                return;
            }

            if (checkOut.isBefore(checkIn) || checkOut.equals(checkIn)) {
                showAlert("Error", "Check-out must be after check-in");
                return;
            }

            Reservation reservation = controller.bookRoom(
                selected.getRoomNumber(), checkIn, checkOut
            );

            showAlert("Success", "Room booked successfully!\nReservation ID: " + 
                     reservation.getReservationId() + 
                     "\nTotal: $" + reservation.getTotalPrice());

            // Refresh views
            roomComboBox.setItems(controller.getAvailableRooms());
            loadReservations();

        } catch (Exception ex) {
            showAlert("Error", ex.getMessage());
        }
    }
private void cancelReservation() {
    Reservation selected = reservationTable.getSelectionModel().getSelectedItem();
    if (selected == null) {
        showAlert("Error", "Please select a reservation to cancel");
        return;
    }

    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
    confirm.setTitle("Confirm Cancellation");
    confirm.setContentText("Are you sure you want to cancel this reservation?");
    
    if (confirm.showAndWait().get() == ButtonType.OK) {
        try {
            controller.cancelReservation(selected.getReservationId());
            showAlert("Success", "Reservation cancelled successfully");
            loadReservations();  // ← REFRESHES THE TABLE
            roomComboBox.setItems(controller.getAvailableRooms());  // ← UPDATES AVAILABLE ROOMS
        } catch (Exception ex) {
            showAlert("Error", ex.getMessage());
        }
    }
}
   
    private void showAlert(String title, String content) {
        Alert alert = new Alert(
            title.equals("Error") ? Alert.AlertType.ERROR : Alert.AlertType.INFORMATION
        );
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public VBox getView() { return view; }
}
