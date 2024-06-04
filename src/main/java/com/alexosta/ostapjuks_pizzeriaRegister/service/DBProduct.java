package com.alexosta.ostapjuks_pizzeriaRegister.service;

import javafx.scene.control.ListView;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DBProduct {

    private static final String url = "jdbc:postgresql://aws-0-eu-central-1.pooler.supabase.com:6543/postgres";
    private static final String user = "postgres.xmcmzqjbrnjvelcauurb";
    private static final String password = "S$s7J!D/zic3t3V";

    public static void writeToDatabase(String category, double price, String image, String name, String ingredientList, int minutes) {

        String queryDish = "INSERT INTO dish(category, price, img, name, ingredient_list, minutes) VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(queryDish)) {
            pst.setString(1, category);
            pst.setDouble(2, price);
            pst.setString(3, image);
            pst.setString(4, name);
            pst.setString(5, ingredientList);
            pst.setInt(6, minutes);
            pst.executeUpdate();

            // split ingredientList and insert into splitted_ingredients
            Map<String, Integer> ingredientCounts = new HashMap<>();
            if (ingredientList!= null &&!ingredientList.isEmpty()) {
                String[] ingredients = ingredientList.split(",");
                for (String ingredient : ingredients) {
                    ingredient = ingredient.trim();
                    ingredientCounts.put(ingredient, ingredientCounts.getOrDefault(ingredient, 0) + 1);
                }
            }

            for (Map.Entry<String, Integer> entry : ingredientCounts.entrySet()) {
                String queryIngredient = "INSERT INTO splitted_ingredients(dish_name, ingredient, quantity) VALUES(?,?,?)";
                try (PreparedStatement pstIngredient = con.prepareStatement(queryIngredient)) {
                    pstIngredient.setString(1, name);
                    pstIngredient.setString(2, entry.getKey());
                    pstIngredient.setInt(3, entry.getValue());
                    pstIngredient.executeUpdate();
                } catch (SQLException ex) {
                    Logger lgr = Logger.getLogger(DBProduct.class.getName());
                    lgr.log(Level.SEVERE, "error in inserting into splitted_ingredients: " + ex.getMessage(), ex);

                }
            }
        }catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBProduct.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

    }

    public void writeIngredientToDatabase(String ingredient, int quantity) {
        String queryDish = "INSERT INTO ingredient_quantity(ingredient, quantity) VALUES(?,?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(queryDish)) {
            pst.setString(1, ingredient);
            pst.setDouble(2, quantity);
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBProduct.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////
    private static List<Object> getProductsFromDatabase(String sql, String columnName) {
        List<Object> result = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = con.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Object value = rs.getObject(columnName);
                result.add(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<String> getProductCategoryFromDatabase() {
        return getProductsFromDatabase("SELECT category FROM dish", "category")
                .stream()
                .map(String.class::cast)
                .collect(Collectors.toList());
    }

    public static List<String> getProductNameFromDatabase() {
        return getProductsFromDatabase("SELECT name FROM dish", "name")
                .stream()
                .map(String.class::cast)
                .collect(Collectors.toList());
    }

    public static List<Double> getProductPriceFromDatabase() {
        return getProductsFromDatabase("SELECT price FROM dish", "price")
                .stream()
                .map(Double.class::cast)
                .collect(Collectors.toList());
    }

    public static List<Integer> getProductMinutesFromDatabase() {
        return getProductsFromDatabase("SELECT minutes FROM dish", "minutes")
                .stream()
                .map(Integer.class::cast)
                .collect(Collectors.toList());
    }

    public static List<String> getProductIngredientListFromDatabase() {
        return getProductsFromDatabase("SELECT ingredient_list FROM dish", "ingredient_list")
                .stream()
                .map(String.class::cast)
                .collect(Collectors.toList());
    }

    public static List<String> getProductImageFromDatabase() {
        return getProductsFromDatabase("SELECT img FROM dish", "img")
                .stream()
                .map(String.class::cast)
                .collect(Collectors.toList());
    }

    ///////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////
    public static List<Object> getIngredientsFromDatabase(String sql, String columnName) {
        List<Object> result = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = con.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Object value = rs.getObject(columnName);
                result.add(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<String> getIngredientsFromDatabase() {
        return getIngredientsFromDatabase("SELECT ingredient FROM ingredient_quantity", "ingredient")
                .stream()
                .map(String.class::cast)
                .collect(Collectors.toList());
    }

    public static List<Short> getIngredientQuantityFromDatabase() {
        return getIngredientsFromDatabase("SELECT quantity FROM ingredient_quantity", "quantity")
                .stream()
                .map(Integer.class::cast) // Приведение к Integer
                .map(Integer::shortValue) // Преобразование к Short
                .collect(Collectors.toList());
    }

    ///////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////
    public void deleteIngredientByName(String ingredientName) {
        String query = "DELETE FROM ingredient_quantity WHERE ingredient =?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, ingredientName);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(getClass().getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public void deleteProductByName(String name) {
        String query = "DELETE FROM dish WHERE name =?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, name);
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(getClass().getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public static void updateIngredientQuantityInDatabase(String ingredient, int newQuantity) {
        String query = "UPDATE ingredient_quantity SET quantity =? WHERE ingredient =?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, newQuantity);
            pst.setString(2, ingredient);
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBProduct.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

}
