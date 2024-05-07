package com.alexosta.ostapjuks_pizzeriaRegister.controller;

import com.alexosta.ostapjuks_pizzeriaRegister.*;
import com.alexosta.ostapjuks_pizzeriaRegister.animations.ResizeAnimation;
import com.alexosta.ostapjuks_pizzeriaRegister.domain.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Objects;

public class NewOrderController {

    private static NewOrderController instance;
    private static ResizeAnimation resizeAnim;

    @FXML
    private Button cashBtn, cardBtn;

    @FXML
    private HBox productListHBox;

    @FXML
    public ListView<String> categoryListView, listOfProducts;

    @FXML
    private Label paymentSelectionLabel, selectedProduct, totalPriceLabel;

    @FXML
    private CheckBox discountCardCheckBox;

    @FXML
    private TextArea calculatingTextArea;

    private boolean discountCard = false;

    @FXML
    public void initialize() {
        resizeAnim = new ResizeAnimation();
        instance = this;
        productListHBox.getChildren().clear();
        setAllResizableBtnsInNewOrder();
        displayProductList();
    }

    @FXML
    private void setDiscountCard() {
        if (discountCardCheckBox.isSelected()) {
            discountCard = true;
        }else {
            discountCard = false;
        }
    }

    private void displayProductList() {
        Category category = new Category();
        category.fillCategory(categoryListView);
    }

    public boolean getDiscountCard() {
        return discountCard;
    }



    @FXML
    private void confirmOrderResultBtn() {
        PaymentConfirming resultConfirming = new PaymentConfirming();
        resultConfirming.createResult(selectedProduct, totalPriceLabel, calculatingTextArea);


    }

    @FXML
    private void reloadPage() {
        initialize();
    }

    @FXML
    private void switchPayTypeToCash() {
        System.out.println("cash");
        paymentSelectionLabel.setText("Selected 'cash'");

    }

    @FXML
    private void switchPayTypeToCard() {
        System.out.println("card");
        paymentSelectionLabel.setText("Selected 'card'");
    }

    private void setAllResizableBtnsInNewOrder() {
        resizeAnim.setButtonHoverHandlers(cashBtn);
        resizeAnim.setButtonHoverHandlers(cardBtn);
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

    public HBox getProductList_hBox() {
        return productListHBox;
    }
}