package com.alexosta.ostapjuks_pizzeriaRegister.model;

import com.alexosta.ostapjuks_pizzeriaRegister.Main;
import com.alexosta.ostapjuks_pizzeriaRegister.service.DBProduct;
import com.jfoenix.controls.JFXRadioButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IngredientBox {

    ToggleGroup group = new ToggleGroup();
    private String selectedIngredient;

    private List<TextField> textFieldQuantities = new ArrayList<>();

    public HBox createBox(String ingredient, int quantity) {

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.getStyleClass().add("hBox-selectable");
        hbox.setMinSize(-1, -1);
        hbox.setPrefSize(383, 67);
        hbox.setSpacing(20);
        hbox.setPadding(new Insets(0, 20, 0, 20));

        Label labelIngredient = new Label(ingredient);
        labelIngredient.setFont(new Font(15));
        labelIngredient.setMinWidth(Region.USE_PREF_SIZE);

        TextField textFieldQuantity = new TextField(String.valueOf(quantity));
        textFieldQuantity.setFont(new Font(15));
        textFieldQuantity.setMaxWidth(Region.USE_COMPUTED_SIZE);
        textFieldQuantities.add(textFieldQuantity);

        JFXRadioButton radioButton = new JFXRadioButton("");
        radioButton.setToggleGroup(group);

        radioButton.setPrefWidth(Region.USE_COMPUTED_SIZE);

        radioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                selectedIngredient = ingredient;
            }
        });

        Button updateButton = new Button("Update");
        updateButton.setMinWidth(Region.USE_PREF_SIZE);
        updateButton.setOnAction(event -> {
            DBProduct.updateIngredientQuantityInDatabase(ingredient, Integer.parseInt(textFieldQuantity.getText()));
            System.out.println("Updating quantity for " + ingredient + " to " + textFieldQuantity.getText());
        });

        hbox.getChildren().addAll(labelIngredient, textFieldQuantity, updateButton, radioButton);
        return hbox;
    }

    public String getSelectedIngredient() {
        return selectedIngredient;
    }
}
