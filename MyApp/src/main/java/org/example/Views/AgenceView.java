package org.example.Views;

import org.example.Entity.Agence;
import org.example.Entity.AgenceOfEmploye;
import org.example.Entity.Employe;
import org.example.Exceptions.AgenceException;
import org.example.Implementations.AgenceImpl;
import org.example.Interfaces.AgenceInter;
import org.example.Service.AgenceService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AgenceView {

    Scanner scanner = new Scanner(System.in);
    AgenceInter agenceImpl = new AgenceImpl();
    AgenceService agenceService = new AgenceService(agenceImpl);

    protected void AgenceMenuView() {
        System.out.println("1- Ajouter une agence");
        System.out.println("2- Chercher une agence par code");
        System.out.println("3- Supprimer une agence");
        System.out.println("4- Chercher une agence par adresse");
        System.out.println("5- Mettre à jour une agence");
        System.out.println("6- Chercher une agence par employé");
        System.out.println("7- Afficher la liste des contact(nom/tel)");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> {
                this.saveView();
            }
            case "2" -> {
                this.findByCodeView();
            }
            case "3" -> {
                this.deleteView();
            }
            case "4" -> {
                this.findByAdresseView();
            }
            case "5" -> {
                this.updateView();
            }
            case "6" -> {
                this.findByEmployeView();
            }
            case "7" -> {

            }
            default -> {
                System.out.println("Vous devez choisir un choix valide");
                new AgenceView();
            }
        }
    }

    protected void deleteView() {
        System.out.println("Code : ");
        String code = scanner.nextLine();
        Agence agence = new Agence(code);
        try {
            int deleted = agenceService.delete(agence);
            if (deleted == 1) {
                System.out.println("Agence est bien supprimé");
            } else {
                System.out.println("Agence n'est pas supprimé");
            }
        } catch (AgenceException e) {
            System.out.println(e.getMessage());
        }
        scanner.nextLine();
        this.AgenceMenuView();
    }

    protected void findByAdresseView() {
        System.out.println("Adresse :");
        String adresse = scanner.nextLine();
        Agence agence = new Agence();
        agence.setAdresse(adresse);
        try {
            Optional<Agence> agenceOptional = agenceService.findByAdresse(agence);
            agenceOptional.ifPresent(value -> System.out.println(value.getNom() + "        " + value.getAdresse() + "        " + value.getNumeroTelephone()));
        } catch (AgenceException e) {
            System.out.println(e.getMessage());
        }
        scanner.nextLine();
        this.AgenceMenuView();
    }

    protected void updateView() {
        System.out.println("Code :");
        String code = scanner.nextLine();
        System.out.println("nom :");
        String nom = scanner.nextLine();
        System.out.println("adresse :");
        String adresse = scanner.nextLine();
        System.out.println("numero Telephone :");
        String numeroTelephone = scanner.nextLine();
        Agence agence = new Agence(code, nom, adresse, numeroTelephone);
        try {
            if (agenceService.update(agence).isPresent()) {
                System.out.println("l'agence est bien à jour");
            } else {
                System.out.println("l'agence n'a pas été mise à jour");
            }
        } catch (AgenceException e) {
            System.out.println(e.getMessage());
        }
        scanner.nextLine();
        this.AgenceMenuView();
    }

    protected void findByEmployeView() {
        System.out.println("Employe : ");
        String employe = scanner.nextLine();
        Employe employe1 = new Employe(employe);
        List<Agence> agenceList = agenceService.findByEmploye(employe1);
        agenceList.forEach(agence1 -> {
            System.out.println(agence1.getNom() + "         " + agence1.getAdresse());
        });
        scanner.nextLine();
        this.AgenceMenuView();
    }

    protected void findByCodeView() {
        System.out.println("Code : ");
        String code = scanner.nextLine();
        Agence agence = new Agence(code);
        try {
            Optional<Agence> agenceOptional = agenceService.findByCode(agence);
            agenceOptional.ifPresent(agence1 -> {
                System.out.println(agence1.getNom() + "      " + agence1.getAdresse());
            });
        } catch (AgenceException e) {
            System.out.println(e.getMessage());
        }
        scanner.nextLine();
        this.AgenceMenuView();
    }

    protected void saveView() {
        System.out.println("Code :");
        String code = scanner.nextLine();
        System.out.println("nom :");
        String nom = scanner.nextLine();
        System.out.println("adresse :");
        String adresse = scanner.nextLine();
        System.out.println("numero Telephone :");
        String numeroTelephone = scanner.nextLine();
        Agence agence = new Agence(code, nom, adresse, numeroTelephone);
        try {
            if (agenceService.save(agence).isPresent()) {
                System.out.println("L'agence a été bein ajoutée");
            } else {
                System.out.println("L'agence n'a pas ajoutée");
            }
        } catch (AgenceException e) {
            System.out.println(e.getMessage());
        }
        this.AgenceMenuView();
    }
}
