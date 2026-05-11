package org.odk.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import org.odk.DatabaseConnection;
import org.odk.enums.EnumRole;
import org.odk.model.Admin;
import org.odk.model.User;
import org.odk.model.Utilisateur;
import org.odk.repository.interfaces.UserRepository;

public class JdbcRepositoryUser implements UserRepository{

	@Override
	public User sauvegarder(User user) {
		String sql = """
                INSERT INTO utilisateur(
                    nom,
                    prenom,
                    email,
                    mot_de_passe,
                    role,
                    streak_actuel,
                    meilleur_streak,
                    date_inscription
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {

            ps.setString(1, user.getNom());
            ps.setString(2, user.getPrenom());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getMotDePasse());
            ps.setString(5, user.getRole().name());

            /*
             * Seul Utilisateur possède streakActuel et meilleurStreak.
             * Admin n'a pas besoin de ces valeurs, donc on met 0.
             */
            if (user instanceof Utilisateur utilisateur) {
                ps.setInt(6, utilisateur.getStreakActuel());
                ps.setInt(7, utilisateur.getMeilleurStreak());
            } else {
                ps.setInt(6, 0);
                ps.setInt(7, 0);
            }

            ps.setDate(8, Date.valueOf(user.getDateInscription()));

            ps.executeUpdate();

            /*
             * Récupérer l'id généré automatiquement par MySQL.
             */
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur sauvegarde utilisateur : " + e.getMessage());
        }

        return user;
	}

	@Override
	public User trouverParEmail(String email) {
		String sql = "SELECT * FROM utilisateur WHERE email = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertirResultSetEnUser(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur recherche utilisateur par email : " + e.getMessage());
        }

        return null;
	}

	@Override
	public boolean emailExiste(String email) {
		 String sql = "SELECT id FROM utilisateur WHERE email = ?";

	        try (
	                Connection conn = DatabaseConnection.getConnection();
	                PreparedStatement ps = conn.prepareStatement(sql)
	        ) {

	            ps.setString(1, email);

	            try (ResultSet rs = ps.executeQuery()) {
	                return rs.next();
	            }

	        } catch (SQLException e) {
	            System.out.println("Erreur vérification email : " + e.getMessage());
	            return false;
	        }
	}
	
	
private User convertirResultSetEnUser(ResultSet rs) throws SQLException {

	        String roleTexte = rs.getString("role");
	        EnumRole role = EnumRole.valueOf(roleTexte);

	        User user;

	        if (role == EnumRole.ADMIN) {
	            user = new Admin(
	                    rs.getString("nom"),
	                    rs.getString("prenom"),
	                    rs.getString("email"),
	                    rs.getString("mot_de_passe")
	            );
	        } else {
	            Utilisateur utilisateur = new Utilisateur(
	                    rs.getString("nom"),
	                    rs.getString("prenom"),
	                    rs.getString("email"),
	                    rs.getString("mot_de_passe")
	            );

	            utilisateur.setStreakActuel(rs.getInt("streak_actuel"));
	            utilisateur.setMeilleurStreak(rs.getInt("meilleur_streak"));

	            user = utilisateur;
	        }

	        user.setId(rs.getInt("id"));
	        user.setDateInscription(rs.getDate("date_inscription").toLocalDate());

	        return user;
	    }

	
}
