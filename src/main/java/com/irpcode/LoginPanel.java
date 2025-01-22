package com.irpcode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public abstract class LoginPanel implements ActionListener {

    private static JFrame loginPanel;
    private static JButton loginButton;
    private static JButton exitButton;
    private static JTextField usernameField;
    private static JTextField passwordField;
    private static JTextField DBField;
    static String BASE_URL = "jdbc:mysql://localhost:3306/";
    private static boolean DBFlag = false;

    public static void loginDBPanel(boolean DBFlag, String query, int queryType) throws InstantiationException, ClassNotFoundException,
            IllegalAccessException, IOException, UnsupportedLookAndFeelException, InterruptedException {

        loginPanel = new JFrame("Log in to your database");
        UIMaker.frameStyle(loginPanel);
        loginPanel.setSize(500, 255);
        loginPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginPanel.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GroupLayout groupLayout = new GroupLayout(panel);

        JLabel DBLabel = new JLabel("Enter your primary database:");
        DBField = new JTextField("users");

        JLabel usernameLabel = new JLabel("Enter your username:");
        usernameField = new JTextField("root");

        JLabel passwordLabel = new JLabel("Enter your password:");
        passwordField = new JTextField("pass");

        JSeparator separator = new JSeparator();

        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(200, 35));

        if (DBFlag == true) {
            exitButton = new JButton("Cancel");
        } else {
            exitButton = new JButton("Exit");
        }

        exitButton.setPreferredSize(new Dimension(100, 20));

        panel.setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        loginPanel.add(panel);

        

        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(usernameLabel)
                .addComponent(usernameField)
                .addComponent(passwordLabel)
                .addComponent(passwordField)
                .addComponent(DBLabel)
                .addComponent(DBField)
                .addComponent(separator)
                .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE));

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addComponent(usernameLabel)
                .addComponent(usernameField)
                .addComponent(passwordLabel)
                .addComponent(passwordField)
                .addComponent(DBLabel)
                .addComponent(DBField)
                .addComponent(separator)
                .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE));

        exitButton.addActionListener(e -> {

            if (DBFlag == true) {
                loginPanel.dispose();
            } else {
                System.exit(0);
            }

        });
        loginButton.addActionListener(e -> {
            try {
                loginChecker(DBFlag, query, queryType);
            } catch (SQLException ex) {
                try {
                    DBActionOptions.crashError(null);
                } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | IOException
                        | UnsupportedLookAndFeelException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        Border border = BorderFactory.createLineBorder(Color.decode("#5d5f60"), 3);
        loginPanel.getRootPane().setBorder(border);

        loginPanel.setVisible(true);

    }

    public static void loginChecker(boolean DBFlag1, String query, int queryType) throws SQLException {

        String USER = usernameField.getText();
        String PASS = passwordField.getText();
        String DB_URL = BASE_URL + DBField.getText();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            System.out.println("Success");
            loginPanel.dispose();
            if (DBFlag1 == true) {
                SQLQuery.statementMaker(DB_URL, USER, PASS, query, queryType);
                System.out.println(query + queryType);
                DBFlagSetter();
            } else {
                UIMaker.setupUI(null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("Access denied for user")) {
                loginButton.setText("Access Denied");
            } else if (e.getMessage().contains("Unknown database")) {
                loginButton.setText("Unknown Database");
            } else {
                loginButton.setText("Server Communication Failure");
            }

        }

    }

    public static void DBFlagSetter() {
        DBFlag = false;
    }
}
