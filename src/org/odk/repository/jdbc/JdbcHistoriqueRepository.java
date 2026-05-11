package org.odk.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.odk.DatabaseConnection;
import org.odk.model.Historique;
import org.odk.repository.interfaces.HistoriqueRepository;

public class JdbcHistoriqueRepository implements HistoriqueRepository{

	@Override
	public Historique sauvegarder(Historique historique) {
		 String sql = """
		            INSERT INTO historique(
		                date_historique,
		                action,
		                utilisateur_id,
		                objectif_id
		            )
		            VALUES (?, ?, ?, ?)
		        """;

		        try (
		                Connection connection = DatabaseConnection.getConnection();
		                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
		        ) {

		            ps.setDate(1, new java.sql.Date(historique.getDateHistorique().getTime()));
		            ps.setString(2, historique.getAction());
		            ps.setInt(3, historique.getUtilisateurId());
		            ps.setInt(4, historique.getObjectifId());

		            ps.executeUpdate();

		            try (ResultSet rs = ps.getGeneratedKeys()) {
		                if (rs.next()) {
		                    historique.setId(rs.getInt(1));
		                }
		            }

		        } catch (Exception e) {
		            System.err.println("Erreur sauvegarde historique : " + e.getMessage());
		        }

		        return historique;
	}

	@Override
	public List<Historique> findByUtilisateurId(int utilisateurId) {
		 List<Historique> historiques = new ArrayList<>();

	        String sql = """
	            SELECT *
	            FROM historique
	            WHERE utilisateur_id = ?
	            ORDER BY date_historique DESC
	        """;

	        try (
	                Connection connection = DatabaseConnection.getConnection();
	                PreparedStatement ps = connection.prepareStatement(sql)
	        ) {

	            ps.setInt(1, utilisateurId);

	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                    historiques.add(mapperHistorique(rs));
	                }
	            }

	        } catch (Exception e) {
	            System.err.println("Erreur liste historique : " + e.getMessage());
	        }

	        return historiques;
	}
	
	private Historique mapperHistorique(ResultSet rs) throws Exception {

        Historique historique = new Historique();

        historique.setId(rs.getInt("id"));
        historique.setDateHistorique(rs.getDate("date_historique"));
        historique.setAction(rs.getString("action"));
        historique.setUtilisateurId(rs.getInt("utilisateur_id"));
        historique.setObjectifId(rs.getInt("objectif_id"));

        return historique;
    }

}
