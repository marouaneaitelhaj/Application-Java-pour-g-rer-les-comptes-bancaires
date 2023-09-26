package org.example.Implementations;

import org.example.Entity.Client;
import org.example.Helpers.DatabaseConnection;
import org.example.Interfaces.ClientInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.PropertyResourceBundle;
import java.util.concurrent.RecursiveTask;

public class ClientImpl implements ClientInter {
    Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public Optional<Client> save(Client client) {
        try {
            String query = "INSERT INTO public.client(nom, prenom, datedenaissance, telephone, code, adresse) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, client.getNom());
            statement.setString(2, client.getPrenom());
            statement.setString(3, client.getDateDeNaissance().toString());
            statement.setString(4, client.getTelephone());
            statement.setString(5, client.getCode());
            statement.setString(6, client.getAdresse());
            return Optional.of(client);
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client> update(Client client) {
        try {
            String query = "UPDATE public.client SET nom=?, prenom=?, telephone=?, adresse=?, datedenaissance=? WHERE code=?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, client.getNom());
            statement.setString(2, client.getPrenom());
            statement.setString(3, client.getTelephone());
            statement.setString(4, client.getAdresse());
            statement.setString(5, client.getDateDeNaissance().toString());
            statement.setString(6, client.getCode());
            statement.executeUpdate();
            return Optional.of(client);
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }

    @Override
    public int delete(Client client) {
        try {
            String query = "DELETE FROM public.client WHERE code=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, client.getCode());
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
    public Optional<Client> findOne(Client client) {
        try {
            String query = "SELECT nom, prenom, telephone, code, adresse, datedenaissance FROM public.client WHERE code=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, client.getCode());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                client.setNom(resultSet.getString("nom"));
                client.setPrenom(resultSet.getString("prenom"));
                client.setTelephone(resultSet.getString("telephone"));
                client.setCode(resultSet.getString("code"));
                client.setAdresse(resultSet.getString("adresse"));
                client.setDateDeNaissance(LocalDate.parse(resultSet.getString("datedenaissance")));
            }
            return Optional.of(client);
        } catch (Exception e) {
            System.out.println(e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<List<Client>> findAll() {
        try {
            List<Client> clientList = new ArrayList<Client>();
            String query = "SELECT nom, prenom, telephone, code, adresse, datedenaissance FROM public.client;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Client client = new Client();
                client.setNom(resultSet.getString("nom"));
                client.setPrenom(resultSet.getString("prenom"));
                client.setTelephone(resultSet.getString("telephone"));
                client.setCode(resultSet.getString("code"));
                client.setAdresse(resultSet.getString("adresse"));
                client.setDateDeNaissance(LocalDate.parse(resultSet.getString("datedenaissance")));
                clientList.add(client);
            }
            return Optional.of(clientList);
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }

    public Optional<List<Client>> findByAtr(String text) {
        try {
            List<Client> clientList = new ArrayList<Client>();
            String query = "SELECT nom, prenom, telephone, code, adresse, datedenaissance FROM public.client WHERE nom LIKE ? OR prenom LIKE ? OR telephone LIKE ? OR code LIKE ? OR adresse LIKE ? OR datedenaissance LIKE ? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + text + "%");
            preparedStatement.setString(2, "%" + text + "%");
            preparedStatement.setString(3, "%" + text + "%");
            preparedStatement.setString(4, "%" + text + "%");
            preparedStatement.setString(5, "%" + text + "%");
            preparedStatement.setString(6, "%" + text + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Client client = new Client();
                client.setNom(resultSet.getString("nom"));
                client.setPrenom(resultSet.getString("prenom"));
                client.setTelephone(resultSet.getString("telephone"));
                client.setCode(resultSet.getString("code"));
                client.setAdresse(resultSet.getString("adresse"));
                client.setDateDeNaissance(LocalDate.parse(resultSet.getString("datedenaissance")));
                clientList.add(client);
            }
            return Optional.of(clientList);
        } catch (Exception e) {
        }
        return Optional.empty();
    }
}
