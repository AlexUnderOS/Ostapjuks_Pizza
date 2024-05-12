package com.alexosta.ostapjuks_pizzeriaRegister.model;

import com.jfoenix.controls.JFXRadioButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class ProductBox {

    ToggleGroup group = new ToggleGroup();
    private String selectedProduct;

    public HBox createBox(String category, String product, double price, int min) {
        HBox outerHBox = new HBox();
        outerHBox.setPrefHeight(100.0);
        outerHBox.setPrefWidth(539.0);
        outerHBox.setSpacing(20.0);
        outerHBox.setAlignment(Pos.CENTER_LEFT);
        outerHBox.setPadding(new Insets(0, 20, 0, 20));
        outerHBox.getStyleClass().add("hBox-selectable");


        Label categoryLabel = new Label(category+":");
        Label productLabel = new Label(product.toUpperCase());
        Label priceLabel = new Label(price+" EUR");
        Label timeLabel = new Label(min+" min");
        JFXRadioButton radioButton = new JFXRadioButton();
        radioButton.setToggleGroup(group);

        radioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                selectedProduct = product;
            }
        });

        HBox innerHBox = new HBox();
        innerHBox.setPrefHeight(100.0);
        innerHBox.setPrefWidth(50.0);
        innerHBox.setAlignment(Pos.CENTER_RIGHT);

        Button editButton = new Button("edit");
        editButton.setMinHeight(-1);
        editButton.setMinWidth(-1);
        editButton.setPrefHeight(30.0);
        editButton.setPrefWidth(66.0);

        innerHBox.getChildren().add(editButton);

        outerHBox.getChildren().addAll(categoryLabel, productLabel, priceLabel, radioButton, timeLabel, innerHBox);

        return  outerHBox;
    }

    public String getSelectedProduct() {
        return selectedProduct;
    }
}
