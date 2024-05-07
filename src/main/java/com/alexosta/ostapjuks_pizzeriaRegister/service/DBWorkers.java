package com.alexosta.ostapjuks_pizzeriaRegister.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBWorkers {

    private static final String url = "jdbc:postgresql://localhost:5432/users_info";
    private static final String user = "postgres";
    private static final String password = "Parole01!";

    public static void writeToDatabase(String userName, String userPassword) {

        String query = "INSERT INTO worker(name, password) VALUES(?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, userName);
            pst.setString(2, userPassword);
            pst.executeUpdate();

        }catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBWorkers.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

    }

    public static List<String> readFromDatabase(String colName) {

        List<String> colList = new ArrayList<>();

        String query = STR."SELECT \{colName} FROM worker";

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                colList.add(rs.getString(colName));
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBWorkers.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return colList;
    }

    public static boolean checkNameExists(String userName) {
        String query = "SELECT * FROM worker WHERE name =?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, userName);
            ResultSet rs = pst.executeQuery();

            return rs.next();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBWorkers.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    public static boolean checkPassExists(String userName) {
        String query = "SELECT * FROM worker WHERE password =?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, userName);
            ResultSet rs = pst.executeQuery();

            return rs.next();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBWorkers.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }
}
