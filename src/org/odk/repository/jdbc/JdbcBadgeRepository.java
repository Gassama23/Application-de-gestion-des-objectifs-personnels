package org.odk.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.odk.DatabaseConnection;
import org.odk.model.Badge;
import org.odk.repository.interfaces.BadgeRepository;

public class JdbcBadgeRepository implements BadgeRepository {

    @Override
    public Badge findById(int id) {

        String sql = """
            SELECT *
            FROM badge
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

                    return mapperBadge(rs);
                }
            }

        } catch (Exception e) {

            System.err.println(
                    "Erreur recherche badge : "
                            + e.getMessage()
            );
        }

        return null;
    }

    @Override
    public List<Badge> findAll() {

        List<Badge> badges =
                new ArrayList<>();

        String sql = """
            SELECT *
            FROM badge
            ORDER BY condition_succes ASC
        """;

        try (

                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                badges.add(
                        mapperBadge(rs)
                );
            }

        } catch (Exception e) {

            System.err.println(
                    "Erreur liste badges : "
                            + e.getMessage()
            );
        }

        return badges;
    }

    private Badge mapperBadge(
            ResultSet rs
    ) throws Exception {

        Badge badge =
                new Badge();

        badge.setId(
                rs.getInt("id")
        );

        badge.setNom(
                rs.getString("nom")
        );

        badge.setDescription(
                rs.getString("description")
        );

        badge.setConditionStreak(
                rs.getInt("condition_succes")
        );

        return badge;
    }

	@Override
	public Badge sauvergarder(Badge badge) {
		String sql = """
	            INSERT INTO badge(
	                nom,
	                description,
	                condition_succes
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

	            ps.setString(
	                    1,
	                    badge.getNom()
	            );

	            ps.setString(
	                    2,
	                    badge.getDescription()
	            );

	            ps.setInt(
	                    3,
	                    badge.getConditionStreak()
	            );

	            ps.executeUpdate();

	            try (ResultSet rs =
	                         ps.getGeneratedKeys()) {

	                if (rs.next()) {

	                    badge.setId(
	                            rs.getInt(1)
	                    );
	                }
	            }

	            System.out.println(
	                    "✓ Badge sauvegardé."
	            );

	        } catch (Exception e) {

	            System.err.println(
	                    "Erreur sauvegarde badge : "
	                            + e.getMessage()
	            );
	        }

	        return badge;
	}

}
