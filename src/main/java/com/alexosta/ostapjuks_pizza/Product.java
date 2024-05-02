package com.alexosta.ostapjuks_pizza;

public class Product {
    private String name;
    private String ingredients;
    private String size;
    private double price;
    private int id;
    public Product(String name, String ingredients, String size, double price, int id) {
        this.name = name;
        this.ingredients = ingredients;
        this.size = size;
        this.price = price;
        this.id = id;
    }

    public Product() {}


    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }
}
