package com.blackship.battlesheep.database;

import java.sql.*;

public class DatabaseConnectionHandler {

    public static void getConnection() {

        try {
            Driver myDriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver( myDriver );

            String url = "jdbc:mysql://localhost:3306/battlesheep_db";

            Connection connection = DriverManager.getConnection(url, "root", "dupadupa");

            Statement statement = connection.createStatement();
//            statement.execute("create table dupadupa (id int not null auto_increment primary key, text varchar(255));");
//            statement.executeBatch();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
