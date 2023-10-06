package org.example.Views;

import org.example.Entity.Agence;
import org.example.Entity.AgenceOfEmploye;
import org.example.Entity.Employe;
import org.example.Exceptions.AgenceException;
import org.example.Implementations.AgenceOfEmployeImpl;
import org.example.Interfaces.AgenceOfEmployeInter;
import org.example.Service.AgenceOfEmployeService;
import org.example.Service.AgenceService;

import java.util.Optional;
import java.util.Scanner;

public class AgenceOfEmployeView {
    Scanner scanner = new Scanner(System.in);
    AgenceOfEmployeInter agenceOfEmployeImpl = new AgenceOfEmployeImpl();
    AgenceOfEmployeService agenceOfEmployeService = new AgenceOfEmployeService(agenceOfEmployeImpl);

    public void AgenceOfEmployeViewMenu() {
        System.out.println("1- Affecter un employé à une agence");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> {
                this.saveView();
            }
            default -> {
                System.out.println("Vous devez choisir un choix valide");
                this.AgenceOfEmployeViewMenu();
            }
        }
    }

    private void saveView() {
        System.out.println("employe: ");
        Employe employe = new Employe(scanner.nextLine());
        System.out.println("agence: ");
        Agence agence = new Agence(scanner.nextLine());
        AgenceOfEmploye agenceOfEmploye = new AgenceOfEmploye(employe, agence);
        try {
            Optional<AgenceOfEmploye> agenceOfEmploye1 = this.agenceOfEmployeService.save(agenceOfEmploye);
            if (agenceOfEmploye1.isPresent())
                System.out.println("l'employé est affecté à l'agence");
            if (agenceOfEmploye1.isEmpty())
                System.out.println("l'employé n'a pas été affecté à l'agence");
        } catch (AgenceException e) {
            System.out.println(e.getMessage());
        }
        scanner.nextLine();
        this.AgenceOfEmployeViewMenu();
    }
}
