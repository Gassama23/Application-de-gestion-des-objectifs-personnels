package org.odk.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.odk.DatabaseConnection;
import org.odk.enums.EnumRole;
import org.odk.model.Admin;
import org.odk.model.User;
import org.odk.model.Utilisateur;

public class UserRepository {
    
    /**
     * Inscrire un nouvel utilisateur dans la base de données
     * @param user L'utilisateur à inscrire
     * @return true si l'inscription réussit, false sinon
     */
    public boolean inscrireUtilisateur(User user) {
        String sql = "INSERT INTO  (nom, prenom, email, mot_de_passe, role, date_inscription) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, user.getNom());
            stmt.setString(2, user.getPrenom());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getMotDePasse());
            stmt.setString(5, user.getRole().name());
            stmt.setTimestamp(6, new java.sql.Timestamp(user.getDateInscription().getTime()));
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                // Récupérer l'ID généré
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                }
                return true;
            }
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'inscription : " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * Connecter un utilisateur (vérifier email et mot de passe)
     * @param email L'email de l'utilisateur
     * @param motDePasse Le mot de passe de l'utilisateur
     * @return L'utilisateur connecté ou null si échec
     */
    public User connecterUtilisateur(String email, String motDePasse) {
        String sql = "SELECT * FROM users WHERE email = ? AND mot_de_passe = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            stmt.setString(2, motDePasse);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                // Créer l'objet utilisateur approprié selon le rôle
                EnumRole role = EnumRole.valueOf(rs.getString("role"));
                User user;
                
                if (role == EnumRole.ADMIN) {
                    user = new Admin();
                } else {
                    user = new Utilisateur();
                }
                
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setEmail(rs.getString("email"));
                user.setMotDePasse(rs.getString("mot_de_passe"));
                user.setRole(role);
                user.setDateInscription(new Date(rs.getTimestamp("date_inscription").getTime()));
                
                return user;
            }
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion : " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * Vérifier si un email existe déjà
     * @param email L'email à vérifier
     * @return true si l'email existe, false sinon
     */
    public boolean emailExiste(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de l'email : " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
}
