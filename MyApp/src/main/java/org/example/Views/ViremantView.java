package org.example.Views;

import org.example.Entity.Compte;
import org.example.Entity.Virement;
import org.example.Exceptions.MyException;
import org.example.Implementations.CompteImpl;
import org.example.Implementations.VirementImpl;
import org.example.Interfaces.CompteInter;
import org.example.Service.ViremantService;

import java.time.LocalDate;
import java.util.Scanner;

public class ViremantView {
    Scanner scanner = new Scanner(System.in);
    CompteImpl compteImpl = new CompteImpl();
    VirementImpl virementImpl = new VirementImpl(compteImpl);
    CompteInter compteInter = new CompteImpl();
    ViremantService viremantService = new ViremantService(virementImpl, compteInter);

    public void ViremantMenu() {
        System.out.println("1- Ajouter un transfert");
        System.out.println("2- Supprimer un transfert");
        System.out.println("2- Afficher la liste des transactions");
        switch (scanner.nextLine()) {
            case "1" -> {
                this.saveView();
            }
            case "2" -> {
                this.supprimerView();
            }
            case "3" -> {
                this.findAll();
            }
            default -> {
                System.out.println("Vous devez choisir un choix valide");
                new AgenceView();
            }
        }
    }

    private void findAll() {
        if (this.viremantService.findAll().isEmpty()){

        }else {
            this.viremantService.findAll().forEach(virement -> {
                System.out.println(virement.getCompteEmetteur().getClient().getNom() +"         " + virement.getCompteDestinataire().getClient().getNom() + "       " + virement.getMantant() +"$       " + virement.getDate());
            });
        }
        scanner.nextLine();
        this.ViremantMenu();
    }

    private void supprimerView() {
        System.out.println("Compte Destinataire");
        String code1 = scanner.nextLine();
        System.out.println("Compte Emetteur");
        String code2 = scanner.nextLine();
        System.out.println("solde");
        int solde = scanner.nextInt();
        Compte destinataire = new Compte(code1);
        Compte emetteur = new Compte(code2);
        Virement virement = new Virement(emetteur, destinataire, solde, LocalDate.now());
        try {
            if (this.viremantService.delete(virement)) {
                System.out.println("la transaction est supprimée");
            } else {
                System.out.println("la transaction n'est pas supprimée");
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        scanner.nextLine();
        this.ViremantMenu();
    }

    private void saveView() {
        System.out.println("Compte Destinataire");
        String code1 = scanner.nextLine();
        System.out.println("Compte Emetteur");
        String code2 = scanner.nextLine();
        System.out.println("solde");
        int solde = scanner.nextInt();
        Compte destinataire = new Compte(code1);
        Compte emetteur = new Compte(code2);
        Virement virement = new Virement(emetteur, destinataire, solde,LocalDate.now());
        try {
            if (this.viremantService.save(virement)) {
                System.out.println("la transaction est effectuée");
            } else {
                System.out.println("la transaction n'est pas  effectuée");
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        scanner.nextLine();
        this.ViremantMenu();
    }
}
