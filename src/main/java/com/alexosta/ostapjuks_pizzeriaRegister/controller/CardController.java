package com.alexosta.ostapjuks_pizzeriaRegister.controller;

import com.alexosta.ostapjuks_pizzeriaRegister.service.DBCard;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.IOException;
import java.text.DecimalFormat;

public class CardController {

    private NewOrderController newOrderController;
    private final Stage stage = new Stage();

    @FXML
    private TextField numberTextField;

    @FXML
    private Label balanceLabel;

    @FXML
    private Label identityNum;

    @FXML
    private VBox transferBlockVBox;

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
    private void clickToAddMoney() {
        double currentBalance = DBCard.getBalance(DBCard.getFirstCardNumber()) + 10;
        DBCard.setBalance(DBCard.getFirstCardNumber(), currentBalance);
        reloadCardStatus();
    }

    private void reloadCardStatus() {
        String firstCardNumber = DBCard.getFirstCardNumber();
        System.out.println(firstCardNumber);
        double cardBalance = DBCard.getBalance(firstCardNumber);
        setBalanceLabel(cardBalance);

        numberTextField.setText(firstCardNumber);
    }

    public void showTransferBlock() {
        transferBlockVBox.setVisible(true);
    }

    public Stage getStage() {
        return stage;
    }

    public void setBalanceLabel(double balance) {
        String convertedBalanceText = new DecimalFormat("#00.00").format(balance);
        balanceLabel.setText(convertedBalanceText + " EURO");
    }
}
