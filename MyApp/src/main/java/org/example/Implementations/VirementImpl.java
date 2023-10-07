package org.example.Implementations;

import org.example.Entity.Compte;
import org.example.Entity.Virement;
import org.example.Helpers.DatabaseConnection;
import org.example.Interfaces.CompteInter;
import org.example.Interfaces.VirementInter;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VirementImpl implements VirementInter {
    Connection connection = DatabaseConnection.getInstance().getConnection();
    private CompteInter compteInter;

    public VirementImpl(CompteInter compteInter) {
        this.compteInter = compteInter;
    }

    @Override
    public Optional<Virement> save(Virement virement) {
        try {
            String query = "BEGIN;" +
                    "UPDATE compte SET solde=? WHERE numero=?;" +
                    "UPDATE compte SET solde=? WHERE numero=?;" +
                    "INSERT INTO virment(comptedestinataire, compteemetteur, mantant,date) VALUES (?, ?, ?, ?);" +
                    "COMMIT;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, virement.getCompteDestinataire().getSolde());
            preparedStatement.setString(2, virement.getCompteDestinataire().getNumero());
            preparedStatement.setInt(3, virement.getCompteEmetteur().getSolde());
            preparedStatement.setString(4, virement.getCompteEmetteur().getNumero());
            preparedStatement.setString(5, virement.getCompteDestinataire().getNumero());
            preparedStatement.setString(6, virement.getCompteEmetteur().getNumero());
            preparedStatement.setInt(7, virement.getMantant());
            preparedStatement.setDate(8, Date.valueOf(LocalDate.now()));
            preparedStatement.execute();
            return Optional.of(virement);
        } catch (Exception e) {

        }
        return Optional.empty();
    }

    @Override
    public Optional<Virement> update(Virement virement) {
        return Optional.empty();
    }

    @Override
    public int delete(Virement virement) {
        try {
            String query = "DELETE FROM public.virment WHERE comptedestinataire=? and compteemetteur=? and mantant=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, virement.getCompteDestinataire().getNumero());
            preparedStatement.setString(2, virement.getCompteEmetteur().getNumero());
            preparedStatement.setInt(3, virement.getMantant());
            if (preparedStatement.executeUpdate() == 0) {
                return 0;
            } else {
                return 1;
            }
        } catch (Exception e) {

        }
        return 0;
    }

    @Override
    public Optional<Virement> findOne(Virement virement) {
        return Optional.empty();
    }

    @Override
    public List<Virement> findAll() {
        List<Virement> virements = new ArrayList<>();
        try {
            String query = "SELECT * FROM virment;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Compte comptedestinataire = new Compte(resultSet.getString("comptedestinataire"));
                Compte compteemetteur = new Compte(resultSet.getString("compteemetteur"));
                Optional<Compte> comptedestinataireOptional = this.compteInter.findOne(comptedestinataire);
                Optional<Compte> compteemetteurOptional = this.compteInter.findOne(compteemetteur);
                int mantant = resultSet.getInt("mantant");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                virements.add(new Virement(compteemetteurOptional.get(), comptedestinataireOptional.get(), mantant, date));
            }
        } catch (Exception e) {

        }
        return virements;
    }
    @Override
    public List<Virement> findAllByDate() {
        List<Virement> virements = new ArrayList<>();
        try {
            String query = "SELECT * FROM virment ORDER BY date;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Compte comptedestinataire = new Compte(resultSet.getString("comptedestinataire"));
                Compte compteemetteur = new Compte(resultSet.getString("compteemetteur"));
                Optional<Compte> comptedestinataireOptional = this.compteInter.findOne(comptedestinataire);
                Optional<Compte> compteemetteurOptional = this.compteInter.findOne(compteemetteur);
                int mantant = resultSet.getInt("mantant");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                virements.add(new Virement(compteemetteurOptional.get(), comptedestinataireOptional.get(), mantant, date));
            }
        } catch (Exception e) {

        }
        return virements;
    }
}
