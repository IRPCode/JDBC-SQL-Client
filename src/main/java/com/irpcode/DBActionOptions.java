package com.irpcode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
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
    private static String strInt[] = { "String", "Int" };
    private static String comparisonTypes[] = { "=", "<", ">" };
    private static String columnNames[] = new String[6];
    private static String columnNameTypes[] = new String[6];
    private static JButton getColumns;

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
            baseOptionsPanel(500, 525);
            dataEditorSetup("This section will allow you to insert info into a table",
                    "Enter the table you wish to add an entry to:", 2, "Insert");
        } catch (Exception e) {
            optionsFrame.dispose();
            System.out.println("Fatal Error");
        }
    }

    public static void deleteData() {
        try {
            baseOptionsPanel(500, 500);
            dataEditorSetup("Enter the table you wish to interact with:",
                    "Enter your condition to identify deletable entires:", 3, "Delete");
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

    public static void editTable() { // Tell the user the app only supports 6 columns, and this is where to modify
                                     // columns (prevent adding and dropping)
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

    public static void dataEditorSetup(String labelText, String labelText2, int type, String executeText) {
        boolean DBFlag = true;
        explanationLabel = new JLabel(labelText);
        explanationLabel2 = new JLabel(labelText2);

        JTextField textField = new JTextField();
        JSeparator getColumnSeparator = new JSeparator();
        buttonSeparator = new JSeparator();
        buttonSeparator1 = new JSeparator();
        JButton executeQueryButton = new JButton(executeText);
        cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(e -> {
            optionsFrame.dispose();
        });

        switch (type) {
            case 1 -> {

            }

            case 2 -> {
                GroupLayout optionsLayout = new GroupLayout(optionsFrame.getContentPane());
                optionsLayout.setAutoCreateGaps(true);
                optionsLayout.setAutoCreateContainerGaps(true);

                GroupLayout.ParallelGroup hGroup = optionsLayout.createParallelGroup(); // Horizontal layout
                GroupLayout.SequentialGroup pGroup = optionsLayout.createSequentialGroup(); // Vertical layout

                JLabel[] columnNameLabel = new JLabel[6];
                JTextField[] dataFields = new JTextField[6];
                JButton getColumns = new JButton("Get Columns!");

                for (int i = 0; i < 6; i++) {
                    columnNameLabel[i] = new JLabel("null - null:");
                    dataFields[i] = new JTextField();
                }

                hGroup.addComponent(explanationLabel, GroupLayout.Alignment.CENTER);
                hGroup.addComponent(explanationLabel2, GroupLayout.Alignment.CENTER);
                hGroup.addComponent(textField, GroupLayout.Alignment.CENTER);
                hGroup.addComponent(getColumns, GroupLayout.Alignment.CENTER);
                hGroup.addComponent(buttonSeparator, GroupLayout.Alignment.CENTER);

                for (int i = 0; i < 6; i++) {
                    hGroup.addComponent(columnNameLabel[i], GroupLayout.Alignment.CENTER);
                    hGroup.addComponent(dataFields[i], GroupLayout.Alignment.CENTER);

                }

                hGroup.addComponent(buttonSeparator1, GroupLayout.Alignment.CENTER);
                hGroup.addComponent(executeQueryButton, GroupLayout.Alignment.CENTER);
                hGroup.addComponent(cancelButton, GroupLayout.Alignment.CENTER);

                pGroup.addComponent(explanationLabel);
                pGroup.addComponent(explanationLabel2);
                pGroup.addComponent(textField);
                pGroup.addComponent(getColumns);
                pGroup.addComponent(buttonSeparator);

                for (int i = 0; i < 6; i++) {
                    pGroup.addComponent(columnNameLabel[i]);
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

                getColumns.addActionListener((ActionEvent e) -> {

                    try {
                        columnsGetter(textField.getText());
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    for (int i = 0; i < 6; i++) {
                        columnNameLabel[i].setText(columnNameTypes[i] + " - " + columnNames[i] + ":"); // columnNames[i]
                    }

                    optionsFrame.repaint();

                });

                executeQueryButton.addActionListener(e -> {

                    // Build the Query
                    query = "INSERT INTO " + textField.getText() + " (";
                    for (int i = 0; i < 6; i++) {

                        if (i < 5) {
                            columnNames[i] = columnNames[i] + ", ";
                        }
                        query = query + columnNames[i];
                        if (i == 5) {
                            query = query + ") VALUES (";
                        }
                    }

                    for (int i = 0; i < 6; i++) {

                        if (null == typeCheck(dataFields[i].getText())) {
                            query = query + "null";
                        }

                        else if ("String".equals(typeCheck(dataFields[i].getText()))) {
                            query = query + "\"" + dataFields[i].getText() + "\"";
                        }

                        else{
                            query = query + dataFields[i].getText();
                        }

                        if (i < 5) {
                            query = query + ", ";
                        } else {
                            query = query + ");";
                        }
                    }

                    // Send the query off
                    queryType = 3;
                    System.out.println(query);
                    try {
                        SQLQuery.statementMaker(DB_URL, USER, PASS, query, 3);
                    } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | IOException
                            | UnsupportedLookAndFeelException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    optionsFrame.dispose();
                    loginSetter();
                });
            }

            case 3 -> {
                JLabel condition = new JLabel("Enter the column to base your condition off of:");
                JLabel stringOrInt = new JLabel("Is it a string or an integer?");
                JLabel comparisonTypeLabel = new JLabel("Enter comparison type (use '=' for string):");
                GroupLayout optionsLayout = new GroupLayout(optionsFrame.getContentPane());
                JSeparator conditionSeperator = new JSeparator();
                JSeparator conditionSeparator2 = new JSeparator();
                JComboBox<String> columnBox = new JComboBox<>(new String[0]);
                JComboBox<String> strIntCB = new JComboBox<>(strInt);
                JComboBox<String> comparisonTypesCB = new JComboBox<>(comparisonTypes);
                getColumns = new JButton("Get Columns!");
                JTextField enterTable = new JTextField();

                optionsLayout.setAutoCreateGaps(true);
                optionsLayout.setAutoCreateContainerGaps(true);

                optionsLayout.setHorizontalGroup(optionsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(explanationLabel)
                        .addComponent(enterTable, GroupLayout.PREFERRED_SIZE, 300,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(getColumns)
                        .addComponent(getColumnSeparator)
                        .addComponent(explanationLabel2)
                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, 300,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(condition)
                        .addComponent(buttonSeparator)
                        .addComponent(textField)
                        .addComponent(columnBox)
                        .addComponent(conditionSeparator2)
                        .addComponent(stringOrInt)
                        .addComponent(strIntCB)
                        .addComponent(comparisonTypeLabel)
                        .addComponent(comparisonTypesCB)
                        .addComponent(conditionSeperator)
                        .addComponent(executeQueryButton)
                        .addComponent(cancelButton));

                optionsLayout.setVerticalGroup(optionsLayout.createSequentialGroup()
                        .addComponent(explanationLabel)
                        .addComponent(enterTable)
                        .addComponent(getColumns)
                        .addComponent(getColumnSeparator)
                        .addComponent(explanationLabel2)
                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(condition)
                        .addComponent(buttonSeparator)
                        .addComponent(columnBox)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textField)
                        .addComponent(conditionSeparator2)
                        .addComponent(stringOrInt)
                        .addComponent(strIntCB)
                        .addComponent(comparisonTypeLabel)
                        .addComponent(comparisonTypesCB)
                        .addComponent(conditionSeperator)
                        .addComponent(executeQueryButton)
                        .addComponent(cancelButton));
                optionsFrame.setLayout(optionsLayout);

                getColumns.addActionListener(e -> {

                    if (!enterTable.getText().isEmpty()) {
                        try {
                            columnBox.setModel(new DefaultComboBoxModel<>(columnsGetter(enterTable.getText())));
                            optionsFrame.repaint();
                        } catch (SQLException ex) {
                        }
                    }

                    else {
                        getColumns.setText("Enter table!");
                    }

                });

                 // send instructions to login panel
                 executeQueryButton.addActionListener(e -> {
                    textFieldText = textField.getText();
                    textFieldText = textField.getText();
                    query = "DELETE FROM " + enterTable.getText() + " WHERE " + columnBox.getSelectedItem().toString() + " " + comparisonTypesCB.getSelectedItem().toString() + " ";
                    
                    if ("String".equals(strIntCB.getSelectedItem().toString())) {
                        query = query + "\"" + textField.getText() + "\"" + ";";
                    }
                    else {
                        query = query + textField.getText() + ";";
                    }
                    
                    
                    System.out.println(query);
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
        }
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

                // send instructions to login panel
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

                    // Build the Query
                    query = "CREATE TABLE " + textField.getText() + "(";
                    for (int i = 0; i < 6; i++) {
                        tableItems[i] = dataFields[i].getText() + " " + dataTypesBoxes[i].getSelectedItem().toString();
                        if (i < 5) {
                            tableItems[i] = tableItems[i] + ", ";
                        }
                        query = query + tableItems[i];
                    }
                    query = query + ");";

                    // Send the query off
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

    public static String[] columnsGetter(String tableName) throws SQLException {

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement statement = conn.createStatement();) {
            ResultSet getColQuery = statement.executeQuery("SELECT * FROM " + tableName + ";");
            ResultSetMetaData metaData = getColQuery.getMetaData();
            System.out.println(tableName);
            for (int i = 1; i < 7; i++) {
                columnNames[i - 1] = metaData.getColumnName(i);
                columnNameTypes[i - 1] = metaData.getColumnTypeName(i);
                System.out.println(columnNames[i - 1]);
            }

        } catch (Exception e) {

        }
        return columnNames;
    }

    @SuppressWarnings("UnnecessaryTemporaryOnConversionFromString")
    public static String typeCheck(String value) {
        try {
            Integer.parseInt(value);
            return "Integer";
        } catch (NumberFormatException e1) {
            try {
                Double.parseDouble(value);
                return "Double";
            } catch (NumberFormatException e2) {
                return "String";
            }
        }
    }
}
