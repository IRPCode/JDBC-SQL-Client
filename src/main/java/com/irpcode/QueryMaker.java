package com.irpcode;

import java.io.IOException;

import javax.swing.UnsupportedLookAndFeelException;

//make sure user inputs are parameterized to prevent SQL injection
public class QueryMaker {
    static boolean DBFlag = false;

    public static void main(String[] args) throws IOException, InstantiationException, UnsupportedLookAndFeelException, ClassNotFoundException, IllegalAccessException, InterruptedException {
        LoginPanel.loginDBPanel(DBFlag, null, -1);
    }
}
