package org.odk.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import org.odk.DatabaseConnection;

import org.odk.model.Planning;
import org.odk.model.PlanningDetail;

import org.odk.repository.interfaces.PlanningDetailRepository;

/**
 * Repository JDBC des détails planning.
 */
public class JdbcPlanningDetail implements PlanningDetailRepository {

    /**
     * Sauvegarder détail planning.
     */
    @Override
    public PlanningDetail save(
            PlanningDetail planningDetail
    ) {

        String sql = """
            INSERT INTO planning_detail(
                titre_section,
                contenu,
                ordre_section,
                planning_id
            )
            VALUES (?, ?, ?, ?)
        """;

        try (

                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(
                                sql,
                                PreparedStatement.RETURN_GENERATED_KEYS
                        )
        ) {

            ps.setString(
                    1,
                    planningDetail.getTitreSection()
            );

            ps.setString(
                    2,
                    planningDetail.getContenu()
            );

            ps.setInt(
                    3,
                    planningDetail.getOrdreSection()
            );

            ps.setInt(
                    4,
                    planningDetail
                            .getPlanning()
                            .getId()
            );

            ps.executeUpdate();

            /*
             * Récupération ID généré.
             */
            try (ResultSet rs =
                         ps.getGeneratedKeys()) {

                if (rs.next()) {

                    planningDetail.setId(
                            rs.getInt(1)
                    );
                }
            }

            System.out.println(
                    "✓ Détail planning sauvegardé."
            );

        } catch (Exception e) {

            System.err.println(
                    "Erreur sauvegarde détail planning : "
                            + e.getMessage()
            );
        }

        return planningDetail;
    }

    /**
     * Trouver détail par ID.
     */
    @Override
    public PlanningDetail findById(int id) {

        String sql = """
            SELECT *
            FROM planning_detail
            WHERE id = ?
        """;

        try (

                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            try (ResultSet rs =
                         ps.executeQuery()) {

                if (rs.next()) {

                    return mapperPlanningDetail(rs);
                }
            }

        } catch (Exception e) {

            System.err.println(
                    "Erreur recherche détail planning : "
                            + e.getMessage()
            );
        }

        return null;
    }

    /**
     * Trouver tous les détails
     * d’un planning.
     */
    @Override
    public List<PlanningDetail> findByPlanningId(
            int planningId
    ) {

        List<PlanningDetail> details =
                new ArrayList<>();

        String sql = """
            SELECT *
            FROM planning_detail
            WHERE planning_id = ?
            ORDER BY ordre_section ASC
        """;

        try (

                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql)
        ) {

            ps.setInt(1, planningId);

            try (ResultSet rs =
                         ps.executeQuery()) {

                while (rs.next()) {

                    details.add(
                            mapperPlanningDetail(rs)
                    );
                }
            }

        } catch (Exception e) {

            System.err.println(
                    "Erreur liste détails planning : "
                            + e.getMessage()
            );
        }

        return details;
    }

    /**
     * Supprimer détail planning.
     */
    @Override
    public void delete(int id) {

        String sql = """
            DELETE FROM planning_detail
            WHERE id = ?
        """;

        try (

                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println(
                    "✓ Détail planning supprimé."
            );

        } catch (Exception e) {

            System.err.println(
                    "Erreur suppression détail planning : "
                            + e.getMessage()
            );
        }
    }

    /**
     * Mapper ResultSet -> PlanningDetail.
     */
    private PlanningDetail mapperPlanningDetail(
            ResultSet rs
    ) throws Exception {

        PlanningDetail detail =
                new PlanningDetail();

        detail.setId(
                rs.getInt("id")
        );

        detail.setTitreSection(
                rs.getString("titre_section")
        );

        detail.setContenu(
                rs.getString("contenu")
        );

        detail.setOrdreSection(
                rs.getInt("ordre_section")
        );

        /*
         * Planning simplifié.
         */
        Planning planning =
                new Planning();

        planning.setId(
                rs.getInt("planning_id")
        );

        detail.setPlanning(planning);

        return detail;
    }
}