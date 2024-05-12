package com.alexosta.ostapjuks_pizzeriaRegister.model;

import com.alexosta.ostapjuks_pizzeriaRegister.domain.Product;
import com.jfoenix.controls.JFXRadioButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class ProductContainer {
    private String title;
    private String description;
    private String imageUrl;
    private int quantityInStock;

    private List<Product> productList = new LinkedList<>();

    public ProductContainer(HBox container, String title, String description, String imageUrl, int quantityInStock) {
        this.title = title;
        this.description = description;
        this.quantityInStock = quantityInStock;
        System.out.println("Attempting to load resource: UI/imgs/" + imageUrl);
        URL resourceUrl = getClass().getResource("UI/imgs/" + imageUrl);
        if (resourceUrl == null) {
            System.out.println("Resource not found: UI/imgs/" + imageUrl);
        } else {
            this.imageUrl = resourceUrl.toExternalForm();
        }
        container.getChildren().add(createProductContainer());
    }

    private VBox createProductContainer() {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefWidth(192.0);
        vbox.setSpacing(5.0);
        vbox.getStyleClass().add("pepper-types-vbox");

        ImageView imageView = createProductImageView();
        vbox.getChildren().add(imageView);

        HBox titleHBox = createTitleHBox();
        vbox.getChildren().add(titleHBox);

        HBox sizeSelectionHBox = createSizeSelectionHBox();
        vbox.getChildren().add(sizeSelectionHBox);

        TextField descriptionTextField = createDescriptionTextField();
        vbox.getChildren().add(descriptionTextField);

        Spinner<Integer> inStockSpinner = createInStockSpinner();
        vbox.getChildren().add(inStockSpinner);

        Label amountInStock = createAmountInStockLabel();
        vbox.getChildren().add(amountInStock);

        vbox.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));

        return vbox;
    }


    private Label createAmountInStockLabel() {
        Label amountInStockLabel = new Label();
        amountInStockLabel.setText("In stock: "+(quantityInStock));
        amountInStockLabel.setFont(new Font("Comic Sans MS", 17.0));
        return amountInStockLabel;
    }

    private ImageView createProductImageView() {
        ImageView pizzaImageView = new ImageView();
        pizzaImageView.setFitHeight(164.0);
        pizzaImageView.setFitWidth(200.0);
        pizzaImageView.setPickOnBounds(true);
        pizzaImageView.setPreserveRatio(true);
        Image image = new Image(imageUrl);
        pizzaImageView.setImage(image);
        return pizzaImageView;
    }

    private HBox createTitleHBox() {
        HBox titleHBox = new HBox();
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.setPrefHeight(30.0);
        titleHBox.setPrefWidth(194.0);
        titleHBox.setSpacing(20.0);

        Label pizzaTitleLabel = new Label(title);
        pizzaTitleLabel.setTextAlignment(TextAlignment.CENTER);
        pizzaTitleLabel.setFont(new Font("Comic Sans MS", 17.0));
        titleHBox.getChildren().add(pizzaTitleLabel);

        return titleHBox;
    }

    private HBox createSizeSelectionHBox() {
        HBox sizeSelectionHBox = new HBox();
        sizeSelectionHBox.setAlignment(Pos.TOP_CENTER);
        sizeSelectionHBox.setPrefHeight(30.0);
        sizeSelectionHBox.setPrefWidth(184.0);
        sizeSelectionHBox.setSpacing(10.0);

        ToggleGroup pizzaSizeToggleGroup = new ToggleGroup();

        RadioButton sizeSRadioButton = createSizeRadioButton("S", pizzaSizeToggleGroup);
        RadioButton sizeMRadioButton = createSizeRadioButton("M", pizzaSizeToggleGroup);
        RadioButton sizeLRadioButton = createSizeRadioButton("L", pizzaSizeToggleGroup);

        sizeSelectionHBox.getChildren().addAll(sizeSRadioButton, sizeMRadioButton, sizeLRadioButton);



        return sizeSelectionHBox;
    }

    private RadioButton createSizeRadioButton(String size, ToggleGroup toggleGroup) {
        JFXRadioButton radioButton = new JFXRadioButton(size);
        radioButton.setFont(new Font(12.0));
        radioButton.setToggleGroup(toggleGroup);
        return radioButton;
    }

    private TextField createDescriptionTextField() {
        TextField descriptionTextField = new TextField(description);
        descriptionTextField.setAlignment(Pos.CENTER);
        descriptionTextField.setOpacity(0.35);
        descriptionTextField.getStyleClass().add("product-description");
        descriptionTextField.setFont(new Font("Comic Sans MS Italic", 14.0));
        descriptionTextField.setEditable(false);
        return descriptionTextField;
    }

    private Spinner<Integer> createInStockSpinner() {
        Spinner<Integer> inStockSpinner = new Spinner<>();
        inStockSpinner.setPrefHeight(30.0);
        inStockSpinner.setPrefWidth(62.0);
        return inStockSpinner;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
