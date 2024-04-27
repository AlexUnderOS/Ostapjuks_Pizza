package com.alexosta.ostapjuks_pizza;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class CustomDialog {

    private String username;
    private String password;
    private boolean dbApproved = false;

    public void showLoginDialog() {
        Stage primaryStage = new Stage();
        VBox root = new VBox();

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button okButton = new Button("OK");
        okButton.setOnAction(event -> {
            username = usernameField.getText();
            password = passwordField.getText();
            checkUsername(username);
            checkPassword(password);
            if (dbApproved) {
                primaryStage.close();
            }
        });

        root.getChildren().addAll(usernameField, passwordField, okButton);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 300, 200);
        scene.getStylesheets().add(Objects.requireNonNull(
                getClass().getResource("UI/login-styles.css")).toExternalForm()); // run css style

        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(windowEvent -> System.exit(0));
        primaryStage.showAndWait();
    }

    private void checkUsername(String check) {
        System.out.println(">> username checked - " + check);
        if (check.equals("admin")) {
            dbApproved = true;
        }
    }

    private void checkPassword(String check) {
        System.out.println(">> password checked - " + check);
        if (check.equals("admin")) {
            dbApproved = true;
        }
    }
}