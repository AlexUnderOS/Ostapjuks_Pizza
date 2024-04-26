package com.alexosta.ostapjuks_pizza;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class FXDialogue {
    public void showOptionalDialogue(String title, String header, String content,
                                     String btn1_text, String btn2_text, String btn3_text) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        ButtonType buttonTypeOne = new ButtonType(btn1_text);
        ButtonType buttonTypeTwo = new ButtonType(btn2_text);
        ButtonType buttonTypeThree = new ButtonType(btn3_text);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){

        } else if (result.get() == buttonTypeTwo) {

        } else if (result.get() == buttonTypeThree) {

        } else {

        }
    }

    public void showLoginDialog(String title, String headerText, String correctUsername, String correctPassword) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        usernameField.setPromptText("Username");
        passwordField.setPromptText("Password");

        VBox vbox = new VBox(usernameField, passwordField);
        vbox.setSpacing(5);
        dialog.getDialogPane().setContent(vbox);

        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        EventHandler<ActionEvent> filter = event -> {
            if (!passwordField.getText().equals(correctPassword) ||
                    !usernameField.getText().equals(correctUsername)) {
                System.exit(0);
            }
        };

        Button okButton = (Button) dialog.getDialogPane().lookupButton(loginButtonType);
        Button cancelButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.CANCEL);
        okButton.addEventFilter(ActionEvent.ACTION, filter);
        cancelButton.addEventFilter(ActionEvent.ACTION, filter);

        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.setOnCloseRequest(event -> {
            System.exit(0);
        });

        dialog.showAndWait();
    }

    public void showRegistrationDialog(String title, String headerText) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        PasswordField paswConfirmField = new PasswordField();
        usernameField.setPromptText("Username");
        passwordField.setPromptText("Password");
        paswConfirmField.setPromptText("Confirm");

        VBox vbox = new VBox(usernameField, passwordField, paswConfirmField);
        vbox.setSpacing(5);
        dialog.getDialogPane().setContent(vbox);

        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.setOnCloseRequest(event -> {
            System.exit(0);
        });

        dialog.showAndWait();
    }
}