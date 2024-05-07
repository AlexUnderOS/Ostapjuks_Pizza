package com.alexosta.ostapjuks_pizzeriaRegister;

import com.alexosta.ostapjuks_pizzeriaRegister.controller.MenuController;
import com.alexosta.ostapjuks_pizzeriaRegister.view.LoginDialog;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        new LoginDialog().showLoginDialog(true);

        MenuController MenuController = new MenuController();
        stage = MenuController.getStageOfMenuScene();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}