package org.example.Views;

import org.example.Entity.Agence;
import org.example.Entity.AgenceOfEmploye;
import org.example.Entity.Employe;
import org.example.Enums.AffectationStatus;
import org.example.Exceptions.MyException;
import org.example.Helpers.MyFunction;
import org.example.Implementations.AgenceImpl;
import org.example.Implementations.AgenceOfEmployeImpl;
import org.example.Implementations.EmployeImpl;
import org.example.Interfaces.AgenceOfEmployeInter;
import org.example.Service.AgenceOfEmployeService;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

public class AgenceOfEmployeView {
    Scanner scanner = new Scanner(System.in);
    EmployeImpl employeImpl = new EmployeImpl();
    AgenceImpl agenceImpl = new AgenceImpl();
    AgenceOfEmployeInter agenceOfEmployeImpl = new AgenceOfEmployeImpl(agenceImpl, employeImpl);
    AgenceOfEmployeService agenceOfEmployeService = new AgenceOfEmployeService(agenceOfEmployeImpl);

    public void AgenceOfEmployeViewMenu() {
        System.out.println("1- Affecter un employé à une agence");
        System.out.println("2- Muter un employé");
        System.out.println("3- Historique des affectations Employé/agences");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> {
                this.saveView();
            }
            case "2" -> {
                this.updateAffectation();
            }
            case "3" -> {
                this.findAllView();
            }
            default -> {
                System.out.println("Vous devez choisir un choix valide");
                this.AgenceOfEmployeViewMenu();
            }
        }
    }

    private void findAllView() {
        this.agenceOfEmployeService.findAll().forEach(agenceOfEmploye -> {
            System.out.println(agenceOfEmploye.getAgence().getNom() + "          " + agenceOfEmploye.getEmploye().getNom() + "              " + agenceOfEmploye.getDate());
        });
        scanner.nextLine();
        this.AgenceOfEmployeViewMenu();
    }

    private void updateAffectation() {
        System.out.println("employe: ");
        Employe employe = new Employe(scanner.nextLine());
        System.out.println("agence: ");
        Agence agence = new Agence(scanner.nextLine());
        LocalDate date = MyFunction.getDate("date : ");
        AgenceOfEmploye agenceOfEmploye = new AgenceOfEmploye(employe, agence, AffectationStatus.INACTIVE);
        agenceOfEmploye.setDate(date);
        try {
            Optional<AgenceOfEmploye> agenceOfEmploye1 = this.agenceOfEmployeService.update(agenceOfEmploye);
            if (agenceOfEmploye1.isPresent())
                System.out.println("l'employé est affecté à l'agence");
            if (agenceOfEmploye1.isEmpty())
                System.out.println("l'employé n'a pas été affecté à l'agence");
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        scanner.nextLine();
        this.AgenceOfEmployeViewMenu();
    }

    private void saveView() {
        System.out.println("employe: ");
        Employe employe = new Employe(scanner.nextLine());
        System.out.println("agence: ");
        Agence agence = new Agence(scanner.nextLine());
        AgenceOfEmploye agenceOfEmploye = new AgenceOfEmploye(employe, agence, AffectationStatus.ACTIVE);
        try {
            Optional<AgenceOfEmploye> agenceOfEmploye1 = this.agenceOfEmployeService.save(agenceOfEmploye);
            if (agenceOfEmploye1.isPresent())
                System.out.println("l'employé est affecté à l'agence");
            if (agenceOfEmploye1.isEmpty())
                System.out.println("l'employé n'a pas été affecté à l'agence");
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        scanner.nextLine();
        this.AgenceOfEmployeViewMenu();
    }
}
