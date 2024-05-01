package com.alexosta.ostapjuks_pizza;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CustomPizzaController {

    private static CustomPizzaController instance;

    @FXML
    private VBox productList_vBox;

    @FXML
    private AnchorPane imageContainer_anchorPane;

    @FXML
    private ImageView dough_ImageView;

    @FXML
    private void initialize() {
        instance = this;
        CustomPizza jsonHandler = new CustomPizza();
        jsonHandler.fillListViewWithJSONData(productList_vBox, "custom-pizza", imageContainer_anchorPane);
    }

    public void showCustomPizzaScene() throws IOException {
        Stage newStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(PizzeriaSelector.class.getResource("custom-pizza-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(
                getClass().getResource("UI/creator-styles.css")).toExternalForm());

        newStage.setTitle("Create Custom Pizza!");
        newStage.setScene(scene);
        newStage.show();
    }

    public void callCreateCustomPizza() {
        CustomPizza customPizza = new CustomPizza();
        customPizza.createCustomPizza();
    }

    public void backToNewOrder() {
        Stage currentStage = (Stage) productList_vBox.getScene().getWindow();
        currentStage.close();
    }

    public double getDoughPositionX() {
        return dough_ImageView.getLayoutX();
    }

    public double getDoughPositionY() {
        return dough_ImageView.getLayoutY();
    }

    public int getDoughSizeWidth() {
        return (int) dough_ImageView.getFitWidth();
    }

    public int getDoughSizeHeight() {
        return (int) dough_ImageView.getFitHeight();
    }

    public static CustomPizzaController getInstance() {
        return instance;
    }
}
