package com.irpcode;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
    private static JSeparator buttonSeparator, buttonSeparator1;
    private static boolean loginSuccess = false;
    private static int queryType, DBEditorType;
    private static String dataTypes[] = { "CHAR(255)", "VARCHAR(100)", "BINARY(1)", "VARBINARY(25)", "MEDIUMTEXT",
            "MEDIUMBLOB", "LONGTEXT", "LONGBLOB", "BIT(1)", "BOOL", "INT(255)", "DOUBLE(10,2)" };

    public static void optionPanel(int selectedAction) throws IOException, InstantiationException,
            ClassNotFoundException, IllegalAccessException, UnsupportedLookAndFeelException {
        switch (selectedAction) {
            case 0 -> updateData();
            case 1 -> insertData();
            case 2 -> deleteData();
            case 3 -> createTable();
            case 4 -> editTable();
            case 5 -> deleteTable();
            case 6 -> changeDatabase();
            case 7 -> createDatabase();
            case 8 -> deleteDatabase();
        }
    }

    public static void updateData() {
        try {
            baseOptionsPanel(500, 300);
        } catch (Exception e) {
            optionsFrame.dispose();
            System.out.println("Fatal Error");
        }
    }

    public static void insertData() {
        try {
            baseOptionsPanel(500, 300);
        } catch (Exception e) {
            optionsFrame.dispose();
            System.out.println("Fatal Error");
        }
    }

    public static void deleteData() {
        try {
            baseOptionsPanel(500, 300);
        } catch (Exception e) {
            optionsFrame.dispose();
            System.out.println("Fatal Error");
        }
    }

    public static void createTable() throws IOException, InstantiationException, ClassNotFoundException,
            IllegalAccessException, UnsupportedLookAndFeelException {
        explanationString = "Make sure you are using a new table name.";
        explanationString2 = "Enter the name of the table you wish to create:";
        try {
            baseOptionsPanel(500, 800);
            tableEditorSetup(explanationString, explanationString2, 1, "Login");
        } catch (Exception e) {
            optionsFrame.dispose();
            System.out.println("Fatal Error");
            e.printStackTrace();
            crashError(null);
        }
    }

    public static void editTable() {
        try {
            baseOptionsPanel(500, 300);
        } catch (Exception e) {
            optionsFrame.dispose();
            System.out.println("Fatal Error");
        }
    }

    public static void deleteTable() throws IOException, InstantiationException, ClassNotFoundException,
            IllegalAccessException, UnsupportedLookAndFeelException {
        explanationString = "WARNING: THIS WILL DELETE YOUR TABLE!";
        explanationString2 = "Enter the table you wish to delete:";
        try {
            baseOptionsPanel(500, 300);
            tableEditorSetup(explanationString, explanationString2, 3, "Login");
        } catch (Exception e) {
            optionsFrame.dispose();
            System.out.println("Fatal Error");
            e.printStackTrace();
            crashError(null);
        }
    }

    public static void changeDatabase() throws IOException, InstantiationException, ClassNotFoundException,
            IllegalAccessException, UnsupportedLookAndFeelException {

    }

    public static void createDatabase() throws IOException, InstantiationException, ClassNotFoundException,
            IllegalAccessException, UnsupportedLookAndFeelException {
        explanationString = "Make sure you are using a new DB name.";
        explanationString2 = "Enter the database you wish to create:";
        try {
            baseOptionsPanel(500, 300);
            DBEditorSetup(explanationString, explanationString2, 2);
        } catch (Exception e) {
            optionsFrame.dispose();
            System.out.println("Fatal Error");
            e.printStackTrace();
            crashError(null);
        }
    }

    public static void deleteDatabase() throws IOException, InstantiationException, ClassNotFoundException,
            IllegalAccessException, UnsupportedLookAndFeelException {
        explanationString = "WARNING: THIS WILL DELETE YOUR DATABASE!";
        explanationString2 = "Enter the database you wish to delete:";
        try {
            baseOptionsPanel(500, 300);
            DBEditorSetup(explanationString, explanationString2, 3);
        } catch (Exception e) {
            optionsFrame.dispose();
            System.out.println("Fatal Error");
            e.printStackTrace();
            crashError(null);
        }
    }

    public static void baseOptionsPanel(int x, int y)
            throws IOException, InstantiationException, UnsupportedLookAndFeelException,
            ClassNotFoundException, IllegalAccessException {

        // basic setup
        optionsFrame = new JFrame();
        UIMaker.frameStyle(optionsFrame);
        optionsFrame.setSize(x, y);

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

    public static void tableEditorSetup(String labelText, String labelText2, int type, String executeText) {
        boolean DBFlag = true;
        explanationLabel = new JLabel(labelText);
        explanationLabel2 = new JLabel(labelText2);
        JTextField textField = new JTextField();
        buttonSeparator = new JSeparator();
        buttonSeparator1 = new JSeparator();
        JButton executeQueryButton = new JButton(executeText);
        cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(e -> {
            optionsFrame.dispose();
        });

        switch (type) {
            case 3 -> {
                GroupLayout optionsLayout = new GroupLayout(optionsFrame.getContentPane());
                optionsLayout.setAutoCreateGaps(true);
                optionsLayout.setAutoCreateContainerGaps(true);

                optionsLayout.setHorizontalGroup(optionsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(explanationLabel)
                        .addComponent(explanationLabel2)
                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, 300,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonSeparator)
                        .addComponent(executeQueryButton)
                        .addComponent(cancelButton));

                optionsLayout.setVerticalGroup(optionsLayout.createSequentialGroup()
                        .addComponent(explanationLabel)
                        .addComponent(explanationLabel2)
                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonSeparator)
                        .addComponent(executeQueryButton)
                        .addComponent(cancelButton));
                optionsFrame.setLayout(optionsLayout);

                //send instructions to login panel
                executeQueryButton.addActionListener(e -> {
                    textFieldText = textField.getText();
                    textFieldText = textField.getText();
                    query = "DROP TABLE " + textFieldText + ";";
                    queryType = 3;
                    try {
                        LoginPanel.loginDBPanel(DBFlag, query, queryType);
                    } catch (InstantiationException | ClassNotFoundException | IllegalAccessException
                            | IOException | UnsupportedLookAndFeelException | InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    optionsFrame.dispose();
                    loginSetter();
                });

            }
            case 2 -> {
            }
            case 1 -> {
                GroupLayout optionsLayout = new GroupLayout(optionsFrame.getContentPane());
                optionsLayout.setAutoCreateGaps(true);
                optionsLayout.setAutoCreateContainerGaps(true);

                GroupLayout.ParallelGroup hGroup = optionsLayout.createParallelGroup(); // Horizontal layout
                GroupLayout.SequentialGroup pGroup = optionsLayout.createSequentialGroup(); // Vertical layout

                JComboBox<String>[] dataTypesBoxes = new JComboBox[6];
                JTextField[] dataFields = new JTextField[6];
                JLabel explanationLabel3 = new JLabel("Enter the datatypes and column names for your table:");

                for (int i = 0; i < 6; i++) {
                    dataTypesBoxes[i] = new JComboBox<>(dataTypes);
                    dataFields[i] = new JTextField();
                }

                hGroup.addComponent(explanationLabel, GroupLayout.Alignment.CENTER);
                hGroup.addComponent(explanationLabel2, GroupLayout.Alignment.CENTER);
                hGroup.addComponent(textField, GroupLayout.Alignment.CENTER);
                hGroup.addComponent(buttonSeparator, GroupLayout.Alignment.CENTER);
                hGroup.addComponent(explanationLabel3, GroupLayout.Alignment.CENTER);

                for (int i = 0; i < 6; i++) {
                    hGroup.addComponent(dataTypesBoxes[i], GroupLayout.Alignment.CENTER);
                    hGroup.addComponent(dataFields[i], GroupLayout.Alignment.CENTER);

                }

                hGroup.addComponent(buttonSeparator1, GroupLayout.Alignment.CENTER);
                hGroup.addComponent(executeQueryButton, GroupLayout.Alignment.CENTER);
                hGroup.addComponent(cancelButton, GroupLayout.Alignment.CENTER);

                pGroup.addComponent(explanationLabel);
                pGroup.addComponent(explanationLabel2);
                pGroup.addComponent(textField);
                pGroup.addComponent(buttonSeparator);
                pGroup.addComponent(explanationLabel3);

                for (int i = 0; i < 6; i++) {
                    pGroup.addComponent(dataTypesBoxes[i]);
                    pGroup.addComponent(dataFields[i]);
                }

                pGroup.addComponent(buttonSeparator1);
                pGroup.addComponent(executeQueryButton);
                pGroup.addComponent(cancelButton);

                // Add the Parallel Groups to Sequential Groups for layout
                optionsLayout.setHorizontalGroup(hGroup);
                optionsLayout.setVerticalGroup(pGroup);

                // Set the layout to the frame
                optionsFrame.getContentPane().setLayout(optionsLayout);

                executeQueryButton.addActionListener(e -> {

                    String[] tableItems = new String[6];

                    //Build the Query
                    query = "CREATE TABLE " + textField.getText() + "(";
                    for (int i = 0; i < 6; i++){
                        tableItems[i] = dataFields[i].getText() + " " + dataTypesBoxes[i].getSelectedItem().toString();
                        if (i < 5){
                            tableItems[i] = tableItems[i] + ", ";
                        }
                        query = query + tableItems[i];
                    }
                    query = query + ");"; 

                    //Send the query off
                    queryType = 3;
                    try {
                        LoginPanel.loginDBPanel(DBFlag, query, queryType);
                    } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | IOException
                            | UnsupportedLookAndFeelException | InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    optionsFrame.dispose();
                    loginSetter();
                });

            }
        }

        optionsFrame.setVisible(true);

    }

    public static void DBEditorSetup(String labelText, String labelText2, int type) {
        boolean DBFlag = true;
        explanationLabel = new JLabel(labelText);
        explanationLabel2 = new JLabel(labelText2);
        JTextField textField = new JTextField();
        buttonSeparator = new JSeparator();
        JButton verifyCredentials = new JButton("Verify Credentials");
        cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(e -> {
            optionsFrame.dispose();
        });

        verifyCredentials.addActionListener(e -> {
            try {

                if (!textField.getText().equals("") && type == 1) {
                    // add functionality to this after you make your select statement for swapping
                    // DBs
                }

                else if (!textField.getText().equals("") && type == 2) {
                    textFieldText = textField.getText();
                    textFieldText = textField.getText();
                    query = "CREATE DATABASE " + textFieldText + ";";
                    queryType = 3;
                    LoginPanel.loginDBPanel(DBFlag, query, queryType);
                    optionsFrame.dispose();

                } else if (!textField.getText().equals("") && type == 3) {
                    textFieldText = textField.getText();
                    textFieldText = textField.getText();
                    query = "DROP DATABASE " + textFieldText + ";";
                    queryType = 3;
                    LoginPanel.loginDBPanel(DBFlag, query, queryType);
                    optionsFrame.dispose();
                }

                else {
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
