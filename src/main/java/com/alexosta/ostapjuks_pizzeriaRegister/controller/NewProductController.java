package com.alexosta.ostapjuks_pizzeriaRegister.controller;

import com.alexosta.ostapjuks_pizzeriaRegister.Main;
import com.alexosta.ostapjuks_pizzeriaRegister.service.DBProduct;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class NewProductController {

    @FXML
    private TextField categoryTextField, productNameTextField, quantityTextField, priceTextField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private CheckBox availableCheckBox;

    private static final Stage newStage = new Stage();


    private String category, productName, imageLink, description;
    private int quantity;
    private double price;
    private boolean available;

    private FileChooser fileChooser = new FileChooser();
    public Stage getStageOfNewProductScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("new-product.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        newStage.setTitle("Edit");
        newStage.setScene(scene);

        fileChooser.setInitialDirectory(new File(""));
        return newStage;
    }

    @FXML
    private void addImage() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(newStage);
        if (file!= null) {
            try {
                String resourcesPath = "src/main/resources/com/alexosta/ostapjuks_pizzeriaRegister/UI/imgs/product_images";
                Path destinationPath = Paths.get(resourcesPath);
                Path sourcePath = file.toPath();
                Path targetPath = destinationPath.resolve(file.getName());

                if (Files.exists(targetPath)) {
                    System.out.println("File is exist!");
                } else {
                    Files.copy(sourcePath, targetPath);
                    System.out.println("File successfully was copied!");
                }
                imageLink = targetPath.toString();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void onClickSaveButton() {
        updateData();
    }

    private void updateData() {
        category = categoryTextField.getText();
        price = Double.parseDouble(priceTextField.getText());
        quantity = Integer.parseInt(quantityTextField.getText());
        available = availableCheckBox.isSelected();
        description = descriptionTextArea.getText();
        productName = productNameTextField.getText();

        DBProduct.writeToDatabase(category, price, quantity, imageLink, available, description, productName);
    }
}
