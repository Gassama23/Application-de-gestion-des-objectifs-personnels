package org.odk.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.odk.DatabaseConnection;
import org.odk.model.Badge;
import org.odk.model.BadgeUtilisateur;
import org.odk.repository.interfaces.BadgeUtilisateurRepository;

public class JdbcBadgeUtilisateurRepository implements BadgeUtilisateurRepository {

    @Override
    public BadgeUtilisateur sauvegarder(
            BadgeUtilisateur badgeUtilisateur
    ) {

        String sql = """
            INSERT INTO badge_utilisateur(
                date_obtention,
                utilisateur_id,
                badge_id
            )
            VALUES (?, ?, ?)
        """;

        try (

                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(
                                sql,
                                Statement.RETURN_GENERATED_KEYS
                        )
        ) {

            ps.setDate(
                    1,
                    java.sql.Date.valueOf(
                            badgeUtilisateur.getDateObtention()
                    )
            );

            ps.setInt(
                    2,
                    badgeUtilisateur.getUtilisateurId()
            );

            ps.setInt(
                    3,
                    badgeUtilisateur.getBadgeId()
            );

            ps.executeUpdate();

            try (ResultSet rs =
                         ps.getGeneratedKeys()) {

                if (rs.next()) {

                    badgeUtilisateur.setId(
                            rs.getInt(1)
                    );
                }
            }

            System.out.println(
                    "✓ Badge attribué à l'utilisateur."
            );

        } catch (Exception e) {

            System.err.println(
                    "Erreur attribution badge : "
                            + e.getMessage()
            );
        }

        return badgeUtilisateur;
    }

    @Override
    public boolean badgeDejaAttribue(
            int utilisateurId,
            int badgeId
    ) {

    	String sql = """
    		    SELECT id
    		    FROM badge_utilisateur
    		    WHERE utilisateur_id = ?
    		      AND badge_id = ?
    		""";

        try (

                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql)
        ) {

            ps.setInt(1, utilisateurId);

            ps.setInt(2, badgeId);

            try (ResultSet rs =
                         ps.executeQuery()) {

                return rs.next();
            }

        } catch (Exception e) {

            System.err.println(
                    "Erreur vérification badge : "
                            + e.getMessage()
            );
        }

        return false;
    }

    @Override
    public List<BadgeUtilisateur> findByUtilisateurId(
            int utilisateurId
    ) {

        List<BadgeUtilisateur> badges =
                new ArrayList<>();

        String sql = """
            SELECT *
            FROM badge_utilisateur
            WHERE utilisateur_id = ?
            ORDER BY date_obtention DESC
        """;

        try (

                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql)
        ) {

            ps.setInt(1, utilisateurId);

            try (ResultSet rs =
                         ps.executeQuery()) {

                while (rs.next()) {

                    badges.add(
                            mapperBadgeUtilisateur(rs)
                    );
                }
            }

        } catch (Exception e) {

            System.err.println(
                    "Erreur liste badges : "
                            + e.getMessage()
            );
        }

        return badges;
    }

    private BadgeUtilisateur mapperBadgeUtilisateur(
            ResultSet rs
    ) throws Exception {

        BadgeUtilisateur badgeUtilisateur =
                new BadgeUtilisateur();

        badgeUtilisateur.setId(
                rs.getInt("id")
        );

        badgeUtilisateur.setDateObtention(
                rs.getDate("date_obtention")
                        .toLocalDate()
        );

        badgeUtilisateur.setUtilisateurId(
                rs.getInt("utilisateur_id")
        );

        badgeUtilisateur.setBadgeId(
                rs.getInt("badge_id")
        );

        return badgeUtilisateur;
    }
}