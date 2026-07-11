package com.hotel;

import com.hotel.controller.HotelController;
import com.hotel.view.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    private static HotelController controller;
    private static Stage primaryStage;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        controller = new HotelController();
        
        // Show Login Screen
        showLoginScreen();
        
        stage.setTitle("Grand Palace Hotel - Reservation System");
        stage.setResizable(false);
        stage.show();
    }

    public static void showLoginScreen() {
        LoginView loginView = new LoginView(controller);
        Scene scene = new Scene(loginView.getView(), 450, 350);
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}