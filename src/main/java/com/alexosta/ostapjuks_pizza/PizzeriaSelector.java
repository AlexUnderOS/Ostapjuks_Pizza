package com.alexosta.ostapjuks_pizza;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class PizzeriaSelector extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        CustomDialog dialogue = new CustomDialog();
        dialogue.showLoginDialog(true);


        MenuController MenuController = new MenuController();
        stage = MenuController.getStageOfMenuScene();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}