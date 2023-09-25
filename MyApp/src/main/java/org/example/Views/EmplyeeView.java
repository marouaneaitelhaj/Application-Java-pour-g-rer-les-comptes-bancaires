package org.example.Views;

import org.example.Entity.Employe;
import org.example.Helpers.MyFunction;
import org.example.Implementations.EmployeImpl;
import org.example.Main;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class EmplyeeView {
    Scanner scanner = new Scanner(System.in);
    EmployeImpl employeImpl = new EmployeImpl();

    public EmplyeeView() {
        System.out.println("1- Ajouter un employé");
        System.out.println("2- Chercher un employé par matricule");
        System.out.println("3- Supprimer un employé");
        System.out.println("4- Afficher la liste des employés");
        switch (scanner.nextLine()) {
            case "1" -> {
                this.saveView();
            }
            case "2" -> {
                this.findOneView();
            }
            case "3" -> {
                this.deleteView();
            }
            case "4" -> {
                this.showView();
            }
            default -> {
                System.out.println("Vous devez choisir un choix valide");
                new EmplyeeView();
            }
        }

    }

    public void saveView() {
        Employe employe = new Employe();
        System.out.println("Nom :");
        employe.setNom(scanner.nextLine());
        System.out.println("Prenom :");
        employe.setPrenom(scanner.nextLine());
        System.out.println("Email :");
        employe.setEmail(scanner.nextLine());
        LocalDate DateDeNaissance = MyFunction.getDate("Date De Naissance (yyyy-mm-dd) :");
        employe.setDateDeNaissance(DateDeNaissance);
        System.out.println("Telephone :");
        employe.setTelephone(scanner.nextLine());
        System.out.println("Matricule :");
        employe.setMatricule(scanner.nextLine());
        LocalDate DateDeRecrutement = MyFunction.getDate("Date De Recrutement (yyyy-mm-dd) :");
        employe.setDateDeRecrutement(DateDeRecrutement);
        EmployeImpl employe1 = new EmployeImpl();
        if (employe1.save(employe) == null) {
            System.out.println("L'employé n'a pas ajouté");
        } else {
            System.out.println("L'employé est ajouté");
        }
        new EmplyeeView();
    }


    public void findOneView() {
        System.out.println("Matricule : ");
        Employe employe = new Employe();
        employe.setMatricule(scanner.nextLine());
        Optional<Employe> employe2 = employeImpl.findOne(employe);
        if (employe2.isPresent()) {
            System.out.println(employe2.get().getNom() + "    " + employe2.get().getPrenom() + "    " + employe2.get().getTelephone() + "    " + employe2.get().getMatricule() + "    " + employe2.get().getEmail() + "    " + employe2.get().getDateDeRecrutement() + "    " + employe2.get().getDateDeNaissance());
        } else {
            System.out.println("Aucun employé trouvé");
        }
    }

    public void deleteView() {
        System.out.println("Matricule : ");
        Employe employe = new Employe();
        employe.setMatricule(scanner.nextLine());
        if (employeImpl.delete(employe) == 1) {
            System.out.println("La suppression a bien été effectuée");
            MainPage mainPage = new MainPage();
        } else {
            System.out.println("L'employé n'a pas supprimé");
            new EmplyeeView();
        }
    }

    public void showView() {
        List<Employe> employeImplAll = employeImpl.findAll();
        employeImplAll.stream().forEach(employe -> {
            System.out.println(employe.getNom() + "     "+employe.getPrenom() + "     "+employe.getTelephone() + "     "+employe.getMatricule() + "     "+employe.getEmail() + "     "+employe.getDateDeNaissance() + "     "+employe.getDateDeRecrutement());
        });
    }
}
