package org.example.Implementations;

import org.example.Entity.Client;
import org.example.Helpers.DatabaseConnection;
import org.example.Interfaces.ClientInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
            statement.execute();
            return t;
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
        return 0;
    }

    @Override
    public Client findOne(Client t) {
        return null;
    }

    @Override
    public List<Client> findAll() {
        return null;
    }
}
