package org.example.Views;

import org.example.Implementations.AgenceImpl;
import org.example.Service.AgenceService;

import java.util.Scanner;

public class MainPage {
    public MainPage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1- Employee");
        System.out.println("2- Client");
        System.out.println("3- Compte");
        System.out.println("4- Operation");
        System.out.println("5- Missions");
        System.out.println("6- Affectation");
        System.out.println("7- Agence");
        System.out.println("8- Agence et employe");
        System.out.println("9- Agence et compte");
        System.out.println("10- Virement");
        switch (scanner.nextLine()) {
            case "1" -> {
                EmplyeeView emplyeeView = new EmplyeeView();
            }
            case "2" ->{
                new ClientView();
            }
            case "3" ->{
                CompteView compteView = new CompteView();
            }
            case "4" ->{
                OperationView operationView = new OperationView();
            }
            case "5" ->{
                MissionView missionView = new MissionView();
            }
            case "6" ->{
                MissionOfEmployesView MissionOfEmployesView = new MissionOfEmployesView();
            }
            case "7" ->{
                AgenceView agenceView= new AgenceView();
                agenceView.AgenceMenuView();
            }
            case "8" ->{
                AgenceOfEmployeView agenceOfEmployeView= new AgenceOfEmployeView();
                agenceOfEmployeView.AgenceOfEmployeViewMenu();
            }
            case "9" ->{
                AgenceCompteView agenceCompteView= new AgenceCompteView();
                agenceCompteView.AgenceCompteMenu();
            }
            case "10" ->{
                ViremantView viremantView= new ViremantView();
                viremantView.ViremantMenu();
            }
            default -> {
                System.out.println("Vous devez choisir un choix valide");
                new MainPage();
            }
        }
    }
}
