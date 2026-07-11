package com.hotel.view;

import com.hotel.MainApp;
import com.hotel.controller.HotelController;
import com.hotel.model.*;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AdminDashboardView {
    private HotelController controller;
    private VBox view;
    private TableView<Reservation> reservationTable;
    private TableView<Room> roomTable;
    private Label statsLabel;

    public AdminDashboardView(HotelController controller) {
        this.controller = controller;
        buildView();
    }

    private void buildView() {
        view = new VBox(10);
        view.setPadding(new Insets(20));

        // Header
        HBox header = createHeader();
        
        // Statistics
        statsLabel = new Label();
        statsLabel.setFont(Font.font("Arial", 14));
        updateStats();

        // Tab Pane
        TabPane tabPane = new TabPane();
        
        Tab roomsTab = new Tab("Room Management", createRoomsTab());
        roomsTab.setClosable(false);
        
        Tab reservationsTab = new Tab("All Reservations", createReservationsTab());
        reservationsTab.setClosable(false);
        
        tabPane.getTabs().addAll(roomsTab, reservationsTab);
        
        view.getChildren().addAll(header, statsLabel, tabPane);
    }

    private HBox createHeader() {
        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(0, 0, 20, 0));

        Label title = new Label("Admin Dashboard");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Admin admin = (Admin) controller.getCurrentUser();
        Label welcome = new Label("Welcome, " + admin.getFullName());
        welcome.setFont(Font.font("Arial", 14));

        Button logoutBtn = new Button("Logout");
        logoutBtn.setStyle("-fx-background-color: #E74C3C; -fx-text-fill: white;");
        logoutBtn.setOnAction(e -> {
            controller.logout();
            MainApp.showLoginScreen();
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        header.getChildren().addAll(title, welcome, spacer, logoutBtn);
        return header;
    }

    private void updateStats() {
        statsLabel.setText(String.format(
            "Total Rooms: %d | Available: %d | Booked: %d | Total Reservations: %d | Active: %d",
            controller.getTotalRooms(),
            controller.getAvailableRoomsCount(),
            controller.getBookedRoomsCount(),
            controller.getTotalReservations(),
            controller.getActiveReservations()
        ));
    }

    private VBox createRoomsTab() {
        VBox roomsView = new VBox(15);
        roomsView.setPadding(new Insets(20));

        // Room Table
        roomTable = new TableView<>();
        roomTable.setPrefHeight(250);

        TableColumn<Room, Integer> numCol = new TableColumn<>("Room #");
        numCol.setCellValueFactory(cell -> 
            new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getRoomNumber()).asObject());

        TableColumn<Room, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(cell -> 
            new javafx.beans.property.SimpleStringProperty(cell.getValue().getRoomType()));

        TableColumn<Room, Double> priceCol = new TableColumn<>("Base Price");
        priceCol.setCellValueFactory(cell -> 
            new javafx.beans.property.SimpleDoubleProperty(cell.getValue().getBasePrice()).asObject());

        TableColumn<Room, Integer> capacityCol = new TableColumn<>("Capacity");
        capacityCol.setCellValueFactory(cell -> 
            new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getCapacity()).asObject());

        TableColumn<Room, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(cell -> 
            new javafx.beans.property.SimpleStringProperty(
                cell.getValue().isAvailable() ? "Available" : "Booked"
            ));

        roomTable.getColumns().addAll(numCol, typeCol, priceCol, capacityCol, statusCol);
        roomTable.setItems(controller.getAllRooms());

        // Room details
        TextArea roomDetails = new TextArea();
        roomDetails.setPrefHeight(80);
        roomDetails.setEditable(false);

        roomTable.getSelectionModel().selectedItemProperty().addListener((obs, old, selected) -> {
            if (selected != null) {
                roomDetails.setText("Amenities: " + selected.getAmenities());
            }
        });

        // Buttons
        Button refreshRoomsBtn = new Button("Refresh");
        refreshRoomsBtn.setOnAction(e -> roomTable.setItems(controller.getAllRooms()));

        HBox buttonBox = new HBox(10, refreshRoomsBtn);
        
        roomsView.getChildren().addAll(roomTable, roomDetails, buttonBox);
        return roomsView;
    }

    private VBox createReservationsTab() {
        VBox reservationsView = new VBox(15);
        reservationsView.setPadding(new Insets(20));

        Label title = new Label("All Reservations");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        reservationTable = new TableView<>();
        reservationTable.setPrefHeight(300);

        // Columns
        TableColumn<Reservation, Integer> idCol = new TableColumn<>("Reservation ID");
        idCol.setCellValueFactory(cell -> 
            new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getReservationId()).asObject());

        TableColumn<Reservation, String> guestCol = new TableColumn<>("Guest");
        guestCol.setCellValueFactory(cell -> 
            new javafx.beans.property.SimpleStringProperty(cell.getValue().getGuest().getFullName()));

        TableColumn<Reservation, Integer> roomCol = new TableColumn<>("Room");
        roomCol.setCellValueFactory(cell -> 
            new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getRoom().getRoomNumber()).asObject());

        TableColumn<Reservation, String> checkInCol = new TableColumn<>("Check-in");
        checkInCol.setCellValueFactory(cell -> 
            new javafx.beans.property.SimpleStringProperty(cell.getValue().getCheckInDate().toString()));

        TableColumn<Reservation, String> checkOutCol = new TableColumn<>("Check-out");
        checkOutCol.setCellValueFactory(cell -> 
            new javafx.beans.property.SimpleStringProperty(cell.getValue().getCheckOutDate().toString()));

        TableColumn<Reservation, Double> totalCol = new TableColumn<>("Total");
        totalCol.setCellValueFactory(cell -> 
            new javafx.beans.property.SimpleDoubleProperty(cell.getValue().getTotalPrice()).asObject());

        TableColumn<Reservation, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(cell -> 
            new javafx.beans.property.SimpleStringProperty(cell.getValue().getStatus()));

        reservationTable.getColumns().addAll(idCol, guestCol, roomCol, checkInCol, checkOutCol, totalCol, statusCol);

        // Refresh button
        Button refreshBtn = new Button("Refresh");
        refreshBtn.setOnAction(e -> {
            reservationTable.setItems(controller.getCurrentUserReservations());
            updateStats();
        });

        reservationsView.getChildren().addAll(title, reservationTable, refreshBtn);
        refreshBtn.fire();
        return reservationsView;
    }

    public VBox getView() { return view; }
}