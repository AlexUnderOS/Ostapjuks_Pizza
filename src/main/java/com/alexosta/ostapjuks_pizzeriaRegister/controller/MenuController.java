package com.alexosta.ostapjuks_pizzeriaRegister.controller;

import com.alexosta.ostapjuks_pizzeriaRegister.Main;
import com.alexosta.ostapjuks_pizzeriaRegister.animations.ResizeAnimation;
import com.alexosta.ostapjuks_pizzeriaRegister.service.DBProduct;
import com.alexosta.ostapjuks_pizzeriaRegister.view.LoginDialog;
import com.alexosta.ostapjuks_pizzeriaRegister.view.RegisterDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MenuController {
    private ResizeAnimation resizeAnim;

    @FXML
    private Button newOrderBtn, switchAccountBtn, newAccountBtn;

    @FXML
    private ListView<String> productListView;


    @FXML
    private void initialize() {
        resizeAnim = new ResizeAnimation();
        setAllResizableBtnsInMenu();
        updateListView();

        productListView.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (!empty) {
                    setText(item);
                    setOnMouseClicked(event -> {
                        int index = productListView.getItems().indexOf(item);
                        System.out.println("Selected item index: "+ index);
                    });
                }
            }
        });
    }

    @FXML
    private void callNewOrderScene() throws IOException {

        Stage currentStage = (Stage) newOrderBtn.getScene().getWindow(); // getting this._ window stage
        currentStage.close();

        Stage newStage = new NewOrderController().createNewOrderScene();

        newStage.show();

    }

    @FXML
    private void updateListView() {
        productListView.setItems(getDatabaseList());
    }

    @FXML
    private void createNewProduct() throws IOException {
        Stage newStage = new NewProductController().getStageOfNewProductScene();
        newStage.show();
    }

    @FXML
    private void switchAccount() {
        new LoginDialog().showLoginDialog(false);
    }

    @FXML
    private void createNewAccount() {
        new RegisterDialog().showRegisterDialog(false);
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
    private void setAllResizableBtnsInMenu() {
        resizeAnim.setButtonHoverHandlers(newOrderBtn);
        resizeAnim.setButtonHoverHandlers(switchAccountBtn);
        resizeAnim.setButtonHoverHandlers(newAccountBtn);
    }

    public static ObservableList<String> getDatabaseList() {
        ObservableList<String> products = FXCollections.observableList(DBProduct.getProductsFromDatabase());
        FXCollections.observableArrayList(products);
        return products;
    }
}
