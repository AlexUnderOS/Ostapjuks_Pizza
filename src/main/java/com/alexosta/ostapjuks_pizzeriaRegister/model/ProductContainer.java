package com.alexosta.ostapjuks_pizzeriaRegister.model;

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

import java.util.ArrayList;
import java.util.List;

public class ProductContainer {
    private String category;
    private double price;
    private String imageUrl;
    private String productName;
    private String ingredients;
    private int minutes;
    private String size;

    List<String> selectedProducts = new ArrayList<>();

    public VBox setProductContainer(String category, double price, String imageUrl,
                                       String productName, String ingredients, int minutes, String size) {
        this.category = category;
        this.price = price;
        this.imageUrl = imageUrl;
        this.productName = productName;
        this.ingredients = ingredients;
        this.minutes = minutes;
        this.size = size;

        return createProductContainer();
    }

    private VBox createProductContainer() {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefWidth(192.0);
        vbox.setSpacing(5.0);
        vbox.getStyleClass().add("pepper-types-vbox");

        Label categoryLabel = createCategoryLabel();
        vbox.getChildren().add(categoryLabel);

        ImageView imageView = createProductImageView();
        vbox.getChildren().add(imageView);

        HBox titleHBox = createTitleHBox();
        vbox.getChildren().add(titleHBox);

        HBox sizeSelectionHBox = createSizeSelectionHBox();
        vbox.getChildren().add(sizeSelectionHBox);

        Label minutesLabel = createMinutesLabel();
        vbox.getChildren().add(minutesLabel);

        Label priceLabel = createPriceLabel();
        vbox.getChildren().add(priceLabel);

        TextField descriptionTextField = createDescriptionTextField();
        vbox.getChildren().add(descriptionTextField);

        Label amountInStock = createAmountInStockLabel();
        vbox.getChildren().add(amountInStock);

        Spinner<Integer> inStockSpinner = createInStockSpinner();
        vbox.getChildren().add(inStockSpinner);

        vbox.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));

        return vbox;
    }

    private Label createCategoryLabel() {
        Label minutesLabel = new Label();
        minutesLabel.setText(category.toUpperCase());
        minutesLabel.setFont(new Font("Comic Sans MS", 20.0));
        return minutesLabel;
    }

    private Label createAmountInStockLabel() {
        Label amountInStockLabel = new Label();
        amountInStockLabel.setText("How much:");
        amountInStockLabel.setFont(new Font("Comic Sans MS", 17.0));
        return amountInStockLabel;
    }

    private Label createMinutesLabel() {
        Label minutesLabel = new Label();
        minutesLabel.setText("Minutes: " + minutes);
        minutesLabel.setFont(new Font("Comic Sans MS", 17.0));
        return minutesLabel;
    }

    private Label createPriceLabel() {
        Label minutesLabel = new Label();
        minutesLabel.setText("Price: " + price);
        minutesLabel.setFont(new Font("Comic Sans MS", 17.0));
        return minutesLabel;
    }

    private ImageView createProductImageView() {
        ImageView pizzaImageView = new ImageView();
        pizzaImageView.setFitHeight(164.0);
        pizzaImageView.setFitWidth(200.0);
        pizzaImageView.setPickOnBounds(true);
        pizzaImageView.setPreserveRatio(true);
        System.out.println(imageUrl);
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

        Label pizzaTitleLabel = new Label(productName);
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

        pizzaSizeToggleGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle != null) {
                RadioButton selectedRadioButton = (RadioButton) newToggle;
                String selectedSize = selectedRadioButton.getText();
                System.out.println("Selected size: " + selectedSize);
                size = selectedSize;
            }
        });

        return sizeSelectionHBox;
    }

    private RadioButton createSizeRadioButton(String size, ToggleGroup toggleGroup) {
        JFXRadioButton radioButton = new JFXRadioButton(size);
        radioButton.setFont(new Font(12.0));
        radioButton.setToggleGroup(toggleGroup);
        return radioButton;
    }

    private TextField createDescriptionTextField() {
        TextField descriptionTextField = new TextField(ingredients);
        descriptionTextField.setAlignment(Pos.CENTER);
        descriptionTextField.setOpacity(0.35);
        descriptionTextField.getStyleClass().add("product-description");
        descriptionTextField.setFont(new Font("Comic Sans MS Italic", 14.0));
        descriptionTextField.setEditable(false);
        return descriptionTextField;
    }

    private Spinner<Integer> createInStockSpinner() {
        Spinner<Integer> inStockSpinner = new Spinner<>();

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);

        inStockSpinner.setValueFactory(valueFactory);

        inStockSpinner.setEditable(true);
        inStockSpinner.setPrefHeight(30.0);
        inStockSpinner.setPrefWidth(62.0);
        return inStockSpinner;
    }

    public List<String> getSelectedProducts() {
        return selectedProducts;
    }

}

