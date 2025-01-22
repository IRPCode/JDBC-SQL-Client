package com.irpcode;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

public class DBActionOptions {
    private static JFrame optionsFrame;
    private static JLabel explanationLabel, explanationLabel2;
    private static String explanationString, explanationString2, query, USER, PASS, DB_URL, textFieldText;
    private static JButton cancelButton;
    private static boolean loginSuccess = false;
    private static int queryType, DBEditorType;

    public static void optionPanel(int selectedAction) throws IOException, InstantiationException,
            ClassNotFoundException, IllegalAccessException, UnsupportedLookAndFeelException {
        switch (selectedAction) {
            case 0 -> updateData();
            case 1 -> insertData();
            case 2 -> deleteData();
            case 3 -> createTable();
            case 4 -> editTable();
            case 5 -> deleteTable();
            case 6 -> createDatabase();
            case 7 -> editDatabase();
            case 8 -> deleteDatabase();
        }
    }

    public static void updateData() {
        try {
            baseOptionsPanel();
        } catch (Exception e) {
            optionsFrame.dispose();
            System.out.println("Fatal Error");
        }
    }

    public static void insertData() {
        try {
            baseOptionsPanel();
        } catch (Exception e) {
            optionsFrame.dispose();
            System.out.println("Fatal Error");
        }
    }

    public static void deleteData() {
        try {
            baseOptionsPanel();
        } catch (Exception e) {
            optionsFrame.dispose();
            System.out.println("Fatal Error");
        }
    }

    public static void createTable() {
        try {
            baseOptionsPanel();
        } catch (Exception e) {
            optionsFrame.dispose();
            System.out.println("Fatal Error");
        }
    }

    public static void editTable() {
        try {
            baseOptionsPanel();
        } catch (Exception e) {
            optionsFrame.dispose();
            System.out.println("Fatal Error");
        }
    }

    public static void deleteTable() {
        try {
            baseOptionsPanel();
        } catch (Exception e) {
            optionsFrame.dispose();
            System.out.println("Fatal Error");
        }
    }

    public static void createDatabase() {
        try {
            baseOptionsPanel();
        } catch (Exception e) {
            optionsFrame.dispose();
            System.out.println("Fatal Error");
        }
    }

    public static void editDatabase() {
        try {
            baseOptionsPanel();
        } catch (Exception e) {
            optionsFrame.dispose();
            System.out.println("Fatal Error");
        }
    }

    public static void deleteDatabase() throws IOException, InstantiationException, ClassNotFoundException,
            IllegalAccessException, UnsupportedLookAndFeelException {
        explanationString = "WARNING: THIS WILL DELETE YOUR DATABASE!";
        explanationString2 = "Enter the Database you wish to delete:";
        try {
            baseOptionsPanel();
            DBEditorSetup(explanationString, explanationString2, DBEditorType);
        } catch (Exception e) {
            optionsFrame.dispose();
            System.out.println("Fatal Error");
            e.printStackTrace();
            crashError(null);
        }
    }

    public static void baseOptionsPanel() throws IOException, InstantiationException, UnsupportedLookAndFeelException,
            ClassNotFoundException, IllegalAccessException {

        // basic setup
        optionsFrame = new JFrame();
        UIMaker.frameStyle(optionsFrame);
        optionsFrame.setSize(500, 300);
        optionsFrame.setVisible(true);
        optionsFrame.setLocationRelativeTo(null);

        // set border color
        Border border = BorderFactory.createLineBorder(Color.decode("#5d5f60"), 3); // 4f5153
        optionsFrame.getRootPane().setBorder(border);
    }

    public static void crashError(String errorThrown)
            throws IOException, InstantiationException, ClassNotFoundException,
            IllegalAccessException, UnsupportedLookAndFeelException {
        java.awt.Toolkit.getDefaultToolkit().beep();
        JFrame errorFrame = new JFrame("Error");
        JLabel errorLabel = new JLabel();
        JButton errorButton = new JButton();

        if (errorThrown != null) {
            errorLabel.setText(errorThrown);
            errorButton.setText("Close");
        } else {
            errorLabel.setText("A fatal error occured. Please reboot the program.");
            errorButton.setText("Close Program");
        }

        errorFrame.setSize(250, 100);
        
        errorButton.setPreferredSize(new Dimension(100, 25));

        Border border = BorderFactory.createLineBorder(Color.decode("#af1a1b"), 3);
        errorFrame.getRootPane().setBorder(border);

        UIMaker.frameStyle(errorFrame);

        GroupLayout errorLayout = new GroupLayout(errorFrame.getContentPane());
        errorLayout.setAutoCreateGaps(true);
        errorLayout.setAutoCreateContainerGaps(true);
        errorFrame.setLayout(errorLayout);
        // errorFrame.add(errorPanel);

        errorLayout.setHorizontalGroup(errorLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(errorLabel)
                .addComponent(errorButton)

        );

        errorLayout.setVerticalGroup(errorLayout.createSequentialGroup()
                .addComponent(errorLabel)
                .addComponent(errorButton)

        );

        errorButton.addActionListener(e -> {
            if (errorThrown != null) {
                errorFrame.dispose();
            } else {
                System.exit(0);
            }
        });

        errorFrame.setLocationRelativeTo(null);
        errorFrame.pack();
        errorFrame.setVisible(true);

    }

    public static void DBEditorSetup(String labelText, String labelText2, int type) {
        boolean DBFlag = true;
        explanationLabel = new JLabel(labelText);
        explanationLabel2 = new JLabel(labelText2);
        JTextField textField = new JTextField();
        JSeparator buttonSeparator = new JSeparator();
        JButton verifyCredentials = new JButton("Verify Credentials");
        cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(e -> {
            optionsFrame.dispose();
        });

        verifyCredentials.addActionListener(e -> {
            try {
                
                if (!textField.getText().equals("")) {
                    textFieldText = textField.getText();
                    textFieldText = textField.getText();
                    query = "DROP DATABASE " + textFieldText + ";";
                    queryType = 3;
                    LoginPanel.loginDBPanel(DBFlag, query, queryType);
                    optionsFrame.dispose();
                }
                else{
                    verifyCredentials.setText("Missing Text!");
                }

               
            } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | IOException
                    | UnsupportedLookAndFeelException | InterruptedException e1) {
                e1.printStackTrace();
            }
            loginSetter();
        });

        GroupLayout optionsLayout = new GroupLayout(optionsFrame.getContentPane());
        optionsLayout.setAutoCreateGaps(true);
        optionsLayout.setAutoCreateContainerGaps(true);

        optionsLayout.setHorizontalGroup(optionsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(explanationLabel)
                .addComponent(explanationLabel2)
                .addComponent(textField, GroupLayout.PREFERRED_SIZE, 300,
                        GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonSeparator)
                .addComponent(verifyCredentials)
                .addComponent(cancelButton));

        optionsLayout.setVerticalGroup(optionsLayout.createSequentialGroup()
                .addComponent(explanationLabel)
                .addComponent(explanationLabel2)
                .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSeparator)
                .addComponent(verifyCredentials)
                .addComponent(cancelButton));

        optionsFrame.setLayout(optionsLayout);
        optionsFrame.setVisible(true);
    }

    public static void loginSetter() {
        loginSuccess = false;
    }

    public static void credentialsSetter(String database, String username, String password) {
        DB_URL = database;
        USER = username;
        PASS = password;
    }
}
