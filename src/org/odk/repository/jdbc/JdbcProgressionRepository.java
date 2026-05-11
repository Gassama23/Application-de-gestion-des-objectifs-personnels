package org.odk.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.odk.DatabaseConnection;
import org.odk.model.Progression;
import org.odk.repository.interfaces.ProgressionRepository;

public class JdbcProgressionRepository implements ProgressionRepository {

    @Override
    public Progression sauvegarder(Progression progress) {

        String sql = """
            INSERT INTO progression(
                date_progression,
                etat,
                commentaire,
                objectif_id,
                action_quotidienne_id
            )
            VALUES (?, ?, ?, ?, ?)
        """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {

            java.sql.Date sqlDate =
                    new java.sql.Date(
                            progress.getDate_progression().getTime()
                    );

            ps.setDate(1, sqlDate);
            ps.setBoolean(2, progress.isEtat());
            ps.setString(3, progress.getCommentaire());
            ps.setInt(4, progress.getObjectif_id());
            ps.setInt(5, progress.getAction_quotidienne_id());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    progress.setId(rs.getInt(1));
                }
            }

            System.out.println("✓ Progression sauvegardée.");

        } catch (Exception e) {
            System.err.println("Erreur sauvegarde progression : " + e.getMessage());
        }

        return progress;
    }

    @Override
    public double calculerPourcentage(int objectifId) {

        List<Progression> progressions =
                afficher(objectifId);

        if (progressions == null || progressions.isEmpty()) {
            return 0;
        }

        int total = progressions.size();
        int realisees = 0;

        for (Progression progression : progressions) {
            if (progression.isEtat()) {
                realisees++;
            }
        }

        return ((double) realisees / total) * 100;
    }

    @Override
    public List<Progression> afficher(int objectifId) {

        List<Progression> progressions =
                new ArrayList<>();

        String sql = """
            SELECT *
            FROM progression
            WHERE objectif_id = ?
            ORDER BY date_progression ASC
        """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, objectifId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    progressions.add(mapperProgression(rs));
                }
            }

        } catch (Exception e) {
            System.err.println("Erreur recherche progressions : " + e.getMessage());
        }

        return progressions;
    }

    @Override
    public int compterActionsReussiesParUtilisateur(int utilisateurId) {

        String sql = """
            SELECT COUNT(p.id) AS total
            FROM progression p
            INNER JOIN objectif o
                ON p.objectif_id = o.id
            WHERE o.utilisateur_id = ?
              AND p.etat = true
        """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, utilisateurId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }

        } catch (Exception e) {
            System.err.println("Erreur comptage actions réussies : " + e.getMessage());
        }

        return 0;
    }

    private Progression mapperProgression(ResultSet rs) throws Exception {

        Progression progression =
                new Progression(
                        rs.getDate("date_progression"),
                        rs.getBoolean("etat"),
                        rs.getString("commentaire")
                );

        progression.setId(rs.getInt("id"));
        progression.setObjectif_id(rs.getInt("objectif_id"));
        progression.setAction_quotidienne_id(
                rs.getInt("action_quotidienne_id")
        );

        return progression;
    }
}