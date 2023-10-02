package org.example.Views;

import org.example.Entity.Client;
import org.example.Helpers.MyFunction;
import org.example.Implementations.ClientImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class ClientView {
    Scanner scanner = new Scanner(System.in);
    ClientImpl clientImpl = new ClientImpl();

    public ClientView() {
        System.out.println("1- Créer un client");
        System.out.println("2- Chercher un client par code");
        System.out.println("3- Supprimer un client");
        System.out.println("4- Afficher la liste des clients");
        System.out.println("5- La recherche");
        System.out.println("6- Mettre a jour un client");
        switch (scanner.nextLine()) {
            case "1" -> {
                this.saveView();
            }
            case "2" -> {
                this.findOneView();
            }
            case "3" -> {
                this.deleteView();
            }
            case "4" -> {
                this.showAllView();
            }
            case "5" -> {
                this.findByAtrView();
            }
            case "6" -> {
                this.updateView();
            }
            default -> {
                System.out.println("Vous devez choisir un choix valide");
                new ClientView();
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
        if (clientImpl.save(client).isPresent()) {
            System.out.println("Le client a été bein ajoutée");
        } else {
            System.out.println("Le client n'a pas ajouté");
        }
        new ClientView();
    }

    public void findOneView() {
        Client client = new Client();
        System.out.println("Code:");
        client.setCode(scanner.nextLine());
        ClientImpl clientImpl = new ClientImpl();
        Optional<Client> client1 = clientImpl.findOne(client);
        if (client1.isPresent()) {
            System.out.println(client.getNom() + "    " + client.getPrenom() + "    " + client.getTelephone() + "    " + client.getCode() + "    " + client.getAdresse() + "    " + client.getDateDeNaissance());
        } else {
            System.out.println("Aucun client trouvé");
        }
        MyFunction.appuyezPourQuitter();
        new ClientView();
    }

    public void deleteView() {
        Client client = new Client();
        System.out.println("Code:");
        client.setCode(scanner.nextLine());
        ClientImpl clientImpl = new ClientImpl();
        if (clientImpl.delete(client) == 1) {
            System.out.println("La suppression a bien été effectuée");
            new MainPage();
        }
    }

    public Optional<List<Client>> showAllView() {
        Optional<List<Client>> clientList = clientImpl.findAll();
        if (clientList.isEmpty()) {
            System.out.println("Aucun client trouvé");
        } else {
            clientList.get().forEach(client -> {
                System.out.println(client.getNom() + "    " + client.getPrenom() + "    " + client.getTelephone() + "    " + client.getCode() + "    " + client.getAdresse() + "    " + client.getDateDeNaissance());
            });
        }
        return clientList;
    }

    public void findByAtrView() {
        System.out.println("Ecris quelque chose que tu cherches");
        Optional<List<Client>> optionalClientList = clientImpl.findByAtr(scanner.nextLine());
        if (optionalClientList.isEmpty()) {
            System.out.println("Aucun Client trouvé");
        } else {
            optionalClientList.get().forEach(client -> {
                System.out.println(client.getNom() + "    " + client.getPrenom() + "    " + client.getTelephone() + "    " + client.getCode() + "    " + client.getAdresse() + "    " + client.getDateDeNaissance());
            });
        }
        MyFunction.appuyezPourQuitter();
        new ClientView();
    }

    public void updateView() {
        Optional<List<Client>> clientList = this.showAllView();
        System.out.println("code : ");
        String code = scanner.nextLine();
        clientList.get().stream().forEach(client11 -> {
            if (Objects.equals(client11.getCode(), code)) {
                Client client = new Client();
                System.out.println("Nom:");
                client.setNom(scanner.nextLine());
                System.out.println("Prenom:");
                client.setPrenom(scanner.nextLine());
                System.out.println("Telephone:");
                client.setTelephone(scanner.nextLine());
                client.setCode(code);
                System.out.println("Adresse:");
                client.setAdresse(scanner.nextLine());
                LocalDate DateDeNaissance = MyFunction.getDate("Date De Naissance (yyyy-mm-dd) :");
                client.setDateDeNaissance(DateDeNaissance);
                if (clientImpl.update(client).isPresent()) {
                    System.out.println("Le client a été bein mettre a jour");
                } else {
                    System.out.println("Le client n'a pas mettre a jour");
                }
                MainPage mainPage = new MainPage();
            }
        });
    }
}
