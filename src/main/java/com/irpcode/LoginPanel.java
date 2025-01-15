package com.irpcode;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

    private static JButton loginButton;
    private static JButton exitButton;

    public static void loginDBPanel() throws InstantiationException, ClassNotFoundException, IllegalAccessException, IOException, UnsupportedLookAndFeelException, InterruptedException { //gleeble glorp borp gotta make da class swapper pannel 
        //don't forget to make a log-in panel :O

        JFrame loginPanel = new JFrame("Log in to your database");
        UIMaker.frameStyle(loginPanel);
        loginPanel.setSize(500, 200);
        loginPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginPanel.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GroupLayout groupLayout = new GroupLayout(panel);

        JLabel usernameLabel = new JLabel("Enter your username:");
        JTextField usernameField = new JTextField("root");

        JLabel passwordLabel = new JLabel("Enter your password:");
        JTextField passwordField = new JTextField("pass");

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

        loginPanel.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Check which button was clicked
        if (e.getSource() == loginButton) {
            System.out.println("Login button clicked!");
            // Add login logic here (e.g., validate credentials)
        } else if (e.getSource() == exitButton) {
            System.out.println("Exit button clicked!");
            System.exit(0); // Exit the application
        }
    }
}
