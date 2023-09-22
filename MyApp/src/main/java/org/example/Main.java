package org.example;

import org.example.Entity.Employe;
import org.example.Helpers.DatabaseConnection;
import org.example.Views.EmplyeeView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1- Employee");
        switch (scanner.nextLine()) {
            case "1" -> {
                EmplyeeView emplyeeView = new EmplyeeView();
            }
            default -> {
                System.out.println("You must choose a valid choice");
            }
        }
    }
}