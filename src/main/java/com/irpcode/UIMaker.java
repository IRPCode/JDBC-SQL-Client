package com.irpcode;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLaf;

public abstract class UIMaker extends JFrame implements ActionListener {

    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static JRadioButton updateButton, insertButton, deleteButton, createTableButton, alterTableButton, dropTableButton, createDBButton, deleteDBButton;
    static JButton selectStatementButton, runQueryButton, changeDBButton;
    static JFrame frame;

    public static void setupUI(ResultSet results) throws IOException, InstantiationException, UnsupportedLookAndFeelException, ClassNotFoundException, IllegalAccessException, SQLException {

        String foundType = "";
        frame = new JFrame("JDBC SQL Client");
        frameStyle(frame);
        frame.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        ButtonGroup DBBehaivor = new ButtonGroup();
        DBBehaivor.add(updateButton);
        DBBehaivor.add(insertButton);
        DBBehaivor.add(deleteButton);
        DBBehaivor.add(createTableButton);
        DBBehaivor.add(alterTableButton);
        DBBehaivor.add(dropTableButton);
        DBBehaivor.add(createDBButton);
        DBBehaivor.add(deleteDBButton);

        if (results.next()) {
            foundType = results.getString(1);
            System.out.println(foundType);
        }

        JLabel label = new JLabel(foundType);
        frame.add(label);
        frame.setVisible(true);
        frame.pack();

    }

    public static void frameStyle(JFrame frame) throws IOException, InstantiationException, UnsupportedLookAndFeelException, ClassNotFoundException, IllegalAccessException {
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

    public void setUpListeners() {
        ActionListener listener;
        listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Object o = event.getSource();

                if (o == selectStatementButton) {
                    //actions
                } else if (o == runQueryButton) {
                    if (updateButton.isSelected()) {
                        //actions
                    } else if (insertButton.isSelected()) {
                        //actions
                    } else if (deleteButton.isSelected()) {
                        //actions
                    } else if (createTableButton.isSelected()) {
                        //actions
                    } else if (alterTableButton.isSelected()) {
                        //actions
                    } else if (dropTableButton.isSelected()) {
                        //actions
                    } else if (createDBButton.isSelected()) {
                        //actions
                    } else if (deleteDBButton.isSelected()) {
                        //actions
                    }
                    //have these else if statements for each button return a value, and then in this section that
                    //this comment is written in, have it return a string that is sent off to a
                    //QueryBehaivorDeterminer method in the QueryMaker class to parameterize the query
                } else if (o == changeDBButton) {
                    //make a swap database panel class and method
                }

            }

        };
        selectStatementButton.addActionListener(listener);
        runQueryButton.addActionListener(listener);
    }
}
