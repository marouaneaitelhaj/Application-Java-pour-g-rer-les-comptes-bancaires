package org.example.Views;

import org.example.Entity.Client;
import org.example.Entity.Compte;
import org.example.Entity.Courant;
import org.example.Entity.Epargne;
import org.example.Enums.CompteEtat;
import org.example.Helpers.MyFunction;
import org.example.Implementations.CompteImpl;
import org.example.Implementations.CourantImpl;
import org.example.Implementations.EpargneImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class CompteView {
    Scanner scanner = new Scanner(System.in);
    CompteImpl compteImpl = new CompteImpl();
    CourantImpl courantImpl = new CourantImpl();
    EpargneImpl epargneImpl = new EpargneImpl();

    public CompteView() {
        System.out.println("1- Créer un compte");
        System.out.println("2- Chercher un compte par client");
        System.out.println("3- Supprimer un compte");
        System.out.println("4- Changer le statut d'un compte");
        System.out.println("5- Afficher la liste des comptes");
        System.out.println("6- Afficher la liste des comptes par etat");
        System.out.println("7- Afficher la liste des comptes par date");
        System.out.println("8- Mettre à jour un compte");
        System.out.println("9- Chercher un compte par numero d'operation");
        switch (scanner.nextLine()) {
            case "1" -> {
                System.out.println("1- Compte Courant");
                System.out.println("2- Compte Epargne");
                switch (scanner.nextLine()) {
                    case "1" -> {
                        this.saveCompteEpargne();
                    }
                    case "2" -> {
                        this.saveCompteCourant();
                    }
                    default -> {
                        System.out.println("Vous devez choisir un choix valide");
                        new CompteView();
                    }
                }
            }
            case "2" -> {
                this.findByClientView();
            }
            case "3" -> {
                this.deleteView();
            }
            case "4" -> {
                this.updateEtatView();
            }
            case "5" -> {
                this.showAllView();
            }
            case "6" -> {
                this.showAllByStatusView();
            }
            case "7" -> {
                this.showAllByDateView();
            }
            case "8" -> {
                this.updateView();
            }
            case "9" -> {
                this.findByNumero();
            }
            default -> {
                System.out.println("Vous devez choisir un choix valide");
                new CompteView();
            }
        }
    }

    public Optional<Compte> createCompteView() {
        System.out.println("Numero: ");
        String numero = scanner.nextLine();
        System.out.println("Solde ($): ");
        int sold = Integer.parseInt(scanner.nextLine());
        System.out.println("Client");
        String code = scanner.nextLine();
        Client client = new Client(code);
        LocalDate date = LocalDate.now();
        Compte compte = new Compte(numero,sold,date,CompteEtat.Active,client);
        Optional<Compte> optionalCompte = compteImpl.save(compte);
        return optionalCompte;
    }

    public void findByClientView() {
        System.out.println("Client: ");
        Client client = new Client();
        client.setCode(scanner.nextLine());
        Compte compte = new Compte();
        compte.setClient(client);
        List<Compte> compteList = compteImpl.findByClient(compte);
        if (compteList.isEmpty()) {
            System.out.println("Aucun employé trouvé");
        } else {
            compteList.stream().forEach(compte1 -> {
                System.out.println(compte1.getNumero() + "    " + compte1.getSolde() + "  " + compte1.getDate() + "   " + compte1.getCompteEtat() + "   " + compte1.getClient().getCode());
            });
        }
        MyFunction.appuyezPourQuitter();
        new CompteView();
    }

    public void deleteView() {
        System.out.println("Numero : ");
        Compte compte = new Compte();
        compte.setNumero(scanner.nextLine());
        if (compteImpl.delete(compte) == 1) {
            System.out.println("La suppression a bien été effectuée");
        } else {
            System.out.println("La suppression n'a pas bien été effectuée");
        }
        new MainPage();
    }

    public void saveCompteEpargne() {
        Optional<Compte> compte = this.createCompteView();
        if (compte.isPresent()) {
            System.out.println("Taux Dinteret :");
            Epargne epargne = new Epargne(compte.get(), Double.valueOf(scanner.nextLine()));
            Optional<Epargne> epargne1 = epargneImpl.save(epargne);
            if (epargne1.isPresent()) {
                System.out.println("Le client a été bein ajoutée");
            } else {
                System.out.println("Le client n'a pas ajouté");
            }
        } else {
            System.out.println("Le client n'a pas ajouté");
        }
        new CompteView();
    }

    public void saveCompteCourant() {
        Optional<Compte> compte = this.createCompteView();
        if (compte.isPresent()) {
            System.out.println("Decouvert :");
            Courant courant = new Courant(compte.get().getNumero(), compte.get().getSolde(), compte.get().getDate(), compte.get().getCompteEtat(), compte.get().getClient(), Double.valueOf(scanner.nextLine()));
            Optional<Courant> courant1 = courantImpl.save(courant);
            if (courant1.isPresent()) {
                System.out.println("Le client a été bein ajoutée");
            } else {
                System.out.println("Le client n'a pas ajouté");
            }
        } else {
            System.out.println("Le client n'a pas ajouté");
        }
        new CompteView();
    }

    public void updateView() {
        List<Compte> compteList = this.showAllView();
        System.out.println("Numero: ");
        String numero = scanner.nextLine();
        if (compteList.isEmpty()) {
            System.out.println("quelque chose s'est mal passé");
        } else {
            compteList.forEach(compte11 -> {
                if (Objects.equals(compte11.getNumero(), numero)) {
                    Compte compte = new Compte();
                    Client client = new Client();
                    compte.setNumero(numero);
                    System.out.println("Solde ($): ");
                    compte.setSolde(Integer.parseInt(scanner.nextLine()));
                    compte.setCompteEtat(CompteEtat.Active);
                    System.out.println("Client");
                    client.setCode(scanner.nextLine());
                    compte.setClient(client);
                    compte.setDate(LocalDate.now());
                    Optional<Compte> optionalCompte = Optional.of(compte);
                    optionalCompte.ifPresent(value -> compteImpl.update(value));
                    System.out.println("le compte est mis à jour");
                }
            });
        }
        MyFunction.appuyezPourQuitter();
        new CompteView();
    }


    public List<Compte> showAllView() {
        List<Compte> compteList = compteImpl.findAll();
        if (compteList.isEmpty()) {
            System.out.println("Aucun compte trouvé");
        } else {
            compteList.forEach(compte1 -> {
                System.out.println(compte1.getNumero() + "    " + compte1.getSolde() + "  " + compte1.getDate() + "   " + compte1.getCompteEtat() + "   " + compte1.getClient().getCode());
            });
        }
        return compteList;
    }

    public void showAllByStatusView() {
        List<Compte> compteList = compteImpl.findAllByStatus();
        if (compteList.isEmpty()) {
            System.out.println("Aucun compte trouvé");
        } else {
            compteList.forEach(compte1 -> {
                System.out.println(compte1.getNumero() + "    " + compte1.getSolde() + "  " + compte1.getDate() + "   " + compte1.getCompteEtat() + "   " + compte1.getClient().getCode());
            });
        }
        MyFunction.appuyezPourQuitter();
        new CompteView();
    }

    public void showAllByDateView() {
        List<Compte> compteList = compteImpl.findAllByDate();
        if (compteList.isEmpty()) {
            System.out.println("Aucun compte trouvé");
        } else {
            compteList.forEach(compte1 -> {
                System.out.println(compte1.getNumero() + "    " + compte1.getSolde() + "  " + compte1.getDate() + "   " + compte1.getCompteEtat() + "   " + compte1.getClient().getCode());
            });
        }
        MyFunction.appuyezPourQuitter();
        new CompteView();
    }

    public void updateEtatView() {
        List<Compte> compteList = this.showAllView();
        System.out.println("Numero: ");
        String numero = scanner.nextLine();
        if (compteList.isEmpty()) {
        } else {
            compteList.forEach(compte -> {
                if (Objects.equals(numero, compte.getNumero())) {
                    if (compte.getCompteEtat() == CompteEtat.Active) {
                        compte.setCompteEtat(CompteEtat.Inactive);
                    } else {
                        compte.setCompteEtat(CompteEtat.Active);
                    }
                    if (compteImpl.updateEtat(compte).isEmpty()) {
                        System.out.println("le compte n'a pas été mis à jour");
                    } else {
                        System.out.println("le compte est mis à jour");
                    }
                }
            });
        }
        MyFunction.appuyezPourQuitter();
        new CompteView();
    }

    public void findByNumero() {
        System.out.println("Numero: ");
        Compte compte = new Compte();
        compte.setNumero(scanner.nextLine());
        Optional<Compte> optionalCompte = compteImpl.findOne(compte);
        if (optionalCompte.isEmpty()) {
            System.out.println("aucun compte trouvé");
        } else {
            Compte compte1 = optionalCompte.get();
            System.out.println(compte1.getNumero() + "    " + compte1.getSolde() + "  " + compte1.getDate() + "   " + compte1.getCompteEtat() + "   " + compte1.getClient().getCode());
        }
    }

}
