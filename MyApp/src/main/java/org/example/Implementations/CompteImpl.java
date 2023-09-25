package org.example.Implementations;

import org.example.Entity.Client;
import org.example.Entity.Compte;
import org.example.Enums.CompteEtat;
import org.example.Helpers.DatabaseConnection;
import org.example.Interfaces.CompteInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
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
            preparedStatement.setString(3, compte.getDate().toString());
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
        return Optional.empty();
    }

    @Override
    public int delete(Compte compte) {
        try {
            String query = "DELETE FROM public.compte WHERE numero=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, compte.getNumero());
            return 1;
        } catch (Exception e) {
        }
        return 0;
    }

    @Override
    public Optional<Compte> findOne(Compte compte) {
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
                return Optional.of(compte);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Compte> findAll() {
        return null;
    }
}
