package com.alexosta.ostapjuks_pizza;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class CustomPizza {

    private ImageView productImage;

    private ObservableList<CheckBox> checkBoxes = FXCollections.observableArrayList();
    private HashMap<String, String> pizzaMap = new HashMap<>();
    private HashMap<String, ImageView> imageMap = new HashMap<>();

    public void fillListViewWithJSONData(VBox productList_vBox, String JSONObjectName, AnchorPane imageContainer_anchorPane) {
        try {
            String content = new String(Files.readAllBytes(Paths.get("src/main/resources/com/alexosta/ostapjuks_pizza/JSON/ProductsInRegister.json")));
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(content);
            JSONArray products = (JSONArray) jsonObject.get(JSONObjectName);

            for (Object jo : products) {
                JSONObject product = (JSONObject) jo;

                String name = (String) product.get("name");
                String imagePath = (String) product.get("image");

                pizzaMap.put(name, imagePath);

                CheckBox checkBox = new CheckBox(name);
                checkBoxes.add(checkBox);
                productList_vBox.getChildren().add(checkBox);

            }
            checkBoxes.forEach(checkBox -> {
                checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                    String imagePath = pizzaMap.get(checkBox.getText());
                    System.out.println("Checkbox " + checkBox.getText() + " is selected: " + newValue);
                    ImageView imageToManage = imageMap.get(checkBox.getText());
                    if (newValue) {
                        createProductImage(imagePath);
                        imageMap.put(checkBox.getText(), productImage);
                        addImageToContainer(imageContainer_anchorPane);
                    }else {
                            // Удаляем изображение из контейнера
                            imageContainer_anchorPane.getChildren().remove(imageToManage);
                            System.out.println("Image removed from container.");
                    }
                });
            });

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void createProductImage(String imagePath) {
        productImage = new ImageView(new Image(getClass().getResource("UI/imgs/"+imagePath).toExternalForm()));
        CustomPizzaController controller = CustomPizzaController.getInstance();
        setSizeOfImageView(controller);
        setPosOfImageView(controller);
    }

    public void createCustomPizza() {
        NewOrderController newOrderController = NewOrderController.getInstance();
        newOrderController.initialize();
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