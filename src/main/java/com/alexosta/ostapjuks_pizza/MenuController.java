package com.alexosta.ostapjuks_pizza;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.util.Duration;

public class MenuController {

    @FXML
    private Button cash_btn, card_btn;

    @FXML
    public ListView<String> prod_listView;

    @FXML
    public void initialize() {
        Products prods = new Products();
        prod_listView.getItems().addAll(prods.giveDataToListView().getItems());
        setAllResizableBtns();
    }

    private void setAllResizableBtns(){
        setButtonHoverHandlers(cash_btn);
        setButtonHoverHandlers(card_btn);
    }

    private void setButtonHoverHandlers(Button button) {
        button.setOnMouseEntered(event -> animateUsingScaleTransition(button, 0.1, true));
        button.setOnMouseExited(event -> animateUsingScaleTransition(button, 0.2, false));
    }

    private void animateUsingScaleTransition(Button obj, double speed, boolean isEntered) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(speed), obj);
        scaleTransition.setToX(isEntered ? 1.05 : 1.0);
        scaleTransition.setToY(isEntered ? 1.05 : 1.0);
        scaleTransition.play();
    }
}