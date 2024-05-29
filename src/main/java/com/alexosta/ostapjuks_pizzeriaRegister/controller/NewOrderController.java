package com.alexosta.ostapjuks_pizzeriaRegister.controller;

import com.alexosta.ostapjuks_pizzeriaRegister.*;
import com.alexosta.ostapjuks_pizzeriaRegister.model.ProductContainer;
<<<<<<< HEAD
=======
import com.alexosta.ostapjuks_pizzeriaRegister.service.DBCard;
>>>>>>> feat/receipts
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
<<<<<<< HEAD
import java.util.List;
import java.util.Objects;
=======
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;
import java.util.Random;
>>>>>>> feat/receipts

public class NewOrderController {

    private static NewOrderController instance;
<<<<<<< HEAD
    private ProductContainer container;

=======
    private ProductContainer productContainer;
>>>>>>> feat/receipts

    @FXML
    public ListView<String> listOfProducts;
    @FXML
    public HBox productHBox;
<<<<<<< HEAD
=======
    @FXML
    private TextField cardNumberTextField;
>>>>>>> feat/receipts

    @FXML
    private Label selectedProductLabel, totalPriceLabel;

    @FXML
    private ImageView productImageView;

<<<<<<< HEAD
    private String selectedItemFromProductList;


    @FXML
    public void initialize() {
        container = new ProductContainer();
=======
    @FXML
    private Label identityNum;

    private String selectedItemFromProductList;

    private String cardNumber;

    @FXML
    CheckBox discountCardCheckBox;

    @FXML
    public void initialize() {
        productContainer = new ProductContainer();
>>>>>>> feat/receipts
        instance = this;
        updateProductListInHBox();

        listOfProducts.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedItemFromProductList = newValue;
            updateSelectedProductLabel(selectedItemFromProductList);
        });
<<<<<<< HEAD
=======

        discountCardCheckBox.setOnAction(event -> {
            activateDiscountCard();
        });
    }

    public static NewOrderController getInstance() {
        return instance;
    }

    private CardController cardController;

    @FXML
    private void openCardEmulator() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("card-view.fxml"));
        Scene scene = new Scene(loader.load());
        cardController = loader.getController();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
>>>>>>> feat/receipts
    }

    private void updateProductListInHBox() {
        productHBox.getChildren().clear();
        List<String> categoryList = DBProduct.getProductCategoryFromDatabase();
        List<String> productList = DBProduct.getProductNameFromDatabase();
        List<Double> priceList = DBProduct.getProductPriceFromDatabase();
        List<Integer> minList = DBProduct.getProductMinutesFromDatabase();
        List<String> imgLink = DBProduct.getProductImageFromDatabase();
        List<String> ingredientList = DBProduct.getProductIngredientListFromDatabase();

<<<<<<< HEAD
        container = new ProductContainer();
        for (int i = 0; i < categoryList.size(); i++) {
            VBox vBox = container.setProductContainer(
=======
        productContainer = new ProductContainer();
        for (int i = 0; i < categoryList.size(); i++) {
            VBox vBox = productContainer.setProductContainer(
>>>>>>> feat/receipts
                    categoryList.get(i), priceList.get(i), imgLink.get(i),
                    productList.get(i), ingredientList.get(i), minList.get(i));

            productHBox.getChildren().add(vBox);
        }
    }

    @FXML
<<<<<<< HEAD
    private void confirmOrderResultBtn() throws IOException {
        CardController controller = new CardController();
        controller.showCardScene();
        new Receipts(container);
=======
    private void confirmOrderResultBtn() {
        String cardNumberTransfer = cardNumberTextField.getText();
        cardNumber = DBCard.getFirstCardNumber();
        if (cardNumberTransfer.equals(cardNumber)) {
            cardController.showTransferBlock();
        }
    }

    public void buyOrder() throws IOException {
        new Receipts(productContainer);
        backToMenu();
>>>>>>> feat/receipts
    }

    @FXML
    private void onShowPayTab() {
        updateListOfProducts();
    }

    private void updateListOfProducts() {
        listOfProducts.getItems().clear();
<<<<<<< HEAD
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
=======
        double totalPrice = 0; // reset total price
        List<String> items = productContainer.getSelectedProducts();

        if (items != null && !items.isEmpty()) {
            for (String item : items) {
                listOfProducts.getItems().add(item); // Add product to ListView
                List<String> productNames = DBProduct.getProductNameFromDatabase();
                int index = productNames.indexOf(item);

                if (index != -1) {
                    List<Double> prices = DBProduct.getProductPriceFromDatabase();
                    double price = prices.get(index);
                    totalPrice += price;
                }
            }

            if (discountCardCheckBox.isSelected()) {
                totalPrice = totalPrice * 0.85; // 15% discount
            }

            String convertedTotalPrice = new DecimalFormat("#00.00").format(totalPrice);
            totalPriceLabel.setText("Total Price: " + convertedTotalPrice + " EURO");
        } else {
            System.out.println("No selected products found.");
        }
        generateRandomIdentityNumber();
    }

    private void generateRandomIdentityNumber() {
        Random rand = new Random();
        int min = 1000;
        int max = 9999;
        int randNum = rand.nextInt(max - min - 1) + min;
        identityNum.setText(String.valueOf(randNum));
    }

    public String getRandIdentityNumber() {
        return identityNum.getText();
    }

    @FXML
    private void activateDiscountCard() {
        updateListOfProducts();
>>>>>>> feat/receipts
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
<<<<<<< HEAD
            }catch (NullPointerException ex) {
=======
            } catch (NullPointerException ex) {
>>>>>>> feat/receipts
                productImageView.setImage(null);
            }
        }

<<<<<<< HEAD
        String result =
                "Product: " + productName + "\n" +
                "Price: " + price + " EURO\n" +
                "Minutes: " + min + "\n" +
                "Ingredients: " + listOfIngredients;
        selectedProductLabel.setText(result);

=======
        String convertedPrice = new DecimalFormat("#00.00").format(price);
        String result =
                "Product: " + productName + "\n" +
                        "Price: " + convertedPrice + " EURO\n" +
                        "Minutes: " + min + "\n" +
                        "Ingredients: " + "(" + listOfIngredients + ")";
        selectedProductLabel.setText(result);
>>>>>>> feat/receipts
    }

    @FXML
    private void backToMenu() throws IOException {
        Stage currentStage = (Stage) productHBox.getScene().getWindow();
        currentStage.close();

        Stage newStage = new MenuController().getStageOfMenuScene();
<<<<<<< HEAD

        newStage.show();
    }

//    public void callCustomPizzaScene() throws IOException {
//        CustomPizzaController controller = new CustomPizzaController();
//        controller.showCustomPizzaScene();
//    }
=======
        newStage.show();
    }

>>>>>>> feat/receipts
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

<<<<<<< HEAD


    public static NewOrderController getInstance() {
        return instance;
    }

}
=======
    public String getCardNumber() {
        return cardNumber;
    }
}
>>>>>>> feat/receipts
