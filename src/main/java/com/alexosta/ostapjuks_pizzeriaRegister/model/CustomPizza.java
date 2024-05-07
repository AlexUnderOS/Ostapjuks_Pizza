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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
                        selectedProducts.add(checkBox.getText());
                        System.out.println(selectedProducts);
                        createProductImage(imagePath);
                        imageMap.put(checkBox.getText(), productImage);
                        addImageToContainer(imageContainer_anchorPane);
                    }else {
                        System.out.println(selectedProducts);
                        selectedProducts.remove(checkBox.getText());
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
        HBox container = newOrderController.getProductList_hBox();
        fillBoxWithCustomPizzaData(container);
    }

    private void fillBoxWithCustomPizzaData(HBox container) {
        String line = formatInOneLine();
        new ProductBox(container, "Custom Pizza", line, "custom_pizza/_dough.png", 100);
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
