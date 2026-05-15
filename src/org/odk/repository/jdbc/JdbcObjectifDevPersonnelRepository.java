package org.odk.repository.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.odk.DatabaseConnection;
import org.odk.enums.EnumStatut;
import org.odk.model.ObjectifDevPersonnel;

public class JdbcObjectifDevPersonnelRepository {

    public ObjectifDevPersonnel save(ObjectifDevPersonnel objectif) {

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

        String sqlDevPersonnel = """
            INSERT INTO objectif_dev_personnel(
                objectif_id,
                type_dev_personnel,
                duree_dev_personnel,
                jours_realises,
                habitude_cible
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
                    PreparedStatement psDev =
                            connection.prepareStatement(sqlDevPersonnel)
            ) {
                psDev.setInt(1, objectif.getId());
                psDev.setString(2, objectif.getType_dev_personnel());
                psDev.setInt(3, objectif.getDuree_dev_personnel());
                psDev.setInt(4, objectif.getJours_realises());
                psDev.setString(5, objectif.getHabitude_cible());

                psDev.executeUpdate();
            }

            connection.commit();

            System.out.println("✓ Objectif développement personnel sauvegardé.");

        } catch (Exception e) {
            System.err.println("Erreur sauvegarde objectif développement personnel : " + e.getMessage());
        }

        return objectif;
    }

    public ObjectifDevPersonnel findByObjectifId(int objectifId) {

        String sql = """
            SELECT o.*, d.*
            FROM objectif o
            INNER JOIN objectif_dev_personnel d
                ON o.id = d.objectif_id
            WHERE o.id = ?
        """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, objectifId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ObjectifDevPersonnel objectif = new ObjectifDevPersonnel();

                    objectif.setId(rs.getInt("id"));
                    objectif.setNom_objectif(rs.getString("nom_objectif"));
                    objectif.setDescription(rs.getString("description"));
                    objectif.setDate_debut(rs.getDate("date_debut").toLocalDate());
                    objectif.setDate_fin(rs.getDate("date_fin").toLocalDate());
                    objectif.setStatus(EnumStatut.valueOf(rs.getString("statut")));
                    objectif.setUtilisateur_id(rs.getInt("utilisateur_id"));

                    objectif.setType_dev_personnel(rs.getString("type_dev_personnel"));
                    objectif.setDuree_dev_personnel(rs.getInt("duree_dev_personnel"));
                    objectif.setJours_realises(rs.getInt("jours_realises"));
                    objectif.setHabitude_cible(rs.getString("habitude_cible"));

                    return objectif;
                }
            }

        } catch (Exception e) {
            System.err.println("Erreur recherche objectif développement personnel : " + e.getMessage());
        }

        return null;
    }
}