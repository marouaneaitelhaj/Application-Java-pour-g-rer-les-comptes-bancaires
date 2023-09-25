package org.example.Implementations;

import org.example.Entity.Courant;
import org.example.Helpers.DatabaseConnection;
import org.example.Interfaces.CourantInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class CourantImpl implements CourantInter {
    Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public Optional<Courant> save(Courant courant) {
        try {
            String query = "INSERT INTO public.comptecourant(compte, decouvert) VALUES (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, courant.getNumero());
            preparedStatement.setDouble(2, courant.getDecouvert());
            preparedStatement.execute();
            return Optional.of(courant);
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Courant> update(Courant courant) {
        return Optional.empty();
    }

    @Override
    public int delete(Courant courant) {
        return 0;
    }

    @Override
    public Optional<Courant> findOne(Courant courant) {
        return Optional.empty();
    }

    @Override
    public List<Courant> findAll() {
        return null;
    }
}
