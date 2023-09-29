package org.example.Implementations;

import org.example.Entity.Client;
import org.example.Entity.Compte;
import org.example.Enums.CompteEtat;
import org.example.Helpers.DatabaseConnection;
import org.example.Interfaces.CompteInter;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompteImpl implements CompteInter {
    Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public Optional<Compte> save(Compte compte) {
        try {
            String query = "INSERT INTO public.compte(numero, solde, date, etat, client) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, compte.getNumero());
            preparedStatement.setInt(2, compte.getSolde());
            preparedStatement.setDate(3, Date.valueOf(compte.getDate().toString()));
            preparedStatement.setString(4, compte.getCompteEtat().toString());
            preparedStatement.setString(5, compte.getClient().getCode());
            preparedStatement.execute();
            return Optional.of(compte);
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Compte> update(Compte compte) {
        try {
            String query = "UPDATE public.compte SET solde=?, etat=?, client=?, date=? WHERE numero=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, compte.getSolde());
            preparedStatement.setString(2, compte.getCompteEtat().toString());
            preparedStatement.setString(3, compte.getClient().getCode());
            preparedStatement.setDate(4, Date.valueOf(compte.getDate().toString()));
            preparedStatement.setString(5, compte.getNumero());
            if (preparedStatement.executeUpdate() == 0) {
                return Optional.empty();
            } else {
                return Optional.of(compte);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }

    @Override
    public int delete(Compte compte) {
        try {
            String query = "DELETE FROM public.compte WHERE numero=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, compte.getNumero());
            if (preparedStatement.executeUpdate() == 0) {
                return 0;
            }
            return 1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public Optional<Compte> findOne(Compte compte) {
        try {
            String query = "SELECT numero, solde, date, etat, client FROM public.compte WHERE numero=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, compte.getNumero());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                compte.setNumero(resultSet.getString("numero"));
                compte.setSolde(resultSet.getInt("solde"));
                compte.setDate(LocalDate.parse(resultSet.getString("date")));
                compte.setCompteEtat(CompteEtat.valueOf(resultSet.getString("etat")));
                ClientImpl clientImpl = new ClientImpl();
                Client client = new Client();
                client.setCode(resultSet.getString("client"));
                Optional<Client> client1 = clientImpl.findOne(client);
                if (client1.isPresent()){
                    client = client1.get();
                }
                compte.setClient(client);
                return Optional.of(compte);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Compte> findByClient(Compte compte) {
        List<Compte> compteArrayList = new ArrayList<Compte>();
        try {
            String query = "SELECT numero, solde, date, etat, client FROM public.compte WHERE client=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, compte.getClient().getCode());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                compte.setNumero(resultSet.getString("numero"));
                compte.setSolde(resultSet.getInt("solde"));
                compte.setDate(LocalDate.parse(resultSet.getString("date")));
                compte.setCompteEtat(CompteEtat.valueOf(resultSet.getString("etat")));
                Client client = new Client();
                client.setCode(resultSet.getString("client"));
                compte.setClient(client);
                compteArrayList.add(compte);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return compteArrayList;
    }

    @Override
    public Optional<List<Compte>> findAll() {
        try {
            List<Compte> compteArrayList = new ArrayList<Compte>();
            String query = "SELECT numero, solde, etat, client, date FROM public.compte;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Compte compte = new Compte();
                compte.setNumero(resultSet.getString("numero"));
                compte.setSolde(resultSet.getInt("solde"));
                compte.setDate(LocalDate.parse(resultSet.getString("date")));
                compte.setCompteEtat(CompteEtat.valueOf(resultSet.getString("etat")));
                Client client = new Client();
                client.setCode(resultSet.getString("client"));
                compte.setClient(client);
                compteArrayList.add(compte);
            }
            return Optional.of(compteArrayList);
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Compte>> findAllByStatus() {
        try {
            List<Compte> compteArrayList = new ArrayList<Compte>();
            String query = "SELECT numero, solde, etat, client, date FROM public.compte ORDER BY etat DESC;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Compte compte = new Compte();
                compte.setNumero(resultSet.getString("numero"));
                compte.setSolde(resultSet.getInt("solde"));
                compte.setDate(LocalDate.parse(resultSet.getString("date")));
                compte.setCompteEtat(CompteEtat.valueOf(resultSet.getString("etat")));
                Client client = new Client();
                client.setCode(resultSet.getString("client"));
                compte.setClient(client);
                compteArrayList.add(compte);
            }
            return Optional.of(compteArrayList);
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Compte>> findAllByDate() {
        try {
            List<Compte> compteArrayList = new ArrayList<Compte>();
            String query = "SELECT numero, solde, etat, client, date FROM public.compte ORDER BY date DESC;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Compte compte = new Compte();
                compte.setNumero(resultSet.getString("numero"));
                compte.setSolde(resultSet.getInt("solde"));
                compte.setDate(LocalDate.parse(resultSet.getString("date")));
                compte.setCompteEtat(CompteEtat.valueOf(resultSet.getString("etat")));
                Client client = new Client();
                client.setCode(resultSet.getString("client"));
                compte.setClient(client);
                compteArrayList.add(compte);
            }
            return Optional.of(compteArrayList);
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Compte> updateEtat(Compte compte) {
        try {
            String query = "UPDATE public.compte SET etat=? WHERE numero=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, compte.getCompteEtat().toString());
            preparedStatement.setString(2, compte.getNumero());
            if (preparedStatement.executeUpdate() == 0) {
                return Optional.empty();
            } else {
                return Optional.of(compte);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }
}
