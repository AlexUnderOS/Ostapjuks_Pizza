package com.alexosta.ostapjuks_pizzeriaRegister.model;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Integer> ingredientStock;

    public Inventory() {
        ingredientStock = new HashMap<>();
    }

    public void addIngredient(String ingredient, int quantity) {
        ingredientStock.put(ingredient, ingredientStock.getOrDefault(ingredient, 0) + quantity);
    }

    public void removeIngredient(String ingredient, int quantity) {
        int currentQuantity = ingredientStock.getOrDefault(ingredient, 0);
        if (currentQuantity < quantity) {
            return;
        }
        ingredientStock.put(ingredient, currentQuantity - quantity);
    }

    public int getIngredientQuantity(String ingredient) {
        return ingredientStock.getOrDefault(ingredient, 0);
    }

    public boolean canMakePizza(Map<String, Integer> requiredIngredients) {
        for (Map.Entry<String, Integer> entry : requiredIngredients.entrySet()) {
            String ingredient = entry.getKey();
            int requiredQuantity = entry.getValue();
            if (getIngredientQuantity(ingredient) < requiredQuantity) {
                return false;
            }
        }
        return true;
    }

    public void useIngredients(Map<String, Integer> requiredIngredients) {
        for (Map.Entry<String, Integer> entry : requiredIngredients.entrySet()) {
            String ingredient = entry.getKey();
            int requiredQuantity = entry.getValue();
            removeIngredient(ingredient, requiredQuantity);
        }
    }
}
