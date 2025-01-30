package com.irpcode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLaf;

public abstract class UIMaker extends JFrame implements ActionListener {

    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static JButton exitButton, openPanel;
    static JFrame frame;
    private static String DB_URL, USER, PASS, tableName;
    private static final String columnNames[] = new String[6];
    private static final String columnNameTypes[] = new String[6];
    private static Map<Integer, String> DBResults;
    private static int pageNumber = 1;
    private static int pageResults = 0;

    public static void setupUI(ResultSet results) throws IOException, InstantiationException,
            UnsupportedLookAndFeelException, ClassNotFoundException, IllegalAccessException, SQLException {

        frame = new JFrame("JDBC SQL Client");

        frameStyle(frame);
        frame.setSize(1250, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel topPanel = new JPanel();
        GroupLayout exitLayout = new GroupLayout(topPanel);
        topPanel.setLayout(exitLayout);
        exitLayout.setAutoCreateGaps(true);
        exitLayout.setAutoCreateContainerGaps(true);
        JLabel appLabel = new JLabel("<html><b>JDBC SQL Client</b></html>");

        exitButton = new JButton();
        exitButton.addActionListener(e -> System.exit(0));
        exitButton.setBackground(new Color(255, 0, 0, 150));

        JSeparator topLine = new JSeparator();

        exitLayout.setHorizontalGroup(exitLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(appLabel)
                .addGroup(exitLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE) // Push exitButton to the right
                        .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 45,
                                GroupLayout.PREFERRED_SIZE))
                .addComponent(topLine, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        exitLayout.setVerticalGroup(exitLayout.createSequentialGroup()
                .addGroup(exitLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(appLabel)
                        .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED) // Space between components
                .addComponent(topLine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE));

        String[] DBActionsArray = { "Update Data", "Insert Data", "Delete Data", "Create Table",
                "Edit Table", "Delete Table", "Change Database", "Create Database", "Delete Database" };
        JList<String> DBActionsList = new JList<>(DBActionsArray);
        openPanel = new JButton("Open Query Options");
        openPanel.setBackground(Color.decode("#456b98"));
        openPanel.addActionListener(e -> {
            try {
                openPanelOptionsChooser(DBActionsList);
            } catch (IOException | InstantiationException | ClassNotFoundException | IllegalAccessException
                    | UnsupportedLookAndFeelException ex) {
            }
        });

        JPanel rightPanel = new JPanel(new GridBagLayout());
        JPanel leftPanel = new JPanel();
        JLabel optionsLabel = new JLabel("<html><b>Database Options</b></html>");

        leftPanel.setLayout(new java.awt.BorderLayout());
        leftPanel.add(optionsLabel, java.awt.BorderLayout.NORTH);
        leftPanel.add(new JScrollPane(DBActionsList), java.awt.BorderLayout.CENTER);
        leftPanel.add(openPanel, java.awt.BorderLayout.SOUTH);

        // right panel objects

        GridBagConstraints returnedDataConstraint = new GridBagConstraints();
        returnedDataConstraint.insets = new Insets(5, 5, 5, 5);
        returnedDataConstraint.fill = GridBagConstraints.HORIZONTAL;
        returnedDataConstraint.weightx = 1.0;
        JLabel[] columnName = new JLabel[6];
        JTextField[] SQLReturnedData = new JTextField[60];
        JTextField specifyQuery = new JTextField();
        JComboBox specifyColumn = new JComboBox<>(columnNames);
        JButton search = new JButton("⌕");
        JLabel pageLabel = new JLabel("Page: 1");

        JButton previous = new JButton("←");
        JButton next = new JButton("→");

        JLabel specifyTable = new JLabel("Enter table:");
        JTextField tableNameField = new JTextField();
        JButton selectTable = new JButton("Select Table/Show All");

        returnedDataConstraint.gridx = 0 % 6; // Column
        returnedDataConstraint.gridy = 0 / 6; // Row
        rightPanel.add(specifyTable, returnedDataConstraint);

        returnedDataConstraint.gridx = 1 % 6; // Column
        returnedDataConstraint.gridy = 1 / 6; // Row
        rightPanel.add(tableNameField, returnedDataConstraint);

        returnedDataConstraint.gridx = 2 % 6; // Column
        returnedDataConstraint.gridy = 2 / 6; // Row
        selectTable.setBackground(Color.decode("#456b98"));
        rightPanel.add(selectTable, returnedDataConstraint);

        returnedDataConstraint.gridx = 3 % 6; // Column
        returnedDataConstraint.gridy = 3 / 6; // Row
        rightPanel.add(specifyColumn, returnedDataConstraint);

        returnedDataConstraint.gridx = 4 % 6; // Column
        returnedDataConstraint.gridy = 4 / 6; // Row
        rightPanel.add(specifyQuery, returnedDataConstraint);

        returnedDataConstraint.gridx = 5 % 6; // Column
        returnedDataConstraint.gridy = 5 / 6; // Row
        search.setBackground(Color.decode("#456b98"));
        rightPanel.add(search, returnedDataConstraint);

        for (int i = 0; i < 66; i++) {
            if (i < 6) {
                columnName[i] = new JLabel();
                columnName[i].setText(columnNameTypes[i] + " - " + columnNames[i] + ":");
                returnedDataConstraint.gridx = (i + 12) % 6; // Column
                returnedDataConstraint.gridy = (i + 12) / 6; // Row
                rightPanel.add(columnName[i], returnedDataConstraint);
            } else {
                SQLReturnedData[i - 6] = new JTextField(15);
                returnedDataConstraint.gridx = (i + 12) % 6; // Column
                returnedDataConstraint.gridy = (i + 12) / 6; // Row
                rightPanel.add(SQLReturnedData[i - 6], returnedDataConstraint);
            }
        }

        returnedDataConstraint.gridx = 80 % 6; // Column
        returnedDataConstraint.gridy = 80 / 6; // Row
        previous.setBackground(Color.decode("#456b98"));
        rightPanel.add(previous, returnedDataConstraint);

        returnedDataConstraint.gridx = 81 % 6; // Column
        returnedDataConstraint.gridy = 81 / 6; // Row
        next.setBackground(Color.decode("#456b98"));
        rightPanel.add(next, returnedDataConstraint);

        returnedDataConstraint.gridx = 84 % 6; // Column
        returnedDataConstraint.gridy = 84 / 6; // Row
        rightPanel.add(pageLabel, returnedDataConstraint);

        // right panel button functions

        next.addActionListener(e -> { // next button
            if (DBResults != null) {
                pageNumber = pageNumber + 1;
                pageResults = pageResults + 60;
                pageLabel.setText("Page: " + pageNumber);
                for (int i = 0; i < 60; i++) {
                    SQLReturnedData[i].setText(DBResults.get(i + pageResults));
                }
                rightPanel.repaint();
            }
        });

        previous.addActionListener(e -> { // previous button
            if (pageNumber > 1 && DBResults != null) {
                pageNumber = pageNumber - 1;
                pageResults = pageResults - 60;
                pageLabel.setText("Page: " + pageNumber);
                for (int i = 0; i < 60; i++) {
                    SQLReturnedData[i].setText(DBResults.get(i + pageResults));
                }
                rightPanel.repaint();
            }
        });

        search.addActionListener(e -> { // search button functions
            String specifyColumnText = specifyColumn.getSelectedItem().toString();
            String customQuery = specifyQuery.getText();
            for (int i = 0; i < 60; i++) {
                SQLReturnedData[i].setText(DBResults.get(i));
            }
            try {
                getDBData(DB_URL, USER, PASS,
                        ("SELECT * FROM " + tableName + " WHERE " + specifyColumnText + " = " + customQuery + ";"));
            } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | IOException
                    | UnsupportedLookAndFeelException | SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            rightPanel.repaint();
        });

        selectTable.addActionListener(e -> { // selects table or shows all returned items from select query
            tableName = tableNameField.getText();
            try {
                getDBData(DB_URL, USER, PASS, ("SELECT * FROM " + tableName + ";"));
            } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | IOException
                    | UnsupportedLookAndFeelException | SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            for (int i = 0; i < 6; i++) {
                if (i < 6) {
                    columnName[i].setText(columnNameTypes[i] + " - " + columnNames[i] + ":");
                }
            }

            if (DBResults != null) {
                pageNumber = 1;
                pageResults = 0;
                pageLabel.setText("Page: " + pageNumber);
                for (int i = 0; i < 60; i++) {
                    SQLReturnedData[i].setText(DBResults.get(i + pageResults));
                }
            }
            specifyColumn.setModel(new DefaultComboBoxModel<>(columnNames));
            rightPanel.repaint();
        });

        // set panel up and render panel

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(150);
        splitPane.setOneTouchExpandable(false);
        splitPane.setEnabled(false);
        frame.setLayout(new java.awt.BorderLayout());
        frame.add(topPanel, java.awt.BorderLayout.NORTH);
        frame.add(splitPane, java.awt.BorderLayout.CENTER);
        Border border = BorderFactory.createLineBorder(Color.decode("#5d5f60"), 3);
        frame.getRootPane().setBorder(border);
        frame.setVisible(true);
    }

    public static void frameStyle(JFrame frame) throws IOException, InstantiationException,
            UnsupportedLookAndFeelException, ClassNotFoundException, IllegalAccessException {
        try {
            FlatLaf.setUseNativeWindowDecorations(false); // Disable native decorations
            UIManager.setLookAndFeel(new FlatDarculaLaf());
            frame.setUndecorated(true);
        } catch (UnsupportedLookAndFeelException e) {
            DBActionOptions.crashError(null);
        }

        Image icon = null;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        if (screenSize.width >= 2160) {
            icon = ImageIO.read(new File("jdbcsqlclient\\resources\\DBIcon256x.png"));
        } else if (screenSize.width >= 1440) {
            icon = ImageIO.read(new File("jdbcsqlclient\\resources\\DBIcon48x.png"));
        } else if (screenSize.width >= 1080) {
            icon = ImageIO.read(new File("jdbcsqlclient\\resources\\DBIcon.32xpng"));
        } else {
            icon = ImageIO.read(new File("jdbcsqlclient\\resources\\DBIcon.24xpng"));
        }

        frame.setIconImage(icon);
        // System.out.println(icon);
    }

    // send selected option to the DBActionOptions class
    public static void openPanelOptionsChooser(@SuppressWarnings("rawtypes") JList DBActionsList) throws IOException,
            InstantiationException, ClassNotFoundException, IllegalAccessException, UnsupportedLookAndFeelException {
        int selectedAction = DBActionsList.getSelectedIndex();
        System.out.println(selectedAction);
        if (selectedAction > -1) {
            openPanel.setText("Open Query Options");
            DBActionOptions.optionPanel(selectedAction);
        } else {
            openPanel.setText("Select An Option");
        }

    }

    // ensures that credentials are up to date
    public static void credentialsMainPanelSetter(String url, String username, String password, boolean flag) {
        if (flag == true) {
            DB_URL = url;
        } else {
            DB_URL = url;
            USER = username;
            PASS = password;
        }
        System.out.println(DB_URL + USER + PASS);
    }

    public static ResultSet getDBData(String DB_URL, String USER, String PASS, String query) throws IOException,
            InstantiationException, ClassNotFoundException, IllegalAccessException, UnsupportedLookAndFeelException,
            SQLException { // specifically for the select statement in this class, gets columns and
                           // database information
        ResultSet results = null;
        DBResults = new HashMap<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement statement = conn.createStatement();) {
            @SuppressWarnings("unused")
            ResultSetMetaData metaData = null;
            results = statement.executeQuery(query);
            metaData = results.getMetaData();

            for (int i = 1; i < 7; i++) {
                columnNames[i - 1] = metaData.getColumnName(i);
                columnNameTypes[i - 1] = metaData.getColumnTypeName(i);
                System.out.println(columnNames[i - 1]);
            }

            int x = 0;
            while (results.next()) {
                for (int i = 1; i <= 6; i++) {
                    DBResults.put(x * 6 + i - 1, results.getString(i));
                }
                x++;
            }
            System.out.println(DBResults);

        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("database doesn't exist")) {
                String errorMessage = "Database doesn't exist";
                DBActionOptions.crashError(errorMessage);
            }
        }
        return results;
    }

}
