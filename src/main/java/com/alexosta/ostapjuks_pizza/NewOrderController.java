package com.alexosta.ostapjuks_pizza;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class NewOrderController {

    private static NewOrderController instance;

    private Animations animations;

    @FXML
    private JFXButton cash_btn, card_btn;

    @FXML
    private HBox productList_hBox;

    @FXML
    public ListView<String> prod_listView;

    @FXML
    private Label paymentSelection_label; // not


    @FXML
    public void initialize() {
        instance = this;
        productList_hBox.getChildren().clear();
        fillBoxesJSONData();
        animations = new Animations();
        setAllResizableBtnsInNewOrder();

    }

    private void setAllResizableBtnsInNewOrder() {
        animations.setButtonHoverHandlers(cash_btn);
        animations.setButtonHoverHandlers(card_btn);
    }

    private void fillBoxesJSONData() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("src/main/resources/com/alexosta/ostapjuks_pizza/JSON/ProductsInRegister.json")));
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(content);
            JSONArray products = (JSONArray) jsonObject.get("pizzas");

            for (Object jo : products) {
                JSONObject product = (JSONObject) jo;

                String name = (String) product.get("name");
                String description = (String) product.get("description");
                String image = (String) product.get("image");
                int quantity = ((Number) product.get("quantity")).intValue();

                new ProductBox(productList_hBox, name, description, image, quantity);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void callCustomPizzaScene() throws IOException {
        CustomPizzaController controller = new CustomPizzaController();
        controller.showCustomPizzaScene();
//        Stage currentStage = (Stage) productList_hBox.getScene().getWindow();
//        currentStage.hide();
    }

    public Stage showNewOrderScene() throws IOException {
        Stage newStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(PizzeriaSelector.class.getResource("new-order-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(
                getClass().getResource("UI/styles.css")).toExternalForm());

        newStage.setTitle("New Order");
        newStage.setScene(scene);

        newStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
            System.exit(0);
        });
        return newStage;
    }

    public static NewOrderController getInstance() {
        return instance;
    }

    public HBox getProductList_hBox() {
        return productList_hBox;
    }
}