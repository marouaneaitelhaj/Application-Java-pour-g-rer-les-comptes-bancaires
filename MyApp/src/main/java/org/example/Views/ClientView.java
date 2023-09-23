package org.example.Views;

import org.example.Entity.Client;
import org.example.Helpers.MyFunction;
import org.example.Implementations.ClientImpl;

import java.time.LocalDate;
import java.util.Scanner;

public class ClientView {
    Scanner scanner = new Scanner(System.in);
    ClientImpl clientImpl = new ClientImpl();

    public ClientView() {
        System.out.println("1- Créer un client");
        switch (scanner.nextLine()) {
            case "1" -> {
                this.saveView();
            }
            default -> {
                System.out.println("Vous devez choisir un choix valide");
            }
        }
    }

    public void saveView() {
        Client client = new Client();
        System.out.println("Nom:");
        client.setNom(scanner.nextLine());
        System.out.println("Prenom:");
        client.setPrenom(scanner.nextLine());
        System.out.println("Telephone:");
        client.setTelephone(scanner.nextLine());
        System.out.println("Code:");
        client.setCode(scanner.nextLine());
        System.out.println("Adresse:");
        client.setAdresse(scanner.nextLine());
        LocalDate DateDeNaissance = MyFunction.getDate("Date De Naissance (yyyy-mm-dd) :");
        client.setDateDeNaissance(DateDeNaissance);
        if (clientImpl.save(client) == client) {
            System.out.println("Le client a été bein ajoutée");
            MainPage mainPage = new MainPage();
        }
    }
}
