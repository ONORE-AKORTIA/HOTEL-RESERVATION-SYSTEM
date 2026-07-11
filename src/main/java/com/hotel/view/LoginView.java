package com.hotel.view;

import com.hotel.MainApp;
import com.hotel.controller.HotelController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LoginView {
    private HotelController controller;
    private VBox view;

    public LoginView(HotelController controller) {
        this.controller = controller;
        buildView();
    }

    private void buildView() {
        view = new VBox(20);
        view.setAlignment(Pos.CENTER);
        view.setPadding(new Insets(40));

        // Title
        Text title = new Text("Hotel Reservation System");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Text subtitle = new Text("Grand Palace Hotel");
        subtitle.setFont(Font.font("Arial", 16));

        // Login Form
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(15);
        grid.setAlignment(Pos.CENTER);

        Label userLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(200);

        Label passLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(200);

        Button loginButton = new Button("Login");
        loginButton.setPrefWidth(200);
        loginButton.setStyle("-fx-background-color: #2E86C1; -fx-text-fill: white; -fx-font-weight: bold;");

        Label statusLabel = new Label();
        statusLabel.setStyle("-fx-text-fill: red;");

        grid.add(userLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);

        // Hyperlink for demo credentials
        Hyperlink demoLink = new Hyperlink("Demo: admin/admin123 or john/john123");
        demoLink.setStyle("-fx-font-size: 12px;");

        view.getChildren().addAll(title, subtitle, grid, statusLabel, demoLink);

        // Event Handling
        loginButton.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            if (username.isEmpty() || password.isEmpty()) {
                statusLabel.setText("Please enter username and password");
                return;
            }

            try {
                if (controller.login(username, password)) {
                    statusLabel.setStyle("-fx-text-fill: green;");
                    statusLabel.setText("Login successful!");
                    
                    // Navigate to appropriate dashboard
                    if (controller.isAdmin()) {
                        AdminDashboardView adminView = new AdminDashboardView(controller);
                        MainApp.getPrimaryStage().setScene(new Scene(adminView.getView(), 800, 600));
                    } else {
                        GuestDashboardView guestView = new GuestDashboardView(controller);
                        MainApp.getPrimaryStage().setScene(new Scene(guestView.getView(), 800, 600));
                    }
                } else {
                    statusLabel.setStyle("-fx-text-fill: red;");
                    statusLabel.setText("Invalid username or password");
                }
            } catch (Exception ex) {
                statusLabel.setStyle("-fx-text-fill: red;");
                statusLabel.setText("Error: " + ex.getMessage());
            }
        });

        // Enter key shortcut
        passwordField.setOnAction(e -> loginButton.fire());
        usernameField.setOnAction(e -> passwordField.requestFocus());
    }

    public VBox getView() { return view; }
}