package com.alexosta.ostapjuks_pizzeriaRegister.controller;

import com.alexosta.ostapjuks_pizzeriaRegister.Main;
import com.alexosta.ostapjuks_pizzeriaRegister.service.DBProduct;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NewProductController {

    @FXML
    private TextField categoryTextField, productNameTextField, priceTextField, minutesTextField;

    @FXML
    private TextArea ingredientListTextArea;

    @FXML
    private Label alarmLabel;


    private static final Stage newStage = new Stage();


    private String imageLink;

    private final FileChooser fileChooser = new FileChooser();
    public Stage getStageOfNewProductScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("new-product.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        newStage.setTitle("Edit");
        newStage.setScene(scene);

        fileChooser.setInitialDirectory(new File(""));
        return newStage;
    }

    @FXML
    private void searchImage() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(newStage);
        if (file != null) {
            try {
                String resourcesPath = "src/main/resources/com/alexosta/ostapjuks_pizzeriaRegister/UI/imgs/product_images/";
                Path destinationPath = Paths.get(resourcesPath);
                Path sourcePath = file.toPath();
                Path targetPath = destinationPath.resolve(file.getName());

                if (Files.exists(targetPath)) {
                    System.out.println("File already exists!");
                } else {
                    Files.copy(sourcePath, targetPath);
                    System.out.println("File successfully copied!");
                }
                imageLink = targetPath.toUri().toString();

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
        try {
            String category = categoryTextField.getText();
            double price = Double.parseDouble(priceTextField.getText());
            String productName = productNameTextField.getText();
            String ingredientList = ingredientListTextArea.getText();
            int minutes = Integer.parseInt(minutesTextField.getText());

            DBProduct.writeToDatabase(category, price, imageLink, productName, ingredientList, minutes);
            alarmLabel.setText("");
        }catch (NumberFormatException ex) {
            alarmLabel.setText("* enter correct data!");
        }
    }


}
