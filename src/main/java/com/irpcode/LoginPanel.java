package com.irpcode;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public abstract class LoginPanel implements ActionListener {

    private static JFrame loginPanel;
    private static JButton loginButton;
    private static JButton exitButton;
    private static JTextField usernameField;
    private static JTextField passwordField;
    private static JTextField DBField;
    static String BASE_URL = "jdbc:mysql://localhost:3306/";

    public static void loginDBPanel() throws InstantiationException, ClassNotFoundException, IllegalAccessException, IOException, UnsupportedLookAndFeelException, InterruptedException { //gleeble glorp borp gotta make da class swapper pannel 
        //don't forget to make a log-in panel :O

        loginPanel = new JFrame("Log in to your database");
        UIMaker.frameStyle(loginPanel);
        loginPanel.setSize(500, 200);
        loginPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginPanel.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GroupLayout groupLayout = new GroupLayout(panel);

        JLabel usernameLabel = new JLabel("Enter your username:");
        usernameField = new JTextField("root");

        JLabel passwordLabel = new JLabel("Enter your password:");
        passwordField = new JTextField("pass");

        JLabel DBLabel = new JLabel("Enter your Database:");
        DBField = new JTextField("users"); //TODO: Add this later

        JSeparator separator = new JSeparator();

        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(200, 35));

        exitButton = new JButton("Exit");
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
                .addComponent(separator)
                .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addComponent(usernameLabel)
                .addComponent(usernameField)
                .addComponent(passwordLabel)
                .addComponent(passwordField)
                .addComponent(separator)
                .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        exitButton.addActionListener(e -> System.exit(0));
        loginButton.addActionListener(e -> {
            try {
                loginChecker();
            } catch (SQLException ex) {
            }
        });

        loginPanel.setVisible(true);

    }

    public static void loginChecker() throws SQLException {

        String USER = usernameField.getText();
        String PASS = passwordField.getText();
        String DB_URL = BASE_URL + DBField.getText();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            System.out.println("Success");
            loginPanel.dispose();
            UIMaker.setupUI(null);

        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("Access denied for user")) {
                loginButton.setText("Access Denied");
            } else {
                loginButton.setText("Server Communication Failure");
            }

        }

    }
}
