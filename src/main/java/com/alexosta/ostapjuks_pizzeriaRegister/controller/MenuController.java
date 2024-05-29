package com.alexosta.ostapjuks_pizzeriaRegister.controller;

import com.alexosta.ostapjuks_pizzeriaRegister.Main;
import com.alexosta.ostapjuks_pizzeriaRegister.animation.ResizeAnimation;
import com.alexosta.ostapjuks_pizzeriaRegister.model.IngredientBox;
import com.alexosta.ostapjuks_pizzeriaRegister.model.ProductBox;
import com.alexosta.ostapjuks_pizzeriaRegister.service.DBProduct;
import com.alexosta.ostapjuks_pizzeriaRegister.view.LoginDialog;
import com.alexosta.ostapjuks_pizzeriaRegister.view.RegisterDialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class MenuController {
    private ResizeAnimation resizeAnim;
    private DBProduct dbProduct;
    private IngredientBox ingredientBox;
    private ProductBox productBox;
    private LoginDialog dialog;
    private static final RegisterDialog registerDialog = new RegisterDialog();




    @FXML
    private Button newOrderBtn, switchAccountBtn, newAccountBtn;

    @FXML
    private TextField ingredientNameTextField, ingredientQuantityTextField;

    @FXML
    private Label ingredientAlaramLabel;

    @FXML
    private VBox ingredientListVBox, productListVBox;

    @FXML
    private Label priorityLabel;

    @FXML
    private Tab ingredientsTab, productsTab;


    @FXML
    private void initialize() {
        resizeAnim = new ResizeAnimation();
        dbProduct = new DBProduct();
        ingredientBox = new IngredientBox();
        productBox = new ProductBox();
        dialog = Main.getDialog();
        boolean isAdmin = dialog.getLoginPriority();
        if (isAdmin) {
            System.out.println("Cool!");
            setAdminPrioritySettings();
        }else {
            System.out.println("Bad!");
            setUserPrioritySettings();
        }


        setAllResizableBtnsInMenu();
        updateIngredientsListView();
        updateProductListView();

    }

    @FXML
    private void deleteIngredientByName() {
        String selectedIngredientName = ingredientBox.getSelectedIngredient();
        dbProduct.deleteIngredientByName(selectedIngredientName);

        updateIngredientsListView();
    }

    @FXML
    private void deleteProductByName() {
        String selectedProductName = productBox.getSelectedProduct();
        System.out.println(selectedProductName);
        dbProduct.deleteProductByName(selectedProductName);

        updateProductListView();

    }

    @FXML
    private void callNewOrderScene() throws IOException {

        Stage currentStage = (Stage) newOrderBtn.getScene().getWindow(); // getting this._ window stage
        currentStage.close();

        Stage newStage = new NewOrderController().createNewOrderScene();

        newStage.show();

    }

    @FXML
    private void addNewIngredient() {
        try {
            String name = ingredientNameTextField.getText();
            int quantity = Integer.parseInt(ingredientQuantityTextField.getText());
            dbProduct.writeIngredientToDatabase(name, quantity);
            ingredientAlaramLabel.setText("");
            updateIngredientsListView();
        }catch (NumberFormatException ex) {
            ingredientAlaramLabel.setText("* enter correct data!");
        }
    }

    @FXML
    private void createNewProduct() throws IOException {
        Stage newStage = new NewProductController().getStageOfNewProductScene();
        newStage.show();
    }

    @FXML
    private void updateProductListView() {
        productListVBox.getChildren().clear();
        List<String> categoryList = DBProduct.getProductCategoryFromDatabase();
        List<String> productList = DBProduct.getProductNameFromDatabase();
        List<Double> priceList = DBProduct.getProductPriceFromDatabase();
        List<Integer> minList = DBProduct.getProductMinutesFromDatabase();

        for (int i = 0; i < categoryList.size(); i++) {
            HBox hBox = productBox.createBox(categoryList.get(i), productList.get(i), priceList.get(i), minList.get(i));
            productListVBox.getChildren().add(hBox);
        }

    }

    @FXML
    private void updateIngredientsListView() {
        ingredientListVBox.getChildren().clear();
        List<String> ingredient = DBProduct.getIngredientsFromDatabase();
        List<Long> quantity = DBProduct.getIngredientQuantityFromDatabase();

        for (int i = 0; i < ingredient.size(); i++) {
            HBox hBox = ingredientBox.createBox(ingredient.get(i), Math.toIntExact(quantity.get(i)));
            ingredientListVBox.getChildren().add(hBox);
        }

    }

    @FXML
    private void switchAccount() {
        dialog.showLoginDialog(false);
        if (dialog.getLoginPriority()) {
            setAdminPrioritySettings();
        } else {
            setUserPrioritySettings();
        }

    }
    @FXML
    private void createNewAccount() {
        registerDialog.showRegisterDialog(false);
    }

    public Stage getStageOfMenuScene() throws IOException {
        Stage newStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(
                Main.class.getResource("UI/styles.css")).toExternalForm());

        newStage.setTitle("Pizzeria Register");
        newStage.setScene(scene);
        return newStage;
    }

    private void setAdminPrioritySettings() {
        priorityLabel.setText("Priority: ADMIN");
        ingredientsTab.setDisable(false);
        productsTab.setDisable(false);
    }

    private void setUserPrioritySettings() {
        priorityLabel.setText("Priority: USER");
        ingredientsTab.setDisable(true);
        productsTab.setDisable(true);
    }

    private void setAllResizableBtnsInMenu() {
        resizeAnim.setButtonHoverHandlers(newOrderBtn);
        resizeAnim.setButtonHoverHandlers(switchAccountBtn);
        resizeAnim.setButtonHoverHandlers(newAccountBtn);
    }

}
