package com.alexosta.ostapjuks_pizzeriaRegister.service;

import javafx.scene.control.ListView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBProduct {

    private static final String url = "jdbc:postgresql://localhost:5432/users_info";
    private static final String user = "postgres";
    private static final String password = "Parole01!";

    public static void writeToDatabase(String category, double price, int quantity, String image, boolean available, String description, String name) {

        String query = "INSERT INTO product(category, price, quantity, available, description, name, image) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, category);
            pst.setDouble(2, price);
            pst.setInt(3, quantity);
            pst.setBoolean(4, available);
            pst.setString(5, description);
            pst.setString(6, name);
            pst.setString(7, image);
            pst.executeUpdate();
        }catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBProduct.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

    }

    public void showProductInfo(int index) {
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name, category, price, quantity FROM product")) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getProductsFromDatabase() {
        List<String> products = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name, category, price, quantity FROM product")) {

            while (rs.next()) {
                String product = "| " + rs.getString("name") +
                                 " | " + rs.getString("category") +
                                 " | " + rs.getDouble("price") + "(eur)" +
                                 " | " + rs.getInt("quantity") + " |";
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }
}
