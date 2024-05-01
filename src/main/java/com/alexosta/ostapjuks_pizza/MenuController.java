package com.alexosta.ostapjuks_pizza;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MenuController {
    Animations animations;

    @FXML
    private JFXButton newOrder_btn, listOfChecks_btn;

    @FXML
    private void initialize() {
        animations = new Animations();
        setAllResizableBtnsInMenu();
    }

    private void setAllResizableBtnsInMenu() {
        animations.setButtonHoverHandlers(newOrder_btn);
        animations.setButtonHoverHandlers(listOfChecks_btn);
    }

    public void callNewOrderScene() throws IOException {

        Stage currentStage = (Stage) newOrder_btn.getScene().getWindow(); // getting this._ window stage
        currentStage.close();

        NewOrderController controller = new NewOrderController();
        Stage newStage = controller.showNewOrderScene();

        newStage.show();

    }

    public Stage getStageOfMenuScene() throws IOException {
        Stage newStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(PizzeriaSelector.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(
                getClass().getResource("UI/styles.css")).toExternalForm());

        newStage.setTitle("Pizzeria Register");
        newStage.setScene(scene);
        return newStage;
    }

    public void showLoginDialogue() {
        new CustomDialog().showLoginDialog(false);
    }
}
