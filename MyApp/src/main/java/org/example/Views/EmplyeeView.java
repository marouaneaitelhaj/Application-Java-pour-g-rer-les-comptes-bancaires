package org.example.Views;

import org.example.Entity.Employe;
import org.example.Helpers.MyFunction;
import org.example.Implementations.EmployeImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
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
        System.out.println("5- Chercher");
        System.out.println("6- Mettre a jour un employe");
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
            case "5" -> {
                this.findByAtrView();
            }
            case "6" -> {
                this.updateView();
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
        MyFunction.appuyezPourQuitter();
        new EmplyeeView();
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

    public Optional<List<Employe>> showView() {
        Optional<List<Employe>> employeImplAll = employeImpl.findAll();
        if (employeImplAll.isEmpty()) {
            System.out.println("Aucun employé trouvé");
        } else {
            employeImplAll.get().forEach(employe -> {
                System.out.println(employe.getNom() + "     " + employe.getPrenom() + "     " + employe.getTelephone() + "     " + employe.getMatricule() + "     " + employe.getEmail() + "     " + employe.getDateDeNaissance().toString() + "     " + employe.getDateDeRecrutement().toString());
            });
        }
        return employeImplAll;
    }

    public void findByAtrView() {
        System.out.println("Ecris quelque chose que tu cherches");
        Optional<List<Employe>> optionalEmployeList = employeImpl.findByAtr(scanner.nextLine());
        if (optionalEmployeList.isEmpty()) {
            System.out.println("Aucun employé trouvé");
        } else {
            optionalEmployeList.get().forEach(employe -> {
                System.out.println(employe.getNom() + "     " + employe.getPrenom() + "     " + employe.getTelephone() + "     " + employe.getMatricule() + "     " + employe.getEmail() + "     " + employe.getDateDeNaissance() + "     " + employe.getDateDeRecrutement());
            });
        }
        MyFunction.appuyezPourQuitter();
        new EmplyeeView();
    }
    public void updateView(){
        Optional<List<Employe>> employeImplAll = this.showView();
        System.out.println("matricule:");
        String matricule = scanner.nextLine();
        employeImplAll.get().stream().forEach(employe11 -> {
            if (Objects.equals(employe11.getMatricule(), matricule)){
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
                employe.setMatricule(matricule);
                LocalDate DateDeRecrutement = MyFunction.getDate("Date De Recrutement (yyyy-mm-dd) :");
                employe.setDateDeRecrutement(DateDeRecrutement);
                EmployeImpl employe1 = new EmployeImpl();
                if (employe1.update(employe).isEmpty()) {
                    System.out.println("L'employé n'a pas Mettre a jour");
                } else {
                    System.out.println("L'employé est Mettre a jour");
                }
                new EmplyeeView();
            }
        });
    }
}
