package org.odk.repository.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.odk.DatabaseConnection;
import org.odk.enums.EnumStatut;
import org.odk.model.ObjectifApprentissage;

public class JdbcObjectifApprentissageRepository {

    public ObjectifApprentissage save(ObjectifApprentissage objectif) {

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

        String sqlApprentissage = """
            INSERT INTO objectif_apprentissage(
                objectif_id,
                type_apprentissage,
                ressource,
                duree_etude_par_jour,
                jours_etudies,
                sujet
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
                    PreparedStatement psApprentissage =
                            connection.prepareStatement(sqlApprentissage)
            ) {
                psApprentissage.setInt(1, objectif.getId());
                psApprentissage.setString(2, objectif.getType_apprentissage());
                psApprentissage.setString(3, objectif.getRessource());
                psApprentissage.setInt(4, objectif.getDuree_etudeParJour());
                psApprentissage.setInt(5, objectif.getJours_etudies());
                psApprentissage.setString(6, objectif.getSujet());

                psApprentissage.executeUpdate();
            }

            connection.commit();

            System.out.println("✓ Objectif apprentissage sauvegardé.");

        } catch (Exception e) {
            System.err.println("Erreur sauvegarde objectif apprentissage : " + e.getMessage());
        }

        return objectif;
    }

    public ObjectifApprentissage findByObjectifId(int objectifId) {

        String sql = """
            SELECT o.*, a.*
            FROM objectif o
            INNER JOIN objectif_apprentissage a
                ON o.id = a.objectif_id
            WHERE o.id = ?
        """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, objectifId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ObjectifApprentissage objectif = new ObjectifApprentissage();

                    objectif.setId(rs.getInt("id"));
                    objectif.setNom_objectif(rs.getString("nom_objectif"));
                    objectif.setDescription(rs.getString("description"));
                    objectif.setDate_debut(rs.getDate("date_debut").toLocalDate());
                    objectif.setDate_fin(rs.getDate("date_fin").toLocalDate());
                    objectif.setStatus(EnumStatut.valueOf(rs.getString("statut")));
                    objectif.setUtilisateur_id(rs.getInt("utilisateur_id"));

                    objectif.setType_apprentissage(rs.getString("type_apprentissage"));
                    objectif.setRessource(rs.getString("ressource"));
                    objectif.setDuree_etudeParJour(rs.getInt("duree_etude_par_jour"));
                    objectif.setJours_etudies(rs.getInt("jours_etudies"));
                    objectif.setSujet(rs.getString("sujet"));

                    return objectif;
                }
            }

        } catch (Exception e) {
            System.err.println("Erreur recherche objectif apprentissage : " + e.getMessage());
        }

        return null;
    }
}