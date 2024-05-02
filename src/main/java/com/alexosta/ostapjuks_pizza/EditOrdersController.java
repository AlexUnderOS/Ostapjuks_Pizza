package com.alexosta.ostapjuks_pizza;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class EditOrdersController {

    private static EditOrdersController instance;

    private static EditOrders editOrders;

    @FXML
    TextArea jsonCode_textArea;

    @FXML
    TextField jsonPath_textField;

    @FXML
    private void initialize() {
        instance = this;
        editOrders = new EditOrders();
        loadJSONFile();
    }

    private void loadJSONFile() {
        String path = "src/main/resources/com/alexosta/ostapjuks_pizza/JSON/ProductsInRegister.json";
        jsonPath_textField.setText(path);
        editOrders.callLoadJSONFile(path);
    }

    public static EditOrdersController getInstance() {
        return instance;
    }
}
