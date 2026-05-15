package org.odk.repository.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.odk.DatabaseConnection;
import org.odk.enums.EnumStatut;
import org.odk.model.Objectif;
import org.odk.repository.interfaces.ObjectifRepository;


public class JdbcObjectifRepository implements ObjectifRepository {
	
	  @Override
	    public Objectif save(Objectif objectif) {

	        String sql = """
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
	                    objectif.getNom_objectif()
	            );

	            ps.setString(
	                    2,
	                    objectif.getDescription()
	            );

	            ps.setDate(
	                    3,
	                    Date.valueOf(
	                            objectif.getDate_debut()
	                    )
	            );

	            ps.setDate(
	                    4,
	                    Date.valueOf(
	                            objectif.getDate_fin()
	                    )
	            );

	            ps.setString(
	                    5,
	                    objectif.getStatus().name()
	            );

	            ps.setInt(
	                    6,
	                    objectif.getUtilisateur_id()
	            );

	            ps.executeUpdate();

	            try (ResultSet rs =
	                         ps.getGeneratedKeys()) {

	                if (rs.next()) {

	                    objectif.setId(
	                            rs.getInt(1)
	                    );
	                }
	            }

	            System.out.println(
	                    "✓ Objectif sauvegardé."
	            );

	        } catch (Exception e) {

	            System.err.println(
	                    "Erreur sauvegarde objectif : "
	                            + e.getMessage()
	            );
	        }

	        return objectif;
	    }

	    @Override
	    public void update(Objectif objectif) {

	        String sql = """
	            UPDATE objectif
	            SET nom_objectif = ?,
	                description = ?,
	                date_debut = ?,
	                date_fin = ?,
	                statut = ?,
	                utilisateur_id = ?
	            WHERE id = ?
	        """;

	        try (
	                Connection connection =
	                        DatabaseConnection.getConnection();

	                PreparedStatement ps =
	                        connection.prepareStatement(sql)
	        ) {

	            ps.setString(
	                    1,
	                    objectif.getNom_objectif()
	            );

	            ps.setString(
	                    2,
	                    objectif.getDescription()
	            );

	            ps.setDate(
	                    3,
	                    Date.valueOf(
	                            objectif.getDate_debut()
	                    )
	            );

	            ps.setDate(
	                    4,
	                    Date.valueOf(
	                            objectif.getDate_fin()
	                    )
	            );

	            ps.setString(
	                    5,
	                    objectif.getStatus().name()
	            );

	            ps.setInt(
	                    6,
	                    objectif.getUtilisateur_id()
	            );

	            ps.setInt(
	                    7,
	                    objectif.getId()
	            );

	            ps.executeUpdate();

	            System.out.println(
	                    "✓ Objectif modifié."
	            );

	        } catch (Exception e) {

	            System.err.println(
	                    "Erreur modification objectif : "
	                            + e.getMessage()
	            );
	        }
	    }

	    @Override
	    public void delete(int id) {

	        String sql = """
	            DELETE FROM objectif
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
	                    "✓ Objectif supprimé."
	            );

	        } catch (Exception e) {

	            System.err.println(
	                    "Erreur suppression objectif : "
	                            + e.getMessage()
	            );
	        }
	    }

	    @Override
	    public Objectif findById(int id) {

	        String sql = """
	            SELECT *
	            FROM objectif
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

	                    return mapperObjectif(rs);
	                }
	            }

	        } catch (Exception e) {

	            System.err.println(
	                    "Erreur recherche objectif : "
	                            + e.getMessage()
	            );
	        }

	        return null;
	    }

	    @Override
	    public List<Objectif> findAll() {

	        List<Objectif> objectifs =
	                new ArrayList<>();

	        String sql = """
	            SELECT *
	            FROM objectif
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

	                objectifs.add(
	                        mapperObjectif(rs)
	                );
	            }

	        } catch (Exception e) {

	            System.err.println(
	                    "Erreur liste objectifs : "
	                            + e.getMessage()
	            );
	        }

	        return objectifs;
	    }

	    private Objectif mapperObjectif(ResultSet rs)
	            throws Exception {

	        Objectif objectif =
	                new Objectif() {

	                    @Override
	                    public double calculerPourcentage() {
	                        return 0;
	                    }
	                };

	        objectif.setId(
	                rs.getInt("id")
	        );

	        objectif.setNom_objectif(
	                rs.getString("nom_objectif")
	        );

	        objectif.setDescription(
	                rs.getString("description")
	        );

	        objectif.setDate_debut(
	                rs.getDate("date_debut")
	                        .toLocalDate()
	        );

	        objectif.setDate_fin(
	                rs.getDate("date_fin")
	                        .toLocalDate()
	        );

	        objectif.setStatus(
	                EnumStatut.valueOf(
	                        rs.getString("statut")
	                )
	        );

	        objectif.setUtilisateur_id(
	                rs.getInt("utilisateur_id")
	        );

	        return objectif;
	    }

		@Override
		public List<Objectif> findByUtilisateurId(int utilisateurId) {
			 List<Objectif> objectifs =
			            new ArrayList<>();

			    String sql = """
			        SELECT *
			        FROM objectif
			        WHERE utilisateur_id = ?
			        ORDER BY date_debut DESC
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

			                objectifs.add(
			                        mapperObjectif(rs)
			                );
			            }
			        }

			    } catch (Exception e) {

			        System.err.println(
			                "Erreur objectifs utilisateur : "
			                        + e.getMessage()
			        );
			    }

			    return objectifs;
		}

		@Override
		public void updateStatut(int objectifId, String statut) {
			 String sql = """
				        UPDATE objectif
				        SET statut = ?
				        WHERE id = ?
				    """;

				    try (

				            Connection connection =
				                    DatabaseConnection.getConnection();

				            PreparedStatement ps =
				                    connection.prepareStatement(sql)
				    ) {

				        ps.setString(1, statut);

				        ps.setInt(2, objectifId);

				        ps.executeUpdate();

				        System.out.println(
				                "✓ Statut objectif mis à jour."
				        );

				    } catch (Exception e) {

				        System.err.println(
				                "Erreur mise à jour statut : "
				                        + e.getMessage()
				        );
				    }
			
		}

  
}