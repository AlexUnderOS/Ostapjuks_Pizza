package com.alexosta.ostapjuks_pizza;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ResultConfirming {

    boolean discountCard;
    public void createResult(Label selectedProducts, TextArea calculating_textArea) {
        NewOrderController controller = new NewOrderController().getInstance();
        discountCard = controller.getDiscountCard();

        Receipts receipts = new Receipts();
    }
}
