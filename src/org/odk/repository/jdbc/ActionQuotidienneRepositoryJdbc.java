package org.odk.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.odk.DatabaseConnection;
import org.odk.enums.EnumStatutAction;
import org.odk.model.ActionQuotidienne;
import org.odk.model.Planning;
import org.odk.repository.interfaces.ActionQuotidienneRepository;

public class ActionQuotidienneRepositoryJdbc implements ActionQuotidienneRepository {


    @Override
    public void save(ActionQuotidienne action) {
    	
    	String sql = """
                INSERT INTO action_quotidienne
                (
                    description,
                    date_prevue,
                    date_realisation,
                    statut,
                    commentaire,
                    planning_id
                )
                VALUES (?, ?, ?, ?, ?, ?)
            """;

            try (Connection connection = DatabaseConnection.getConnection();

                  PreparedStatement stmt = connection.prepareStatement(sql)
            ) { stmt.setString(1, action.getDescription());
            stmt.setDate(2,convertirDateSql(action.getDatePrevue()));

                stmt.setDate(
                        3,
                        convertirDateSql(action.getDateRealisation())
                );

                stmt.setString(
                        4,
                        action.getStatut().name()
                );

                stmt.setString(
                        5,
                        action.getCommentaire()
                );

                stmt.setInt(
                        6,
                        action.getPlanning().getId()
                );

                stmt.executeUpdate();

                System.out.println(
                        "✓ Action quotidienne enregistrée."
                );

            } catch (SQLException e) {

                System.err.println(
                        "Erreur sauvegarde action : "
                                + e.getMessage()
                );
            }


    }

    @Override
    public void update(ActionQuotidienne action) {

    	 String sql = """
    	            UPDATE action_quotidienne
    	            SET
    	                description = ?,
    	                date_prevue = ?,
    	                date_realisation = ?,
    	                statut = ?,
    	                commentaire = ?,
    	                planning_id = ?
    	            WHERE id = ?
    	        """;

    	        try (
    	                Connection connection =
    	                        DatabaseConnection.getConnection();

    	                PreparedStatement stmt =
    	                        connection.prepareStatement(sql)
    	        ) {

    	            stmt.setString(1, action.getDescription());

    	            stmt.setDate(
    	                    2,
    	                    convertirDateSql(action.getDatePrevue())
    	            );

    	            stmt.setDate(
    	                    3,
    	                    convertirDateSql(action.getDateRealisation())
    	            );

    	            stmt.setString(
    	                    4,
    	                    action.getStatut().name()
    	            );

    	            stmt.setString(
    	                    5,
    	                    action.getCommentaire()
    	            );

    	            stmt.setInt(
    	                    6,
    	                    action.getPlanning().getId()
    	            );

    	            stmt.setInt(
    	                    7,
    	                    action.getId()
    	            );

    	            stmt.executeUpdate();

    	            System.out.println(
    	                    "✓ Action quotidienne modifiée."
    	            );

    	        } catch (SQLException e) {

    	            System.err.println(
    	                    "Erreur modification action : "
    	                            + e.getMessage()
    	            );
    	        }
    }

    @Override
    public void delete(int id) {

    	 String sql =
                 "DELETE FROM action_quotidienne WHERE id = ?";

         try (
                 Connection connection =
                         DatabaseConnection.getConnection();

                 PreparedStatement stmt =
                         connection.prepareStatement(sql)
         ) {

             stmt.setInt(1, id);

             stmt.executeUpdate();

             System.out.println(
                     "✓ Action supprimée."
             );

         } catch (SQLException e) {

             System.err.println(
                     "Erreur suppression action : "
                             + e.getMessage()
             );
         }
    }

    @Override
    public ActionQuotidienne findById(int id) {

    	 String sql =
                 "SELECT * FROM action_quotidienne WHERE id = ?";

         try (
                 Connection connection =
                         DatabaseConnection.getConnection();

                 PreparedStatement stmt =
                         connection.prepareStatement(sql)
         ) {

             stmt.setInt(1, id);

             try (ResultSet rs = stmt.executeQuery()) {

                 if (rs.next()) {
                     return mapperAction(rs);
                 }
             }

         } catch (SQLException e) {

             System.err.println(
                     "Erreur recherche action : "
                             + e.getMessage()
             );
         }

         return null;
    }

