package coffee;
/*
QR Attendy base on Website and WebApp lol
Develop by BELDAD-Ace on Github with the team group 1 for PR2
aka Jhon Benedict Belad

all rights reserved 2025

*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// SQL YES YES
public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3925/attendy_db"; //SQL Server bruh
    private static final String USER = "root"; //user 
    private static final String PASSWORD = "NITROgen_jb09"; //passywasy

        static {
        try {
            // Force-load the driver (Tomcat sometimes won’t auto-detect BECAUSE FUCK TOMCAT)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
