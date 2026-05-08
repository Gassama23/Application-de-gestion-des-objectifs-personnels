package org.odk.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.odk.DatabaseConnection;
import org.odk.model.Progression;
import org.odk.repository.interfaces.ProgressionRepository;

public class JdbcProgressionRepository implements ProgressionRepository {

	@Override
	public Progression sauvegarder(Progression progress) {
		String sql = "INSERT INTO progression (date_progression, etat, commentaire, objectif_id, action_quotidienne_id) VALUES (?, ?, ?, ?, ?)";
		try(
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
		){
			// Convertir java.util.Date en java.sql.Date
			java.sql.Date sqlDate = new java.sql.Date(progress.getDate().getTime());
			// Remplir les paramètres de la requête
			ps.setDate(1, sqlDate);
			ps.setBoolean(2, progress.isEtat());
			ps.setString(3, progress.getCommentaire());
			ps.setLong(4, progress.getObjectif_id());
			ps.setLong(5, progress.getAction_quotidienne_id());

			ps.executeUpdate();

			System.out.println("Progression sauvegardée avec succès !");

		} catch( Exception e) {
			System.err.println("Erreur lors de la sauvegarde de la progression : " + e.getMessage());
		}
		return progress;
	}
	
	@Override
	public List<Progression> afficher() {

	    List<Progression> progressions =
	            new ArrayList<>();

	    String sql = "SELECT * FROM progression";

	    try(

	        Connection connection =
	                DatabaseConnection.getConnection();

	        PreparedStatement ps =
	                connection.prepareStatement(sql);

	        ResultSet rs =
	                ps.executeQuery();

	    ) {

	        while(rs.next()) {

	            int id = rs.getInt("id");

	            java.sql.Date sqlDate =
	                    rs.getDate("date_progression");

	            boolean etat =
	                    rs.getBoolean("etat");

	            String commentaire =
	                    rs.getString("commentaire");

	            int objectifId =
	                    rs.getInt("objectif_id");

	            int actionId =
	                    rs.getInt(
	                            "action_quotidienne_id"
	                    );

	            // Création objet
	            Progression progression =
	                    new Progression(
	                            sqlDate,
	                            etat,
	                            commentaire
	                    );

	            progression.setObjectif_id(
	                    objectifId
	            );

	            progression.setAction_quotidienne_id(
	                    actionId
	            );

	            progressions.add(progression);

	            // Affichage
	            System.out.println(
	                    "ID : " + id
	            );

	            System.out.println(
	                    "Date : " + sqlDate
	            );

	            System.out.println(
	                    "Etat : " + etat
	            );

	            System.out.println(
	                    "Commentaire : "
	                    + commentaire
	            );

	            System.out.println(
	                    "Objectif ID : "
	                    + objectifId
	            );

	            System.out.println(
	                    "Action ID : "
	                    + actionId
	            );

	            System.out.println(
	                    "----------------"
	            );
	        }

	    } catch(Exception e) {

	        System.out.println(
	                e.getMessage()
	        );
	    }

	    return progressions;
	}

}
