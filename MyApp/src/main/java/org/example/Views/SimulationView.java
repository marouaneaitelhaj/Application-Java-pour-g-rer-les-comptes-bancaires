package org.example.Views;

import org.example.Service.SimulationService;

import java.util.Scanner;

public class SimulationView {
    Scanner scanner = new Scanner(System.in);
    SimulationService simulationService = new SimulationService();

    public void SimulationMenu() {
        System.out.println("Créer une simulation");
        switch (scanner.nextLine()) {
            case "1" -> {
                this.simulation();
            }
            default -> {
                System.out.println("Vous devez choisir un choix valide");
                new OperationView();
            }
        }
    }

    public void simulation() {
        //System.out.println("mensualité");
        System.out.println("capital emprunté");
        int capitalEmprunte = scanner.nextInt();
        System.out.println("taux annuel proportionnel");
        int tauxAnnuelProportionnel = scanner.nextInt();
        System.out.println("nombre de mensualités");
        int nombreDeMensualites = scanner.nextInt();
        System.out.println(this.simulationService.createSimulation(capitalEmprunte,tauxAnnuelProportionnel,nombreDeMensualites));
    }
}
