package com.alexosta.ostapjuks_pizzeriaRegister.view;

import com.alexosta.ostapjuks_pizzeriaRegister.Main;
import com.alexosta.ostapjuks_pizzeriaRegister.controller.MenuController;
import com.alexosta.ostapjuks_pizzeriaRegister.service.DBWorkers;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class LoginDialog {

    private TextField usernameField;
    private PasswordField passwordField;
    private Button okButton;
    private boolean dbPassChecked, dbUserChecked, dbIsAdminChecker;
    private Stage primaryStage;

    public LoginDialog() {
        Platform.runLater(() -> {
            primaryStage = new Stage();
            initializeFields();
            initializeOkButton();
        });
    }

    private void initializeFields() {
        usernameField = new TextField();
        usernameField.setPromptText("Username");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
    }

    private void initializeOkButton() {
        okButton = new Button("Login");
        okButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            getData(username, password);
            if (dbPassChecked && dbUserChecked) {
                primaryStage.close();
            }
        });
    }

    public void showLoginDialog(boolean closeEvent) {
        VBox root = new VBox();

        root.getChildren().addAll(usernameField, passwordField, okButton);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 300, 200);
        scene.getStylesheets().add(Objects.requireNonNull(
                Main.class.getResource("UI/login-styles.css")).toExternalForm());

        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);

        if (closeEvent) {
            primaryStage.setOnCloseRequest(event -> {
                Platform.exit();
            });
        } else {
            primaryStage.setOnCloseRequest(event -> {
                primaryStage.close();
            });
        }
        primaryStage.showAndWait();
    }

    public void getData(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        dbUserChecked = DBWorkers.checkNameExists(username);
        dbPassChecked = DBWorkers.checkPassExists(password);
        dbIsAdminChecker = DBWorkers.checkAdminStatus(username);
    }

    public boolean getLoginPriority() {
        return dbIsAdminChecker;
    }

}