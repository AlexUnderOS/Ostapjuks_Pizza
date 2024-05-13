package com.alexosta.ostapjuks_pizzeriaRegister.controller;

import com.alexosta.ostapjuks_pizzeriaRegister.*;
import com.alexosta.ostapjuks_pizzeriaRegister.animations.ResizeAnimation;
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


    @FXML
    public ListView<String> listOfProducts;

    @FXML
    public HBox productHBox;



    @FXML
    public void initialize() {
        instance = this;
    }

    @FXML
    private void confirmOrderResultBtn() {
        System.out.println("We do something to confirm our order.");
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