package com.irpcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

//String sql = "SELECT * FROM users WHERE username = ?";
//String sql = "INSERT INTO users (username, role) VALUES (?, ?)";
//String sql = "SELECT * FROM users WHERE username = ? AND role = ?";
//Make sure inputs are treated as data and not as SQL queries.
//Make sure to use PreparedStatement to prevent SQL injection.

public class SQLQuery {

    public static String[] returnedData;

    public static void statementMaker(String DB_URL, String USER, String PASS, String query, int queryType) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = conn.createStatement();) {
            ResultSet results = null;
            int resultsUpdated = 0;
            boolean resultsExecuted = false;
            ResultSetMetaData metaData = null;

            switch (queryType) {
                case 1 -> {
                    results = statement.executeQuery(query);
                    metaData = results.getMetaData();
                }
                case 2 -> {
                    resultsUpdated = statement.executeUpdate(query);
                    System.out.println(resultsUpdated);

                }
                case 3 -> {
                    resultsExecuted = statement.execute(query);
                    System.out.println(resultsExecuted);

                }
            }
         
            QueryMaker.printQueryResults(results, metaData, resultsUpdated, resultsExecuted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
