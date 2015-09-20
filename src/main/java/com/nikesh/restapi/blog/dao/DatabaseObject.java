package com.nikesh.restapi.blog.dao;

import com.nikesh.restapi.blog.model.Post;
import java.util.ArrayList;
import java.util.List;

import com.nikesh.restapi.blog.model.Profile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DatabaseObject {

    private static Connection connection = null;

    public static final String host = "jdbc:mysql://localhost:3306/blog", user = "root", password = "n1k35h";

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                connection = DriverManager.getConnection(host, user, password);
                System.out.println("Connection Successful.");
            } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
                // ERROR MESSAGE HERE
            }
        }
        return connection;
    }
}
