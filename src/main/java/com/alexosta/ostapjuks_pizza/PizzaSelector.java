package com.alexosta.ostapjuks_pizza;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PizzaSelector extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        CustomDialog dialogue = new CustomDialog();
        dialogue.showLoginDialog();

        FXMLLoader fxmlLoader = new FXMLLoader(PizzaSelector.class.getResource("new-order-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1020, 580);
        scene.getStylesheets().add(getClass().getResource("UI/styles.css").toExternalForm());

        stage.setTitle("Pizza!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}