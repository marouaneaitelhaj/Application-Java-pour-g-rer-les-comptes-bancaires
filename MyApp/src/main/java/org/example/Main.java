package org.example;

import org.example.helpers.DatabaseConnection;

public class Main {
    public static void main(String[] args) {

        DatabaseConnection.getInstance().getConnection();
    }
}