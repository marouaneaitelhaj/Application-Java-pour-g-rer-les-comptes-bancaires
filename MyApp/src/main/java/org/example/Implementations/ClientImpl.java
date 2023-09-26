package org.example.Implementations;

import org.example.Entity.Client;
import org.example.Helpers.DatabaseConnection;
import org.example.Interfaces.ClientInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        return null;
    }
}
