package com.alexosta.ostapjuks_pizzeriaRegister.view;

import com.alexosta.ostapjuks_pizzeriaRegister.Main;
import com.alexosta.ostapjuks_pizzeriaRegister.service.DBWorkers;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class RegisterDialog {

    private TextField usernameField;
    private PasswordField passwordField;
    private PasswordField confirmPasswordField;
    private Button okButton;
    private final Stage primaryStage = new Stage();



    public RegisterDialog() {
        initializeFields();
        initializeOkButton();
    }

    private void initializeFields() {
        usernameField = new TextField();
        usernameField.setPromptText("Username");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Password");
    }

    private void initializeOkButton() {
        okButton = new Button("Register");
        okButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            if (password.equals(confirmPassword) &&
                    !password.isEmpty() && !username.isEmpty()) {
                getData(username, password);
                primaryStage.close();
            }
        });
    }

    public void showRegisterDialog(boolean closeEvent) {
        VBox root = new VBox();

        root.getChildren().addAll(usernameField, passwordField, confirmPasswordField, okButton);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 300, 200);
        scene.getStylesheets().add(Objects.requireNonNull(
                Main.class.getResource("UI/login-styles.css")).toExternalForm());

        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);

        if (closeEvent) {
            primaryStage.setOnCloseRequest(event -> System.exit(0));
        }
        primaryStage.showAndWait();
    }

    public void getData(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        DBWorkers.writeToDatabase(username, password, false);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}