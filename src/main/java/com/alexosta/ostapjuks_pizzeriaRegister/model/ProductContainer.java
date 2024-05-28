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
import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductContainer {
    private String category;
    private double price;
    private String imageUrl;
    private String productName;
    private String ingredients;
    private int minutes;

    private final List<String> selectedProducts = new ArrayList<>();

    public VBox setProductContainer(String category, double price, String imageUrl,
                                       String productName, String ingredients, int minutes) {
        this.category = category;
        this.price = price;
        this.imageUrl = imageUrl;
        this.productName = productName;
        this.ingredients = ingredients;
        this.minutes = minutes;

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

        if (imageUrl != null) {
            ImageView imageView = createProductImageView();
            vbox.getChildren().add(imageView);
        }

        HBox titleHBox = createTitleHBox();
        vbox.getChildren().add(titleHBox);

        Label minutesLabel = createMinutesLabel();
        vbox.getChildren().add(minutesLabel);

        Label priceLabel = createPriceLabel();
        vbox.getChildren().add(priceLabel);

        TextField descriptionTextField = createDescriptionTextField();
        vbox.getChildren().add(descriptionTextField);

        Label amountInStock = createAmountInStockLabel();
        vbox.getChildren().add(amountInStock);

        Spinner<Integer> inStockSpinner = createInStockSpinner(productName);
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

    private TextField createDescriptionTextField() {
        TextField descriptionTextField = new TextField(ingredients);
        descriptionTextField.setAlignment(Pos.CENTER);
        descriptionTextField.setOpacity(0.35);
        descriptionTextField.getStyleClass().add("product-description");
        descriptionTextField.setFont(new Font("Comic Sans MS Italic", 14.0));
        descriptionTextField.setEditable(false);
        return descriptionTextField;
    }

    private Spinner<Integer> createInStockSpinner(String productName) {
        Spinner<Integer> inStockSpinner = new Spinner<>();

        List<Pair<String, Pair<Integer, Integer>>> ingredientQuantities = getIngredientQuantities(productName);

        // min value of all ingredients
        int minAvailableQuantity = Integer.MAX_VALUE;
        for (Pair<String, Pair<Integer, Integer>> ingredientQuantity : ingredientQuantities) {
            int requiredQuantity = ingredientQuantity.getValue().getKey();
            int availableQuantity = ingredientQuantity.getValue().getValue();
            minAvailableQuantity = Math.min(minAvailableQuantity, availableQuantity / requiredQuantity);
        }

        // set min value to Spinner
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, minAvailableQuantity, 0);
        inStockSpinner.setValueFactory(valueFactory);

        inStockSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            addToCart(productName, newValue);
            System.out.println(getSelectedProducts());
        });

        inStockSpinner.setEditable(true);
        inStockSpinner.setPrefHeight(30.0);
        inStockSpinner.setPrefWidth(62.0);
        return inStockSpinner;
    }

    private void addToCart(String productName, int quantity) {
        selectedProducts.removeIf(product -> product.equals(productName));

        for (int i = 0; i < quantity; i++) {
            selectedProducts.add(productName);
        }
    }

    public List<Pair<String, Pair<Integer, Integer>>> getIngredientQuantities(String productName) {
        List<Pair<String, Pair<Integer, Integer>>> ingredientQuantities = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/products", "postgres", "Parole01!")) {
            String query =
                    "SELECT ingredient_quantity.ingredient, " +
                    "COALESCE(SUM(splitted_ingredients.quantity), 0) AS required_quantity, " +
                    "COALESCE(SUM(ingredient_quantity.quantity), 0) AS available_quantity " +
                    "FROM splitted_ingredients " +
                    "LEFT JOIN ingredient_quantity ON splitted_ingredients.ingredient = ingredient_quantity.ingredient " +
                    "WHERE splitted_ingredients.dish_name = ? " +
                    "GROUP BY ingredient_quantity.ingredient";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, productName);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String ingredient = resultSet.getString("ingredient");
                int requiredQuantity = resultSet.getInt("required_quantity");
                int availableQuantity = resultSet.getInt("available_quantity");

                System.out.println("Ingredient: " + ingredient);
                System.out.println("Need: " + requiredQuantity);
                System.out.println("Available : " + availableQuantity);

                ingredientQuantities.add(new Pair<>(ingredient, new Pair<>(requiredQuantity, availableQuantity)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredientQuantities;
    }


    public List<String> getSelectedProducts() {
        return selectedProducts;
    }

}

