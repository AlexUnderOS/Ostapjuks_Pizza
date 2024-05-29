package com.alexosta.ostapjuks_pizzeriaRegister;

import com.alexosta.ostapjuks_pizzeriaRegister.controller.MenuController;
import com.alexosta.ostapjuks_pizzeriaRegister.view.LoginDialog;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static final LoginDialog dialog = new LoginDialog();
    @Override
    public void start(Stage stage) throws IOException {
        dialog.showLoginDialog(true);

        MenuController menuController = new MenuController();
        stage = menuController.getStageOfMenuScene();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static LoginDialog getDialog() {
        return dialog;
    }
}