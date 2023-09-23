package org.example.Views;

import org.example.Entity.Employe;
import org.example.Helpers.MyFunction;
import org.example.Implementations.EmployeImpl;

import java.time.LocalDate;
import java.util.Scanner;

public class EmplyeeView {
    Scanner scanner = new Scanner(System.in);
    EmployeImpl employeImpl = new EmployeImpl();

    public EmplyeeView() {
        System.out.println("1- Ajouter un employé");
        System.out.println("2- Supprimer un employé");
        System.out.println("3- Mettre a jour un employé");
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
            default -> {
                System.out.println("Vous devez choisir un choix valide");
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
        employe1.save(employe);
    }


    public void findOneView() {
        System.out.println("Matricule : ");
        Employe employe = new Employe();
        employe.setMatricule(scanner.nextLine());
        Employe employe2 = employeImpl.findOne(employe);
        System.out.println(employe2.getNom() + "    " + employe2.getPrenom() + "    " + employe2.getTelephone() + "    " + employe2.getMatricule() + "    " + employe2.getEmail() + "    " + employe2.getDateDeRecrutement() + "    " + employe2.getDateDeNaissance());
    }

    public void deleteView() {
        System.out.println("Matricule : ");
        Employe employe = new Employe();
        employe.setMatricule(scanner.nextLine());
        if (employeImpl.delete(employe) == 1) {
            System.out.println("La suppression a bien été effectuée");
            MainPage mainPage = new MainPage();
        }
    }
}
