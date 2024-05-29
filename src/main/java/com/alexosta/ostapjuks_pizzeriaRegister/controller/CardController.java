package com.alexosta.ostapjuks_pizzeriaRegister.controller;

import com.alexosta.ostapjuks_pizzeriaRegister.Main;
import com.alexosta.ostapjuks_pizzeriaRegister.service.DBCard;
<<<<<<< HEAD
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
=======
import com.alexosta.ostapjuks_pizzeriaRegister.view.Receipts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.control.TextField;
import javafx.scene.control.Label;
>>>>>>> feat/receipts
import java.io.IOException;

public class CardController {

    @FXML
    private TextField numberTextField;

    @FXML
    private Label balanceLabel;

<<<<<<< HEAD
=======
    @FXML
    private Label identityNum;

    @FXML
    private VBox transferBlockVBox;

    private NewOrderController newOrderController;
>>>>>>> feat/receipts

    @FXML
    private void initialize() {
        reloadCardStatus();
<<<<<<< HEAD
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
=======
        newOrderController = NewOrderController.getInstance();
        String randIdentityNum = newOrderController.getRandIdentityNumber();
        identityNum.setText(randIdentityNum);

        transferBlockVBox.setVisible(false);
    }

    @FXML
    private void confirmTransfer() throws IOException {
        reloadCardStatus();
        newOrderController.buyOrder();
    }

    @FXML
    private void cancelTransfer() {

    }

    private void reloadCardStatus() {
        String firstCardNumber = DBCard.getFirstCardNumber();
        System.out.println(firstCardNumber);
>>>>>>> feat/receipts
        double cardBalance = DBCard.getBalance(firstCardNumber);

        balanceLabel.setText(String.valueOf(cardBalance));
        numberTextField.setText(firstCardNumber);
    }

<<<<<<< HEAD

=======
    public void showTransferBlock() {
        transferBlockVBox.setVisible(true);
    }
>>>>>>> feat/receipts
}
