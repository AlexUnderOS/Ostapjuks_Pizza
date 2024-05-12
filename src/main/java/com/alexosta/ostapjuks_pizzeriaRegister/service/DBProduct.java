package com.alexosta.ostapjuks_pizzeriaRegister.service;

import javafx.scene.control.ListView;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBProduct {

    private static final String url = "jdbc:postgresql://localhost:5432/products";
    private static final String user = "postgres";
    private static final String password = "Parole01!";

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


    public void showProductInfo(int index) {
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT category, price, img, name, ingredient_list, minutes FROM product")) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getIngredientsFromDatabase() {
        List<String> ingredients = new ArrayList<>();
        String sql = "SELECT ingredient FROM ingredient_quantity";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = con.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String ingredient = rs.getString("ingredient");
                ingredients.add(ingredient);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ingredients;
    }

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
            pst.setString(4, name);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(getClass().getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public static List<Integer> getIngredientQuantityFromDatabase() {
        List<Integer> quantities = new ArrayList<>();
        String sql = "SELECT quantity FROM ingredient_quantity";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = con.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int quantity = rs.getInt("quantity");
                quantities.add(quantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return quantities;
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

    private List<String> categoryList = new ArrayList<>();
    private List<String> productList = new ArrayList<>();
    private List<Double> priceList = new ArrayList<>();
    private List<Integer> minList = new ArrayList<>();
    private List<String> imgList = new ArrayList<>();
    private List<String> ingredientList = new ArrayList<>();


    public void updateProductFromDatabase() {
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT category, price, img, name, ingredient_list, minutes FROM dish")) {

            while (rs.next()) {
                String category = rs.getString("category");
                String product = rs.getString("name");
                double price = rs.getDouble("price");
                int min = rs.getInt("minutes");
                categoryList.add(category);
                productList.add(product);
                priceList.add(price);
                minList.add(min);


//                String img = rs.getString("img");
//                String ingredient_list = rs.getString("ingredient_list");
//                imgList.add(img);
//                ingredientList.add(ingredient_list);
            }
        } catch (Exception e) {
            System.err.println("Error fetching data from database: " + e.getMessage());
        }
    }

    public List<String> getCategoryList() {
        return categoryList;
    }

    public List<String> getProductList() {
        return productList;
    }

    public List<Double> getPriceList() {
        return priceList;
    }

    public List<Integer> getMinList() {
        return minList;
    }
}
