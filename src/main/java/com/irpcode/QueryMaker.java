package com.irpcode;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.UnsupportedLookAndFeelException;

//make sure user inputs are parameterized to prevent SQL injection
public class QueryMaker {

    static String BASE_URL = "jdbc:mysql://localhost:3306/";
    static String CHOSEN_DB = "users";
    static String DB_URL = BASE_URL + CHOSEN_DB;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InstantiationException, UnsupportedLookAndFeelException, ClassNotFoundException, IllegalAccessException, InterruptedException {

        //getUserInfo();
        LoginPanel.loginDBPanel();

    }

    public static void getUserInfo() {
        String USER = "root";
        String PASS = "pass";
        int yn = 1;
        //temp scanner

        System.out.println("Do you want to enter your username and password? (0/1 (y/n)): ");

        yn = scanner.nextInt();
        scanner.nextLine();

        if (yn == 1) {

            getQuery(USER, PASS, DB_URL);
        } else {
            System.out.println("Enter your username: ");
            USER = scanner.nextLine();
            System.out.println("Enter your password: ");
            PASS = scanner.nextLine();
            scanner.close();
            getQuery(USER, PASS, DB_URL);
        }

    }

    public static void getQuery(String USER, String PASS, String DB_URL) {
        //TODO: Get user information from UI to send query
        System.out.println("Enter a number between 1-10 to choose a query type: ");
        int type = scanner.nextInt();
        String query = "";
        int queryType = 0;

        switch (type) {
            //for data in tables
            case 1 -> {
                queryType = 1;
                query = "SELECT * FROM userInfo";
                SQLQuery.statementMaker(DB_URL, USER, PASS, query, queryType);
            }
            case 2 -> {
                queryType = 2;
                query = """
                        UPDATE userInfo
                        SET userEmail = 'JaneDoe@example.com'
                        WHERE userID > 0;
                         """ //
                        //
                        ; //TODO: Make this flexible depending on input
                SQLQuery.statementMaker(DB_URL, USER, PASS, query, queryType);
            }
            case 3 -> {
                queryType = 2;
                query = """
                        INSERT INTO userInfo
                        SET userEmail = 'JaneDoe@example.com'
                        WHERE userID > 0;
                         """ //
                        //
                        ; //TODO: Make this flexible depending on input
                SQLQuery.statementMaker(DB_URL, USER, PASS, query, queryType);
            }
            case 4 -> {
                queryType = 2;
                query = """
                        DELETE FROM userInfo
                        WHERE userID >2;
                         """ //
                        //
                        ; //TODO: Make this flexible depending on input
                SQLQuery.statementMaker(DB_URL, USER, PASS, query, queryType);
            }
            //for tables themselves
            case 5 -> {
                queryType = 3;
                query = """
                        CREATE TABLE supplies(
                        ItemID int,
                        itemName varchar(255),
                        itemPrice Double(10,2),
                        sold int
                        );
                         """ //
                        //
                        ; //TODO: Make this flexible depending on input
                SQLQuery.statementMaker(DB_URL, USER, PASS, query, queryType);
            }

            case 6 -> {
                queryType = 3;
                query = """
                        ALTER TABLE supplies
                        ADD numberInStock int; 
                         """ //
                        //
                        ; //TODO: Make this flexible depending on input
                SQLQuery.statementMaker(DB_URL, USER, PASS, query, queryType);
            }

            case 7 -> {
                queryType = 3;
                query = """
                        DROP TABLE supplies; 
                         """ //
                        //
                        ; //TODO: Make this flexible depending on input
                SQLQuery.statementMaker(DB_URL, USER, PASS, query, queryType);
            }
            //for database itself
            case 8 -> {
                queryType = 3;
                query = """
                        CREATE DATABASE supplierDB; 
                         """ //
                        //
                        ; //TODO: Make this flexible depending on input
                SQLQuery.statementMaker(DB_URL, USER, PASS, query, queryType);
            }

            case 9 -> {
                queryType = 3;
                query = """
                        DELETE DATABASE supplierDB; 
                         """ //
                        //
                        ; //TODO: Make this flexible depending on input
                SQLQuery.statementMaker(DB_URL, USER, PASS, query, queryType);
            }

            case 10 -> {
                System.out.println("Enter new database name: ");
                String input = scanner.nextLine();
                CHOSEN_DB = input;
                DB_URL = BASE_URL + CHOSEN_DB;
                System.out.println("Chosen DB is: " + DB_URL);
                scanner.close();
            }

        }

    }

    public static void printQueryResults(ResultSet results, ResultSetMetaData metaData, int resultsUpdated, boolean resultsExecuted) throws SQLException, IOException, InstantiationException, ClassNotFoundException, IllegalAccessException, UnsupportedLookAndFeelException {

        /*if (metaData != null) {
            int colCount = metaData.getColumnCount();
            for (int i = 1; i <= colCount; i++) {
                System.out.println(metaData.getColumnName(i) + "\t");
            }
            System.out.println();
            while (results.next()) { // Print rows
                for (int i = 1; i <= colCount; i++) {
                    
                    System.out.println(results.getString(i) + "\t");
                }
            }

            

            if (resultsUpdated > 0) {
                System.out.println("Entries updated: " + resultsUpdated);
            }

            if (resultsExecuted == true) {
                System.out.println("Entries executed: " + resultsExecuted);
            }

            

        }*/
        UIMaker.setupUI(results);
    }
}
