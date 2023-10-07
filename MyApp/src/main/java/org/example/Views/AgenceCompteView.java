package org.example.Views;

import org.example.Entity.Agence;
import org.example.Entity.Compte;
import org.example.Implementations.CompteAgenceImpl;
import org.example.Interfaces.CompteAgenceIntre;
import org.example.Service.CompteAgenceService;

import java.util.Scanner;

public class AgenceCompteView {
    Scanner scanner = new Scanner(System.in);


    CompteAgenceIntre compteAgenceIntre = new CompteAgenceImpl();
    CompteAgenceService compteAgenceService = new CompteAgenceService(compteAgenceIntre);

    public void AgenceCompteMenu() {
        System.out.println("1- Affecter un compte à une agence");
        switch (scanner.nextLine()) {
            case "1" -> {
                this.affectCompteAgence();
            }
            default -> {
                System.out.println("Vous devez choisir un choix valide");
                new CompteView();
            }
        }
    }

    private void affectCompteAgence() {
        System.out.println("Compte :");
        String numero = scanner.nextLine();
        Compte compte = new Compte(numero);
        System.out.println("Agence :");
        String code = scanner.nextLine();
        Agence agence = new Agence(code);
        compte.setAgence(agence);
        if (this.compteAgenceService.affectCompteAgence(compte))
            System.out.println("compte a été effectué a une agence");
        else {
            System.out.println("compte n'est pas  effectué a une agence");
        }
        scanner.nextLine();
        this.AgenceCompteMenu();
    }
}
