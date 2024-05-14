package com.alexosta.ostapjuks_pizzeriaRegister.controller;

import com.alexosta.ostapjuks_pizzeriaRegister.*;
import com.alexosta.ostapjuks_pizzeriaRegister.model.ProductContainer;
import com.alexosta.ostapjuks_pizzeriaRegister.service.DBProduct;
import com.alexosta.ostapjuks_pizzeriaRegister.view.Receipts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class NewOrderController {

    private static NewOrderController instance;

    @FXML
    public ListView<String> listOfProducts;
    @FXML
    public HBox productHBox;


    @FXML
    public void initialize() {
        instance = this;
        updateProductListInHBox();
    }

    private void updateProductListInHBox() {
        productHBox.getChildren().clear();
        List<String> categoryList = DBProduct.getProductCategoryFromDatabase();
        List<String> productList = DBProduct.getProductNameFromDatabase();
        List<Double> priceList = DBProduct.getProductPriceFromDatabase();
        List<Integer> minList = DBProduct.getProductMinutesFromDatabase();
        List<String> imgLink = DBProduct.getProductImageFromDatabase();
        List<String> ingredientList = DBProduct.getProductIngredientListFromDatabase();
        List<String> sizeList = DBProduct.getProductSizeFromDatabase();

        ProductContainer container = new ProductContainer();
        for (int i = 0; i < categoryList.size(); i++) {
            VBox vBox = container.setProductContainer(
                    categoryList.get(i), priceList.get(i), imgLink.get(i),
                    productList.get(i), ingredientList.get(i), minList.get(i), sizeList.get(i));

            productHBox.getChildren().add(vBox);
        }
    }

    @FXML
    private void confirmOrderResultBtn() throws IOException {
        System.out.println("We do something to confirm our order.");
        new Receipts();
    }

    @FXML
    private void reloadPage() {
        initialize();
    }

    @FXML
    private void backToMenu() throws IOException {
        Stage currentStage = (Stage) productHBox.getScene().getWindow();
        currentStage.close();

        Stage newStage = new MenuController().getStageOfMenuScene();

        newStage.show();
    }

    public void callCustomPizzaScene() throws IOException {
        CustomPizzaController controller = new CustomPizzaController();
        controller.showCustomPizzaScene();
    }
    public Stage createNewOrderScene() throws IOException {
        Stage newStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("new-order-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(
                Main.class.getResource("UI/styles.css")).toExternalForm());

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

}