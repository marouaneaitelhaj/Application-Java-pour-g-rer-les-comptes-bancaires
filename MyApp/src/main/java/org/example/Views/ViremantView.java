package org.example.Views;

import org.example.Entity.Compte;
import org.example.Entity.Virement;
import org.example.Exceptions.MyException;
import org.example.Implementations.CompteImpl;
import org.example.Implementations.VirementImpl;
import org.example.Interfaces.CompteInter;
import org.example.Service.ViremantService;

import java.util.Scanner;

public class ViremantView {
    Scanner scanner = new Scanner(System.in);

    VirementImpl virementImpl = new VirementImpl();
    CompteInter compteInter = new CompteImpl();
    ViremantService viremantService = new ViremantService(virementImpl,compteInter);

    public void ViremantMenu() {
        System.out.println("1- Ajouter un transfert");
        switch (scanner.nextLine()) {
            case "1" -> {
                this.saveView();
            }
            default -> {
                System.out.println("Vous devez choisir un choix valide");
                new AgenceView();
            }
        }
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
        Virement virement = new Virement(emetteur,destinataire,solde);
        try {
            if (this.viremantService.save(virement)){
                System.out.println("la transaction est effectuée");
            }else {
                System.out.println("la transaction n'est pas  effectuée");
            }
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        scanner.nextLine();
        this.ViremantMenu();
    }
}
