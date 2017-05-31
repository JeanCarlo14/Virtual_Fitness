package com.example.usuario.virtual_fitness;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Usuario on 29/3/2017.
 */


public class DBConnection {
    private static DBConnection instance = null;
    private static final String URL = "jdbc:postgresql://10.0.2.2:5432/BD_Virtual_Fitness";  // "jdbc:postgresql://IP:PUERTO/DB", "USER", "PASSWORD");
    private static final String USER = "postgres";
    private static final String PASS = "admin123";
    private static  Connection connection = null;

    private DBConnection(){



    }

    public static DBConnection getInstance(){
        if(instance == null)
            instance =  new DBConnection();
        return instance;
    }

    public Connection getConnection(){
        if(connection ==null)
            connection = conectar();
        return connection;

    }

    private Connection conectar(){
        Connection conn = null;
        try{
            //  (new Driver()).getClass();
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
        }
        catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            System.out.println("oops! No se encuentra la clase. Error: " + e.getMessage());
        }
        return conn;
    }

}