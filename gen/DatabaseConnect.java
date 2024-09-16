package com.pack.gen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnect {

    static Connection connection= null;
    static String database = "";
    static String url="jdbc:mysql://localhost:3306/"+database;
    static String user="root";
    static String pass="root";

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        System.out.println("Loaded driver");
        connection = DriverManager.getConnection(url,user,pass);

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO 'studentdb'.'student'.('name') VALUES ('RAHUL')");

        int m = preparedStatement.executeUpdate();

        if(m!=0){
            System.out.println("data inserted in db");
        }
    }

}
