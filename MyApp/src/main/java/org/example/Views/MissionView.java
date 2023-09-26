package org.example.Views;

import org.example.Entity.Mission;
import org.example.Implementations.MissionImpl;

import java.util.Optional;
import java.util.Scanner;

public class MissionView {
    MissionImpl missionImpl = new MissionImpl();
    Scanner scanner = new Scanner(System.in);

    public MissionView() {
        System.out.println("1- Ajouter une mission");
        System.out.println("2- Supprimer une mission");
        System.out.println("3- Ajouter une mission");
        switch (scanner.nextLine()) {
            case "1" -> {
                this.saveView();
            }
            case "2" -> {
                this.deleteView();
            }
            case "3" -> {
                this.showView();
            }
            default -> {
                System.out.println("Vous devez choisir un choix valide");
                new MissionView();
            }
        }
    }

    private void deleteView() {
        System.out.println("code :");
        Mission mission = new Mission();
        mission.setCode(scanner.nextInt());
        if (missionImpl.delete(mission) == 0) {
            System.out.println("la mission n'a pas été supprimée");
        }else {
            System.out.println("mission supprimée");
        }
        new MissionView();
    }

    private void showView() {

    }

    private void saveView() {
        Mission mission = new Mission();
        System.out.println("nom: ");
        mission.setNom(scanner.nextLine());
        System.out.println("description: ");
        mission.setDescription(scanner.nextLine());
        if (missionImpl.save(mission).isEmpty()) {
            System.out.println("la mission n'a pas été ajoutée");
        } else {
            System.out.println("mission ajoutée");
        }
        new MissionView();
    }
}
