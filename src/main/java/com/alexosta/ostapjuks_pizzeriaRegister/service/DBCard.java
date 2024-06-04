package com.alexosta.ostapjuks_pizzeriaRegister.service;

import java.sql.*;

public class DBCard {
    private static final String url = "jdbc:postgresql://aws-0-eu-central-1.pooler.supabase.com:6543/postgres";
    private static final String user = "postgres.xmcmzqjbrnjvelcauurb";
    private static final String password = "S$s7J!D/zic3t3V";

    public static boolean checkCard(String cardNumber) {
        String query = "SELECT * FROM card WHERE number = ?";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, cardNumber);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean withdrawMoney(String cardNumber, double amount) {
        String query = "UPDATE card SET balance = balance - ? WHERE number = ?";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setDouble(1, amount);
            pst.setString(2, cardNumber);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static double getBalance(String cardNumber) {
        String query = "SELECT balance FROM card WHERE number = ?";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, cardNumber);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("balance");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static void setBalance(String cardNumber, double newBalance) {
        String query = "UPDATE card SET balance = ? WHERE number = ?";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setDouble(1, newBalance);
            pst.setString(2, cardNumber);
            int rowsAffected = pst.executeUpdate();  // Важно: выполняем обновление
            if (rowsAffected == 0) {
                System.out.println("No rows updated, check if the card number is correct.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static String getFirstCardNumber() {
        String query = "SELECT number FROM card LIMIT 1";
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getString("number");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}