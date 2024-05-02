package com.alexosta.ostapjuks_pizza;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EditOrders {
    public void callEditOrders() throws IOException {
        Stage newStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(PizzeriaSelector.class.getResource("edit-orders-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        newStage.setTitle("Edit JSON files!");
        newStage.setScene(scene);
        newStage.show();
    }

    public void callLoadJSONFile(String path) {
        TextArea textArea = new TextArea();
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            textArea.setText(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
