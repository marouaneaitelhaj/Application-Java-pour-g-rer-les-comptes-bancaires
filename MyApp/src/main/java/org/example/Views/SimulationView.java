package org.example.Views;

import org.example.Entity.Agence;
import org.example.Entity.Client;
import org.example.Entity.Credit;
import org.example.Enums.CreditEtat;
import org.example.Implementations.CreditImpl;
import org.example.Service.SimulationService;

import java.time.LocalDate;
import java.util.Scanner;

public class SimulationView {
    Scanner scanner = new Scanner(System.in);
    CreditImpl creditImpl = new CreditImpl();
    SimulationService simulationService = new SimulationService(creditImpl);

    public void SimulationMenu() {
        System.out.println("Créer une simulation");
        switch (scanner.nextLine()) {
            case "1" -> {
                this.simulation();
            }
            default -> {
                System.out.println("Vous devez choisir un choix valide");
                this.SimulationMenu();
            }
        }
    }

    public void simulation() {
        System.out.print("montant de credit:");
        int montant = scanner.nextInt();
        System.out.print("nombre de mensualite:");
        int mensualite = scanner.nextInt();
        double result = simulationService.createSimulation(montant, mensualite);
        System.out.println("Result " + (int) result);
        scanner.nextLine();
        System.out.println("1- Valider la simulation pour le credit? :");
        System.out.println("2- non :");
        switch (scanner.nextLine()) {
            case "1" -> {
                this.validerUnSimulation(mensualite, montant);
            }
            case "2" -> {
                this.SimulationMenu();
            }
            default -> {
                System.out.println("Vous devez choisir un choix valide");
                this.SimulationMenu();
            }
        }

    }

    private void validerUnSimulation(int mensualite, int montant) {
        System.out.println("numero : ");
        String numero = scanner.nextLine();
        System.out.println("Client : ");
        Client client = new Client(scanner.nextLine());
        System.out.println("Agence : ");
        Agence agence = new Agence(scanner.nextLine());
        System.out.println("remarques : ");
        String remarques = scanner.nextLine();
        Credit credit = new Credit(numero, client, agence, LocalDate.now(), montant, mensualite, remarques, CreditEtat.EN_ATTENTE);
        if (simulationService.save(credit).isPresent()) {
            System.out.println("Credit is added");
        } else {
            System.out.println("Credit didn't added");
        }
        scanner.nextLine();
        this.SimulationMenu();
    }
}
