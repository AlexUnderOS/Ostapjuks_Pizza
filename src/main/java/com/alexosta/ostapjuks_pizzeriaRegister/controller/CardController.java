package com.alexosta.ostapjuks_pizzeriaRegister.controller;

import com.alexosta.ostapjuks_pizzeriaRegister.Main;
import com.alexosta.ostapjuks_pizzeriaRegister.service.DBCard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class CardController {

    @FXML
    private TextField numberTextField;

    @FXML
    private Label balanceLabel;


    @FXML
    private void initialize() {
        reloadCardStatus();
    }
    public void showCardScene() throws IOException {
        Stage newStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("card-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        newStage.setTitle("Create Custom Pizza!");
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    private void reloadCardStatus() {
        String firstCardNumber = DBCard.getFirstCardNumber();
        double cardBalance = DBCard.getBalance(firstCardNumber);

        balanceLabel.setText(String.valueOf(cardBalance));
        numberTextField.setText(firstCardNumber);
    }


}
