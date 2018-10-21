package tn.esprit.twin.ninja.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Karray
 */
public class MyConnection {

    static MyConnection instance;
    static Connection cnx;
    String url = "jdbc:mysql://localhost:3306/esprit";
    String login = "root";
    String pwd = "";

    private MyConnection() {
        try {
            cnx = DriverManager.getConnection(url, login, pwd);

            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();
        }
        return instance;
    }

    public  Connection getConnection() {
        return cnx;
    }

}
