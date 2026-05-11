package org.odk.repository.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.odk.DatabaseConnection;
import org.odk.enums.EnumStatut;
import org.odk.model.ObjectifSport;

public class JdbcObjectifSportRepository {

    public ObjectifSport save(ObjectifSport objectif) {

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

        String sqlSport = """
            INSERT INTO objectif_sport(
                objectif_id,
                type_activite,
                duree_seance,
                nb_seances_realisees,
                frequence_hebdo,
                niveau_sportif
            )
            VALUES (?, ?, ?, ?, ?, ?)
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
                    PreparedStatement psSport =
                            connection.prepareStatement(sqlSport)
            ) {
                psSport.setInt(1, objectif.getId());
                psSport.setString(2, objectif.getType_activite());
                psSport.setInt(3, objectif.getDuree_seance());
                psSport.setInt(4, objectif.getNb_seances_realisees());
                psSport.setInt(5, objectif.getFrequence_hebdo());
                psSport.setString(6, objectif.getNiveauSportif());

                psSport.executeUpdate();
            }

            connection.commit();

            System.out.println("✓ Objectif sport sauvegardé.");

        } catch (Exception e) {
            System.err.println("Erreur sauvegarde objectif sport : " + e.getMessage());
        }

        return objectif;
    }

    public ObjectifSport findByObjectifId(int objectifId) {

        String sql = """
            SELECT o.*, s.*
            FROM objectif o
            INNER JOIN objectif_sport s
                ON o.id = s.objectif_id
            WHERE o.id = ?
        """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, objectifId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ObjectifSport objectif = new ObjectifSport();

                    objectif.setId(rs.getInt("id"));
                    objectif.setNom_objectif(rs.getString("nom_objectif"));
                    objectif.setDescription(rs.getString("description"));
                    objectif.setDate_debut(rs.getDate("date_debut").toLocalDate());
                    objectif.setDate_fin(rs.getDate("date_fin").toLocalDate());
                    objectif.setStatus(EnumStatut.valueOf(rs.getString("statut")));
                    objectif.setUtilisateur_id(rs.getInt("utilisateur_id"));

                    objectif.setType_activite(rs.getString("type_activite"));
                    objectif.setDuree_seance(rs.getInt("duree_seance"));
                    objectif.setNb_seances_realisees(rs.getInt("nb_seances_realisees"));
                    objectif.setFrequence_hebdo(rs.getInt("frequence_hebdo"));
                    objectif.setNiveauSportif(rs.getString("niveau_sportif"));

                    return objectif;
                }
            }

        } catch (Exception e) {
            System.err.println("Erreur recherche objectif sport : " + e.getMessage());
        }

        return null;
    }
}