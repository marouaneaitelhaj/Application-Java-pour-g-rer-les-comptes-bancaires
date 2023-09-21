package org.example;

import org.example.Helpers.DatabaseConnection;

public class Main {
    public static void main(String[] args) {

        DatabaseConnection.getInstance().getConnection();
    }
}