    @Override
    public List<ActionQuotidienne> findAll() {

        String sql =
                "SELECT * FROM action_quotidienne";

        List<ActionQuotidienne> actions =
                new ArrayList<>();

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement stmt =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()
        ) {

            while (rs.next()) {

                actions.add(
                        mapperAction(rs)
                );
            }

        } catch (SQLException e) {

            System.err.println(
                    "Erreur liste actions : "
                            + e.getMessage()
            );
        }

        return actions;

    }

    @Override
    public List<ActionQuotidienne> findByPlanningId(int planningId) {

    	 String sql = """
    	            SELECT *
    	            FROM action_quotidienne
    	            WHERE planning_id = ?
    	        """;

    	        List<ActionQuotidienne> actions =
    	                new ArrayList<>();

    	        try (
    	                Connection connection =
    	                        DatabaseConnection.getConnection();

    	                PreparedStatement stmt =
    	                        connection.prepareStatement(sql)
    	        ) {

    	            stmt.setInt(1, planningId);

    	            try (ResultSet rs = stmt.executeQuery()) {

    	                while (rs.next()) {

    	                    actions.add(
    	                            mapperAction(rs)
    	                    );
    	                }
    	            }

    	        } catch (SQLException e) {

    	            System.err.println(
    	                    "Erreur actions planning : "
    	                            + e.getMessage()
    	            );
    	        }

    	        return actions;
    }

	@Override
	public List<ActionQuotidienne> findByStatut(EnumStatutAction statut) {
		 String sql = """
		            SELECT *
		            FROM action_quotidienne
		            WHERE statut = ?
		        """;

		        List<ActionQuotidienne> actions =
		                new ArrayList<>();

		        try (
		                Connection connection =
		                        DatabaseConnection.getConnection();

		                PreparedStatement stmt =
		                        connection.prepareStatement(sql)
		        ) {

		            stmt.setString(
		                    1,
		                    statut.name()
		            );

		            try (ResultSet rs = stmt.executeQuery()) {

		                while (rs.next()) {

		                    actions.add(
		                            mapperAction(rs)
		                    );
		                }
		            }

		        } catch (SQLException e) {

		            System.err.println(
		                    "Erreur actions statut : "
		                            + e.getMessage()
		            );
		        }

		        return actions;
	}

	@Override
	public void marquerCommeRealisee(int id, String commentaire) {
		 String sql = """
		            UPDATE action_quotidienne
		            SET
		                statut = ?,
		                date_realisation = CURDATE(),
		                commentaire = ?
		            WHERE id = ?
		        """;

		        try (
		                Connection connection =
		                        DatabaseConnection.getConnection();

		                PreparedStatement stmt =
		                        connection.prepareStatement(sql)
		        ) {

		            stmt.setString(
		                    1,
		                    EnumStatutAction.TERMINEE.name()
		            );

		            stmt.setString(
		                    2,
		                    commentaire
		            );

		            stmt.setInt(
		                    3,
		                    id
		            );

		            stmt.executeUpdate();

		            System.out.println(
		                    "✓ Action marquée comme réalisée."
		            );

		        } catch (SQLException e) {

		            System.err.println(
		                    "Erreur validation action : "
		                            + e.getMessage()
		            );
		        }
		
	}
	
private ActionQuotidienne mapperAction(ResultSet rs) throws SQLException {

	        ActionQuotidienne action = new ActionQuotidienne();

	        action.setId(
	                rs.getInt("id")
	        );

	        action.setDescription(
	                rs.getString("description")
	        );

	        action.setDatePrevue(
	                rs.getDate("date_prevue")
	        );

	        action.setDateRealisation(
	                rs.getDate("date_realisation")
	        );

	        action.setStatut(
	                EnumStatutAction.valueOf(
	                        rs.getString("statut")
	                )
	        );

	        action.setCommentaire(
	                rs.getString("commentaire")
	        );

	        Planning planning =
	                new Planning();

	        planning.setId(
	                rs.getInt("planning_id")
	        );

	        action.setPlanning(planning);

	        return action;
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