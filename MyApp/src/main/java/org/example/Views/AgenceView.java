package org.example.Views;

import org.example.Entity.Agence;
import org.example.Entity.Employe;
import org.example.Implementations.AgenceImpl;
import org.example.Interfaces.AgenceInter;
import org.example.Service.AgenceService;

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
    }

    protected void findByAdresseView() {
    }

    protected void updateView() {
    }

    protected void findByEmployeView() {
    }

    protected void findByCodeView() {
        System.out.println("Code : ");
        String code = scanner.nextLine();
        Agence agence = new Agence(code);
        Optional<Agence> agenceOptional = agenceService.findByCode(agence);
        agenceOptional.ifPresent(agence1 -> {
            System.out.println(agence1.getNom() + "      " + agence1.getAdresse());
        });
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
        System.out.println("employe :");
        String employe = scanner.nextLine();
        Employe employe1 = new Employe(employe);
        System.out.println("numero Telephone :");
        String numeroTelephone = scanner.nextLine();
        Agence agence = new Agence(code, nom, adresse, numeroTelephone, employe1);
        if (agenceService.save(agence).isPresent()) {
            System.out.println("L'agence a été bein ajoutée");
        } else {
            System.out.println("L'agence n'a pas ajoutée");
        }
        this.AgenceMenuView();
    }
}
