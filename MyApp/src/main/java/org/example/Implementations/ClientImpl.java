package org.example.Implementations;

import org.example.Entity.Client;
import org.example.Helpers.DatabaseConnection;
import org.example.Interfaces.ClientInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

public class ClientImpl implements ClientInter {
    Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public Client save(Client t) {
        try {
            String query = "INSERT INTO public.client(nom, prenom, datedenaissance, telephone, code, adresse) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, t.getNom());
            statement.setString(2, t.getPrenom());
            statement.setString(3, t.getDateDeNaissance().toString());
            statement.setString(4, t.getTelephone());
            statement.setString(5, t.getCode());
            statement.setString(6, t.getAdresse());
            if (statement.execute()) {
                return t;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Client update(Client t) {
        return null;
    }

    @Override
    public int delete(Client t) {
        try {
            String query = "DELETE FROM public.client WHERE code=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, t.getCode());
            System.out.println(preparedStatement.execute());
            if (preparedStatement.execute()) {
                return 1;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public Client findOne(Client t) {
        try {
            String query = "SELECT nom, prenom, telephone, code, adresse, datedenaissance FROM public.client WHERE code=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, t.getCode());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                t.setNom(resultSet.getString("nom"));
                t.setPrenom(resultSet.getString("prenom"));
                t.setTelephone(resultSet.getString("telephone"));
                t.setCode(resultSet.getString("code"));
                t.setAdresse(resultSet.getString("adresse"));
                t.setDateDeNaissance(LocalDate.parse(resultSet.getString("datedenaissance")));
            }
            return t;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    @Override
    public List<Client> findAll() {
        return null;
    }
}
