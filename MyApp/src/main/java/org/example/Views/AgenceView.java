package org.example.Views;

import org.example.Entity.Agence;
import org.example.Entity.Employe;
import org.example.Implementations.AgenceImpl;
import org.example.Service.AgenceService;
import java.util.Scanner;

public class AgenceView {

    Scanner scanner = new Scanner(System.in);
    AgenceImpl agenceImpl = new AgenceImpl();
    AgenceService agenceService = new AgenceService(agenceImpl);

    public void AgenceMenuView() {
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

    private void deleteView() {
    }

    private void findByAdresseView() {
    }

    private void updateView() {
    }

    private void findByEmployeView() {
    }

    public void findByCodeView() {
    }

    public void saveView() {
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
        Agence agence = new Agence(code,nom,adresse,numeroTelephone,employe1);
        if (agenceService.save(agence)){
            System.out.println("L'agence a été bein ajoutée");
        }else {
            System.out.println("L'agence n'a pas ajoutée");
        }
        this.AgenceMenuView();
    }
}
