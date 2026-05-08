package org.odk.repository;

import org.odk.DatabaseDouyon;
import org.odk.model.BadgeUtilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BadgeUtilisateurRepository {

    public void ajouter(BadgeUtilisateur b) {

        String sql = "INSERT INTO badge_utilisateur " +
                     "(utilisateur_id, badge_id, date_obtention) " +
                     "VALUES (?, ?, ?)";

        try {

            Connection conn = DatabaseDouyon.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, b.getUtilisateurId());
            ps.setInt(2, b.getBadgeId());
            ps.setDate(3, java.sql.Date.valueOf(b.getDateObtention()));

            ps.executeUpdate();

            System.out.println("✔ Badge ajouté avec succès");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}