package org.odk.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

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

}
