package com.irpcode;

import java.awt.Color;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

public class DBActionOptions {
    private static JFrame optionsFrame;
    public static void optionPanel(int selectedAction) {
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

    public static void deleteDatabase() {
        try {
            baseOptionsPanel();
        } catch (Exception e) {
            optionsFrame.dispose();
            System.out.println("Fatal Error");
        }

    }

    public static void baseOptionsPanel() throws IOException, InstantiationException, UnsupportedLookAndFeelException, ClassNotFoundException, IllegalAccessException{
        
        //basic setup
        optionsFrame = new JFrame();
        UIMaker.frameStyle(optionsFrame);
        optionsFrame.setSize(500, 300);
        optionsFrame.setVisible(true);
        optionsFrame.setLocationRelativeTo(null);

        //set border color
        
        Border border = BorderFactory.createLineBorder(Color.decode("#5d5f60"), 3); //4f5153
        optionsFrame.getRootPane().setBorder(border);
        
    }   
}
