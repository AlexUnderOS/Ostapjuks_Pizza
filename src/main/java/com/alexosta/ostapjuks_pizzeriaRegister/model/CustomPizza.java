package com.alexosta.ostapjuks_pizzeriaRegister.model;

import com.alexosta.ostapjuks_pizzeriaRegister.controller.CustomPizzaController;
import com.alexosta.ostapjuks_pizzeriaRegister.controller.NewOrderController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomPizza {

    private ImageView productImage;

    private ObservableList<CheckBox> checkBoxes = FXCollections.observableArrayList();
    private HashMap<String, String> pizzaMap = new HashMap<>();
    private HashMap<String, ImageView> imageMap = new HashMap<>();
    private List<String> selectedProducts = new ArrayList<>();

    private void createProductImage(String imagePath) {
        productImage = new ImageView(new Image(getClass().getResource("UI/imgs/"+imagePath).toExternalForm()));
        CustomPizzaController controller = CustomPizzaController.getInstance();
        setSizeOfImageView(controller);
        setPosOfImageView(controller);
    }

    public void createCustomPizza() {
        NewOrderController newOrderController = NewOrderController.getInstance();
        newOrderController.initialize();
        HBox container = newOrderController.productHBox;
        fillBoxWithCustomPizzaData(container);
    }

    private void fillBoxWithCustomPizzaData(HBox container) {
        String line = formatInOneLine();
//        new ProductContainer(container, "Custom Pizza", line, "custom_pizza/_dough.png", 100);
    }

    private String formatInOneLine() {
        StringBuilder line = new StringBuilder();
        int size = selectedProducts.size();
        for (int i = 0; i < size; i++) {
            line.append(selectedProducts.get(i));
        }
        return line.toString();
    }

    private void setSizeOfImageView(CustomPizzaController controller) {
        productImage.setFitWidth(controller.getDoughSizeWidth());
        productImage.setFitHeight(controller.getDoughSizeHeight());
    }

    private void setPosOfImageView(CustomPizzaController controller) {
        productImage.setLayoutX(controller.getDoughPositionX());
        productImage.setLayoutY(controller.getDoughPositionY());
    }

    private void addImageToContainer(AnchorPane container) {
        container.getChildren().add(productImage);
    }


}
