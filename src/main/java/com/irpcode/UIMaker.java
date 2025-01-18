package com.irpcode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLaf;

public abstract class UIMaker extends JFrame implements ActionListener {

    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static private JButton exitButton, openPanel;
    static JFrame frame;

    public static void setupUI(ResultSet results) throws IOException, InstantiationException,
            UnsupportedLookAndFeelException, ClassNotFoundException, IllegalAccessException, SQLException {

        String foundType = "";

        frame = new JFrame("JDBC SQL Client");

        frameStyle(frame);
        // frame.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
        frame.setSize(1250, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        // frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

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
                        .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 15,
                                GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED) // Space between components
                .addComponent(topLine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE));
        String[] DBActionsArray = { "Update Data", "Insert Data", "Delete Data", "Create Table",
                "Edit Table", "Delete Table", "Create Database", "Delete Database", "Change Database" };
        JList<String> DBActionsList = new JList<>(DBActionsArray);
        openPanel = new JButton("Open Query Options");
        openPanel.addActionListener(e -> openPanelOptionsChooser(DBActionsList));

        JPanel rightPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JLabel optionsLabel = new JLabel("<html><b>Database Options</b></html>");

        leftPanel.setLayout(new java.awt.BorderLayout());
        leftPanel.add(optionsLabel, java.awt.BorderLayout.NORTH);
        leftPanel.add(new JScrollPane(DBActionsList), java.awt.BorderLayout.CENTER);
        leftPanel.add(openPanel, java.awt.BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(150); // Set initial divider location
        splitPane.setOneTouchExpandable(false); // Add a little button to toggle
        splitPane.setEnabled(false);
        frame.setLayout(new java.awt.BorderLayout());
        frame.add(topPanel, java.awt.BorderLayout.NORTH);
        frame.add(splitPane, java.awt.BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void frameStyle(JFrame frame) throws IOException, InstantiationException,
            UnsupportedLookAndFeelException, ClassNotFoundException, IllegalAccessException {
        try {
            FlatLaf.setUseNativeWindowDecorations(false); // Disable native decorations
            UIManager.setLookAndFeel(new FlatDarculaLaf());
            frame.setUndecorated(true);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
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
        System.out.println(icon);
    }

    public static void openPanelOptionsChooser(@SuppressWarnings("rawtypes") JList DBActionsList) {
        int selectedAction = DBActionsList.getSelectedIndex();
        System.out.println(selectedAction);
        if (selectedAction > -1){
            openPanel.setText("Open Query Options");
            DBActionOptions.optionPanel(selectedAction);
        }
        else{
            openPanel.setText("Select An Option");
        }
        
    }

}
