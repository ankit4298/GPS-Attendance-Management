/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ANKIT
 */
public class DBConnection {

    static Connection con = null;

    public static Connection getDBConnection() {
        
        try {
            String dbhost, dbname, dbuser, dbpass;
            dbhost = "jws-app-mysql:3306";
            dbname = "all_info";
            dbuser = "user";
            dbpass = "password";

            Class.forName("com.mysql.jdbc.Driver");

            String dbURL = "jdbc:mysql://" + dbhost + "/" + dbname;

            //user is DBConnectionClass name
            con = DriverManager.getConnection(dbURL, dbuser, dbpass);

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Driver not found");
        }

        return con;
    }

}
