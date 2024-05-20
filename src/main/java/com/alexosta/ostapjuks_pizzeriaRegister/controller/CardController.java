package com.alexosta.ostapjuks_pizzeriaRegister.controller;

import com.alexosta.ostapjuks_pizzeriaRegister.Main;
import com.alexosta.ostapjuks_pizzeriaRegister.service.DBCard;
import com.alexosta.ostapjuks_pizzeriaRegister.view.Receipts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.IOException;

public class CardController {

    @FXML
    private TextField numberTextField;

    @FXML
    private Label balanceLabel;

    @FXML
    private Label identityNum;

    @FXML
    private VBox transferBlockVBox;

    private NewOrderController newOrderController;

    @FXML
    private void initialize() {
        reloadCardStatus();
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
        double cardBalance = DBCard.getBalance(firstCardNumber);

        balanceLabel.setText(String.valueOf(cardBalance));
        numberTextField.setText(firstCardNumber);
    }

    public void showTransferBlock() {
        transferBlockVBox.setVisible(true);
    }
}
