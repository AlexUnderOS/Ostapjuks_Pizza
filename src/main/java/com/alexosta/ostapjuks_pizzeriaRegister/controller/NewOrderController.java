package com.alexosta.ostapjuks_pizzeriaRegister.controller;

import com.alexosta.ostapjuks_pizzeriaRegister.*;
import com.alexosta.ostapjuks_pizzeriaRegister.model.ProductContainer;
import com.alexosta.ostapjuks_pizzeriaRegister.service.DBProduct;
import com.alexosta.ostapjuks_pizzeriaRegister.view.Receipts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class NewOrderController {

    private static NewOrderController instance;
    private ProductContainer container;


    @FXML
    public ListView<String> listOfProducts;
    @FXML
    public HBox productHBox;

    @FXML
    private Label selectedProductLabel, totalPriceLabel;

    @FXML
    private ImageView productImageView;

    private String selectedItemFromProductList;


    @FXML
    public void initialize() {
        container = new ProductContainer();
        instance = this;
        updateProductListInHBox();

        listOfProducts.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedItemFromProductList = newValue;
            updateSelectedProductLabel(selectedItemFromProductList);
        });
    }

    private void updateProductListInHBox() {
        productHBox.getChildren().clear();
        List<String> categoryList = DBProduct.getProductCategoryFromDatabase();
        List<String> productList = DBProduct.getProductNameFromDatabase();
        List<Double> priceList = DBProduct.getProductPriceFromDatabase();
        List<Integer> minList = DBProduct.getProductMinutesFromDatabase();
        List<String> imgLink = DBProduct.getProductImageFromDatabase();
        List<String> ingredientList = DBProduct.getProductIngredientListFromDatabase();

        container = new ProductContainer();
        for (int i = 0; i < categoryList.size(); i++) {
            VBox vBox = container.setProductContainer(
                    categoryList.get(i), priceList.get(i), imgLink.get(i),
                    productList.get(i), ingredientList.get(i), minList.get(i));

            productHBox.getChildren().add(vBox);
        }
    }

    @FXML
    private void confirmOrderResultBtn() throws IOException {
        CardController controller = new CardController();
        controller.showCardScene();
        new Receipts(container);
    }

    @FXML
    private void onShowPayTab() {
        updateListOfProducts();
    }

    private void updateListOfProducts() {
        listOfProducts.getItems().clear();
        List<String> items = container.getSelectedProducts();
        double totalPrice = 0;

        for (String item : items) {
            List<String> productNames = DBProduct.getProductNameFromDatabase();
            int index = productNames.indexOf(item);

            if (index != -1) {
                List<Double> prices = DBProduct.getProductPriceFromDatabase();
                double price = prices.get(index);
                totalPrice += price;
            }
        }
        totalPriceLabel.setText("Total Price: " + totalPrice + " EURO");
    }

    private void updateSelectedProductLabel(String productName) {
        double price = 0;
        int min = 0;
        String listOfIngredients = "";

        List<String> productNames = DBProduct.getProductNameFromDatabase();
        int index = productNames.indexOf(productName);

        if (index != -1) {
            List<Double> prices = DBProduct.getProductPriceFromDatabase();
            List<Integer> minutes = DBProduct.getProductMinutesFromDatabase();
            List<String> ingredients = DBProduct.getProductIngredientListFromDatabase();
            List<String> imgs = DBProduct.getProductImageFromDatabase();

            price = prices.get(index);
            min = minutes.get(index);
            listOfIngredients = ingredients.get(index);
            String imgPath = imgs.get(index);

            try {
                Image image = new Image(imgPath);
                productImageView.setImage(image);
            }catch (NullPointerException ex) {
                productImageView.setImage(null);
            }
        }

        String result =
                "Product: " + productName + "\n" +
                "Price: " + price + " EURO\n" +
                "Minutes: " + min + "\n" +
                "Ingredients: " + listOfIngredients;
        selectedProductLabel.setText(result);

    }

    @FXML
    private void backToMenu() throws IOException {
        Stage currentStage = (Stage) productHBox.getScene().getWindow();
        currentStage.close();

        Stage newStage = new MenuController().getStageOfMenuScene();

        newStage.show();
    }

//    public void callCustomPizzaScene() throws IOException {
//        CustomPizzaController controller = new CustomPizzaController();
//        controller.showCustomPizzaScene();
//    }
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