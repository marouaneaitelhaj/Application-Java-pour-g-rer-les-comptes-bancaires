package org.example.Views;

import org.example.Entity.Credit;
import org.example.Implementations.CreditImpl;
import org.example.Interfaces.CreditInter;
import org.example.Service.CreditService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CreditView {
    Scanner scanner = new Scanner(System.in);
    CreditInter creditInter = new CreditImpl();
    CreditService creditService = new CreditService(creditInter);

    public void creditViewmenu() {
        System.out.println("1- Chercher une demande par code");
        System.out.println("2- Affiche la liste des demandes");
        System.out.println("3- Afficher liste par date");
        System.out.println("4- Afficher liste pas statut");
        System.out.println("5- Changer le statut");
        switch (scanner.nextLine()) {
            case "1" -> {
                this.findOne();
            }
            case "2" -> {
                this.findAll();
            }
            case "3" -> {
                this.findAllByDate();
            }
            case "4" -> {
                this.findAllBystatus();
            }
            case "5" -> {
                this.update();
            }
            default -> {
                System.out.println("Vous devez choisir un choix valide");
                this.creditViewmenu();
            }
        }
    }


    public void findAllBystatus() {
        List<Credit> creditList = creditService.findAllBystatus();
        if (creditList.isEmpty()) {
            System.out.println("Nothing to show");
        } else {
            creditList.forEach(credit -> {
                System.out.println(credit.getNumero() + "            " + credit.getRemarques() + "           " + credit.getDate() + "             " + credit.getMontant() + "$");
            });
        }
    }


    public void findAllByDate() {
        List<Credit> creditList = creditService.findAllByDate();
        if (creditList.isEmpty()) {
            System.out.println("Nothing to show");
        } else {
            creditList.forEach(credit -> {
                System.out.println(credit.getNumero() + "            " + credit.getRemarques() + "           " + credit.getDate() + "             " + credit.getMontant() + "$");
            });
        }
    }


    public void update() {
        System.out.println("Numero : ");
        Credit credit = new Credit(scanner.nextLine());
        Optional<Credit> creditList = creditService.update(credit);
        creditList.ifPresent(credit1 -> {
            System.out.println("Updated !!");
        });
        if (creditList.isEmpty()) {
            System.out.println("Not updated");
        }
    }





    public void findOne() {
        System.out.println("Numero : ");
        Credit credit = new Credit(scanner.nextLine());
        Optional<Credit> creditList = creditService.findOne(credit);
        creditList.ifPresent(credit1 -> {
            System.out.println(credit.getNumero() + "            " + credit.getRemarques() + "           " + credit.getDate() + "             " + credit.getMontant() + "$");
        });
        if (creditList.isEmpty()) {
            System.out.println("Nothing to show");
        }
    }


    public void findAll() {
        List<Credit> creditList = creditService.findAll();
        if (creditList.isEmpty()) {
            System.out.println("Nothing to show");
        } else {
            creditList.forEach(credit -> {
                System.out.println(credit.getNumero() + "            " + credit.getRemarques() + "           " + credit.getDate() + "             " + credit.getMontant() + "$");
            });
        }
    }
}
