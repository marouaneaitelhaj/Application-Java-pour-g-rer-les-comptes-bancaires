package org.example.Implementations;

import org.example.Entity.Epargne;
import org.example.Helpers.DatabaseConnection;
import org.example.Interfaces.EpargneInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class EpargneImpl implements EpargneInter {
    Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public Optional<Epargne> save(Epargne epargne) {
        try {
            String query = "INSERT INTO public.compteepargne(compte, tauxdinteret) VALUES (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, epargne.getNumero());
            preparedStatement.setDouble(2, epargne.getTauxDinteret());
            preparedStatement.execute();
            return Optional.of(epargne);
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Epargne> update(Epargne epargne) {
        return Optional.empty();
    }

    @Override
    public int delete(Epargne epargne) {
        return 0;
    }

    @Override
    public Optional<Epargne> findOne(Epargne epargne) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Epargne>> findAll() {
        return null;
    }
}
