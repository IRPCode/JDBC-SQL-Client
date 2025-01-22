package com.irpcode;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.UnsupportedLookAndFeelException;

//String sql = "SELECT * FROM users WHERE username = ?";
//String sql = "INSERT INTO users (username, role) VALUES (?, ?)";
//String sql = "SELECT * FROM users WHERE username = ? AND role = ?";
//Make sure inputs are treated as data and not as SQL queries.
//Make sure to use PreparedStatement to prevent SQL injection.
//Read this: https://stackoverflow.com/questions/8918157/how-do-i-convert-a-resultset-in-a-list-of-hashtable
//Try saving entries from ResultSet in a HashMap using ResultSet.next() to see what it does

public class SQLQuery {

    public static String[] returnedData;

    @SuppressWarnings("CallToPrintStackTrace")
    public static void statementMaker(String DB_URL, String USER, String PASS, String query, int queryType) throws IOException, InstantiationException, ClassNotFoundException, IllegalAccessException, UnsupportedLookAndFeelException {
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
         
            //QueryMaker.printQueryResults(results, metaData, resultsUpdated, resultsExecuted);
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("database doesn't exist")){
                String errorMessage = "Database doesn't exist";
                DBActionOptions.crashError(errorMessage);
            }
        }
    }

}
