package com.alexosta.ostapjuks_pizzeriaRegister.controller;

import com.alexosta.ostapjuks_pizzeriaRegister.model.CustomPizza;
import com.alexosta.ostapjuks_pizzeriaRegister.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CustomPizzaController {

    private static CustomPizzaController instance;

    @FXML
    private VBox productListVBox;

    @FXML
    private AnchorPane imageContainerAnchorPane;

    @FXML
    private ImageView doughImageView;

    @FXML
    private void initialize() {
        instance = this;
    }

    public void showCustomPizzaScene() throws IOException {
        Stage newStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("custom-pizza-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(
                Main.class.getResource("UI/creator-styles.css")).toExternalForm());

        newStage.setTitle("Create Custom Pizza!");
        newStage.setScene(scene);
        newStage.show();
    }

    public void callCreateCustomPizza() {
        CustomPizza customPizza = new CustomPizza();
        customPizza.createCustomPizza();
    }

    public void backToNewOrder() {
        Stage currentStage = (Stage) productListVBox.getScene().getWindow();
        currentStage.close();
    }

    public double getDoughPositionX() {
        return doughImageView.getLayoutX();
    }

    public double getDoughPositionY() {
        return doughImageView.getLayoutY();
    }

    public int getDoughSizeWidth() {
        return (int) doughImageView.getFitWidth();
    }

    public int getDoughSizeHeight() {
        return (int) doughImageView.getFitHeight();
    }

    public static CustomPizzaController getInstance() {
        return instance;
    }
}
