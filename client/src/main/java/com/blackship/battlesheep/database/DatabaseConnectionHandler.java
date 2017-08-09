package com.blackship.battlesheep.database;

import com.blackship.battlesheep.utils.LogUtils;
import org.slf4j.Logger;

import java.sql.*;
import java.util.Optional;

public class DatabaseConnectionHandler {

    private static final Logger log = LogUtils.getLogger();
    private static Connection connection;

    public static Optional<Connection> getConnection(String url, String user, String password) {

        try {
            Driver myDriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(myDriver);

//            String url = "jdbc:mysql://localhost:3306/battlesheep_db";
            log.info("...Establishing connection to database...");
            connection = DriverManager.getConnection(url, user, password);
            return Optional.of(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveWinnerResult(String winner) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(String.format("CALL updateOrInsertPlayer('%s')", winner));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
