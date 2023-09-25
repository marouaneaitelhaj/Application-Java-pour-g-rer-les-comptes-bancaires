package org.example.Views;

import java.util.Scanner;

public class MainPage {
    public MainPage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1- Employee");
        System.out.println("2- Client");
        System.out.println("3- Compte");
        switch (scanner.nextLine()) {
            case "1" -> {
                EmplyeeView emplyeeView = new EmplyeeView();
            }
            case "2" ->{
                ClientView clientView = new ClientView();
            }
            case "3" ->{
                CompteView compteView = new CompteView();
            }
            default -> {
                System.out.println("Vous devez choisir un choix valide");
                new MainPage();
            }
        }
    }
}
