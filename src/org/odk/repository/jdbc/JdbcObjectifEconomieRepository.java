package org.odk.repository.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.odk.DatabaseConnection;
import org.odk.enums.EnumStatut;
import org.odk.model.ObjectifEconomie;

public class JdbcObjectifEconomieRepository {

    public ObjectifEconomie save(ObjectifEconomie objectif) {

        String sqlObjectif = """
            INSERT INTO objectif(
                nom_objectif,
                description,
                date_debut,
                date_fin,
                statut,
                utilisateur_id
            )
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        String sqlEconomie = """
            INSERT INTO objectif_economie(
                objectif_id,
                type_economie,
                montant_cible,
                montant_epargne,
                delai_mois
            )
            VALUES (?, ?, ?, ?, ?)
        """;

        try (
                Connection connection = DatabaseConnection.getConnection()
        ) {
            connection.setAutoCommit(false);

            try (
                    PreparedStatement psObjectif =
                            connection.prepareStatement(sqlObjectif, Statement.RETURN_GENERATED_KEYS)
            ) {
                psObjectif.setString(1, objectif.getNom_objectif());
                psObjectif.setString(2, objectif.getDescription());
                psObjectif.setDate(3, Date.valueOf(objectif.getDate_debut()));
                psObjectif.setDate(4, Date.valueOf(objectif.getDate_fin()));
                psObjectif.setString(5, objectif.getStatus().name());
                psObjectif.setInt(6, objectif.getUtilisateur_id());

                psObjectif.executeUpdate();

                try (ResultSet rs = psObjectif.getGeneratedKeys()) {
                    if (rs.next()) {
                        objectif.setId(rs.getInt(1));
                    }
                }
            }

            try (
                    PreparedStatement psEconomie =
                            connection.prepareStatement(sqlEconomie)
            ) {
                psEconomie.setInt(1, objectif.getId());
                psEconomie.setString(2, objectif.getType_economie());
                psEconomie.setDouble(3, objectif.getMontant_cible());
                psEconomie.setDouble(4, objectif.getMontant_epargne());
                psEconomie.setInt(5, objectif.getDelai_mois());

                psEconomie.executeUpdate();
            }

            connection.commit();

            System.out.println("✓ Objectif économie sauvegardé.");

        } catch (Exception e) {
            System.err.println("Erreur sauvegarde objectif économie : " + e.getMessage());
        }

        return objectif;
    }

    public ObjectifEconomie findByObjectifId(int objectifId) {

        String sql = """
            SELECT o.*, e.*
            FROM objectif o
            INNER JOIN objectif_economie e
                ON o.id = e.objectif_id
            WHERE o.id = ?
        """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, objectifId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ObjectifEconomie objectif = new ObjectifEconomie();

                    objectif.setId(rs.getInt("id"));
                    objectif.setNom_objectif(rs.getString("nom_objectif"));
                    objectif.setDescription(rs.getString("description"));
                    objectif.setDate_debut(rs.getDate("date_debut").toLocalDate());
                    objectif.setDate_fin(rs.getDate("date_fin").toLocalDate());
                    objectif.setStatus(EnumStatut.valueOf(rs.getString("statut")));
                    objectif.setUtilisateur_id(rs.getInt("utilisateur_id"));

                    objectif.setType_economie(rs.getString("type_economie"));
                    objectif.setMontant_cible(rs.getDouble("montant_cible"));
                    objectif.setMontant_epargne(rs.getDouble("montant_epargne"));
                    objectif.setDelai_mois(rs.getInt("delai_mois"));

                    return objectif;
                }
            }

        } catch (Exception e) {
            System.err.println("Erreur recherche objectif économie : " + e.getMessage());
        }

        return null;
    }
}