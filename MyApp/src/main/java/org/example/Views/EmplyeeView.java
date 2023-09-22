package org.example.Views;

import org.example.Entity.Employe;
import org.example.Implementations.EmployeImpl;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

public class EmplyeeView {
    Scanner scanner = new Scanner(System.in);

    public EmplyeeView() {
        System.out.println("1- Ajouter un employé");
        System.out.println("2- Chercher un employé par matricule");
        System.out.println("3- Mettre a jour un employé");
        switch (scanner.nextLine()) {
            case "1" -> {
                this.saveView();
            }
            case "2" -> {
                this.findOneView();
            }
            default -> {
                System.out.println("You must choose a valid choice");
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
        LocalDate DateDeNaissance = this.getDate("Date De Naissance (yyyy-mm-dd) :");
        employe.setDateDeNaissance(DateDeNaissance);
        System.out.println("Telephone :");
        employe.setTelephone(scanner.nextLine());
        System.out.println("Matricule :");
        employe.setMatricule(scanner.nextLine());
        LocalDate DateDeRecrutement = this.getDate("Date De Recrutement (yyyy-mm-dd) :");
        employe.setDateDeRecrutement(DateDeRecrutement);
        EmployeImpl employe1 = new EmployeImpl();
        employe1.save(employe);
    }

    public LocalDate getDate(String message) {
        Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        System.out.println(message);
        String inputDate = scanner.nextLine();
        if (datePattern.matcher(inputDate).matches()) {
            try {
                return LocalDate.parse(inputDate);
            } catch (Exception e) {
                System.out.println(e);
                return getDate(message);
            }
        } else {
            System.out.println("Format de date invalide. Veuillez entrer la date au format yyyy-mm-dd.");
            return getDate(message);
        }
    }

    public void findOneView() {
        System.out.println("Matricule : ");
        Employe employe = new Employe();
        employe.setMatricule(scanner.nextLine());
        EmployeImpl employe1 = new EmployeImpl();
        Employe employe2 = employe1.findOne(employe);
        System.out.println(employe2.getNom() + "    " + employe2.getPrenom()+ "    " + employe2.getTelephone()+ "    " + employe2.getMatricule()+ "    " + employe2.getEmail()+ "    " + employe2.getDateDeRecrutement()+ "    " + employe2.getDateDeNaissance());
    }
}
