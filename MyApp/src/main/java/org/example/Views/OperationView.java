package org.example.Views;

import org.example.Entity.Compte;
import org.example.Entity.Employe;
import org.example.Entity.Operation;
import org.example.Helpers.MyFunction;
import org.example.Implementations.CompteImpl;
import org.example.Implementations.OperationImpl;

import java.time.LocalDate;
import java.util.IllegalFormatCodePointException;
import java.util.Optional;
import java.util.Scanner;

public class OperationView {
    Scanner scanner = new Scanner(System.in);
    OperationImpl operationImpl = new OperationImpl();
    CompteImpl CompteImpl = new CompteImpl();

    public OperationView() {
        System.out.println("1- Ajouter une opération");
        System.out.println("2- Supprimer une opération");
        System.out.println("3- Chercher une opération par numéro");
        switch (scanner.nextLine()) {
            case "1" -> {
                this.saveView();
            }
            case "2" -> {
                this.deleteView();
            }
            case "3" -> {
                this.findOneView();
            }
            default -> {
                System.out.println("Vous devez choisir un choix valide");
                new OperationView();
            }
        }
    }

    public void saveView() {
        System.out.println("1-versement: ");
        System.out.println("2-retrait: ");
        switch (scanner.nextLine()) {
            case "1" -> {
                this.versementView();
            }
            case "2" -> {
                this.retraitView();
            }
            default -> {
                this.saveView();
            }
        }
    }

    private void retraitView() {
        Operation operation = new Operation();
        System.out.println("montant");
        operation.setMontant(Integer.parseInt(scanner.nextLine()));
        System.out.println("employe");
        Employe employe = new Employe();
        employe.setMatricule(scanner.nextLine());
        operation.setEmploye(employe);
        System.out.println("compte");
        Compte compte = new Compte();
        compte.setNumero(scanner.nextLine());
        operation.setCompte(compte);
        Optional<Compte> optionalCompte = CompteImpl.findOne(compte);
        if (optionalCompte.isEmpty()) {
            System.out.println("aucun compte trouvé");
        } else {
            if (optionalCompte.get().getSolde() < operation.getMontant()) {
                System.out.println("tu n'as pas assez d'argent");
            } else {
                compte.setSolde(optionalCompte.get().getSolde() - operation.getMontant());
                operation.setCompte(compte);
                operation.setDateDeCreation(LocalDate.now());
                if (operationImpl.save(operation).isEmpty()) {
                    System.out.println("l'opération n'a pas été ajoutée");
                } else {
                    System.out.println("opération ajoutée");
                }
                new OperationView();
            }
        }
    }

    private void versementView() {
        Operation operation = new Operation();
        System.out.println("montant");
        operation.setMontant(Integer.parseInt(scanner.nextLine()));
        System.out.println("employe");
        Employe employe = new Employe();
        employe.setMatricule(scanner.nextLine());
        operation.setEmploye(employe);
        System.out.println("compte");
        Compte compte = new Compte();
        compte.setNumero(scanner.nextLine());
        operation.setCompte(compte);
        Optional<Compte> optionalCompte = CompteImpl.findOne(compte);
        if (optionalCompte.isEmpty()) {
            System.out.println("aucun compte trouvé");
        } else {
            compte.setSolde(optionalCompte.get().getSolde() + operation.getMontant());
            operation.setCompte(compte);
            operation.setDateDeCreation(LocalDate.now());
            Optional<Operation> operation1 = operationImpl.save(operation);
            if (operation1.isEmpty()) {
                System.out.println("l'opération n'a pas été ajoutée");
            } else {
                System.out.println("opération ajoutée");
            }
            new OperationView();
        }
    }

    public void deleteView() {
        System.out.println("Numero: ");
        Operation operation = new Operation();
        operation.setNumero(Integer.parseInt(scanner.nextLine()));
        if (operationImpl.delete(operation) == 0) {
            System.out.println("l'opération n'a pas été supprimée");
        } else {
            System.out.println("opération supprimée");
        }
        MyFunction.appuyezPourQuitter();
        new OperationView();
    }

    public void findOneView() {
        System.out.println("Numero: ");
        Operation operation = new Operation();
        operation.setNumero(Integer.parseInt(scanner.nextLine()));
        Optional<Operation> operationOperation = operationImpl.findOne(operation);
        if (operationOperation.isEmpty()) {
            System.out.println("Aucun opération trouvé");
        } else {
            Operation operation1 = operationOperation.get();
            System.out.println(operation1.getMontant() + "       " + operation1.getEmploye().getMatricule() + "       " + operation1.getCompte().getNumero() + "       " + operation1.getNumero() + "       " + operation1.getDateDeCreation());
        }
        MyFunction.appuyezPourQuitter();
        new OperationView();
    }
}
