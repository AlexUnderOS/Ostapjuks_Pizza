package com.alexosta.ostapjuks_pizzeriaRegister.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBWorkers {

    private static final String url = "jdbc:postgresql://aws-0-eu-central-1.pooler.supabase.com:6543/postgres";
    private static final String user = "postgres.xmcmzqjbrnjvelcauurb";
    private static final String password = "S$s7J!D/zic3t3V";

    public static void writeToDatabase(String userName, String userPassword, boolean isAdmin) {

        String query = "INSERT INTO worker(name, password, admin) VALUES(?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, userName);
            pst.setString(2, userPassword);
            pst.setBoolean(3, isAdmin);
            pst.executeUpdate();

        }catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBWorkers.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

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

    public static boolean checkAdminStatus(String userName) {
        String query = "SELECT admin FROM worker WHERE name =?";
        boolean isAdmin = false;
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, userName);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                isAdmin = rs.getBoolean("admin");
            }
            return isAdmin;
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBWorkers.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }
}
