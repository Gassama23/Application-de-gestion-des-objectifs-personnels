package org.odk.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.odk.DatabaseConnection;
import org.odk.model.Planning;
import org.odk.repository.interfaces.PlanningRepository;

public class PlanningRepositoryJdbc
        implements PlanningRepository {

    @Override
    public Planning save(Planning planning) {

        String sql = """
            INSERT INTO planning(
                titre,
                date_creation,
                actif,
                objectif_id
            )
            VALUES (?, ?, ?, ?)
        """;

        try (

                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(
                                sql,
                                Statement.RETURN_GENERATED_KEYS
                        )
        ) {

            ps.setString(
                    1,
                    planning.getTitre()
            );

            ps.setDate(
                    2,
                    convertirDateSql(
                            planning.getDateCreation()
                    )
            );

            ps.setBoolean(
                    3,
                    planning.isActif()
            );

            ps.setInt(
                    4,
                    planning.getObjectifId()
            );

            ps.executeUpdate();

            try (ResultSet rs =
                         ps.getGeneratedKeys()) {

                if (rs.next()) {

                    planning.setId(
                            rs.getInt(1)
                    );
                }
            }

            System.out.println(
                    "✓ Planning sauvegardé."
            );

        } catch (Exception e) {

            System.err.println(
                    "Erreur sauvegarde planning : "
                            + e.getMessage()
            );
        }

        return planning;
    }

    @Override
    public Planning findById(int id) {

        String sql = """
            SELECT *
            FROM planning
            WHERE id = ?
        """;

        try (

                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            try (ResultSet rs =
                         ps.executeQuery()) {

                if (rs.next()) {

                    return mapperPlanning(rs);
                }
            }

        } catch (Exception e) {

            System.err.println(
                    "Erreur recherche planning : "
                            + e.getMessage()
            );
        }

        return null;
    }

    @Override
    public Planning findByObjectifId(
            int objectifId
    ) {

        String sql = """
            SELECT *
            FROM planning
            WHERE objectif_id = ?
        """;

        try (

                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setInt(1, objectifId);

            try (ResultSet rs =
                         ps.executeQuery()) {

                if (rs.next()) {

                    return mapperPlanning(rs);
                }
            }

        } catch (Exception e) {

            System.err.println(
                    "Erreur planning objectif : "
                            + e.getMessage()
            );
        }

        return null;
    }

    @Override
    public List<Planning> findByUtilisateurId(
            int utilisateurId
    ) {

        List<Planning> plannings =
                new ArrayList<>();

        String sql = """
            SELECT p.*
            FROM planning p
            INNER JOIN objectif o
                ON p.objectif_id = o.id
            WHERE o.utilisateur_id = ?
        """;

        try (

                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setInt(1, utilisateurId);

            try (ResultSet rs =
                         ps.executeQuery()) {

                while (rs.next()) {

                    plannings.add(
                            mapperPlanning(rs)
                    );
                }
            }

        } catch (Exception e) {

            System.err.println(
                    "Erreur plannings utilisateur : "
                            + e.getMessage()
            );
        }

        return plannings;
    }

    private Planning mapperPlanning(
            ResultSet rs
    ) throws Exception {

        Planning planning =
                new Planning();

        planning.setId(
                rs.getInt("id")
        );

        planning.setTitre(
                rs.getString("titre")
        );

        planning.setDateCreation(
                rs.getDate("date_creation")
        );

        planning.setActif(
                rs.getBoolean("actif")
        );

        planning.setObjectifId(
                rs.getInt("objectif_id")
        );

        return planning;
    }

    private java.sql.Date convertirDateSql(
            java.util.Date date
    ) {

        if (date == null) {
            return null;
        }

        return new java.sql.Date(
                date.getTime()
        );
    }
}