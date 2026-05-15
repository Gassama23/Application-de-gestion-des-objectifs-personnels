package org.odk.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.odk.DatabaseConnection;
import org.odk.model.Rappel;
import org.odk.repository.interfaces.RappelRepository;

public class JdbcRappelRepository implements RappelRepository {

    @Override
    public Rappel sauvegarder(Rappel rappel) {

        String sql = """
            INSERT INTO rappel(
                heure_rappel,
                message,
                actif,
                planning_id
            )
            VALUES (?, ?, ?, ?)
        """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {

            ps.setTime(
                    1,
                    Time.valueOf(
                            rappel.getHeureRappel()
                    )
            );
            ps.setString(2, rappel.getMessage());
            ps.setBoolean(3, rappel.isActif());
            ps.setInt(4, rappel.getPlanningId());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    rappel.setId(rs.getInt(1));
                }
            }

            System.out.println("✓ Rappel sauvegardé.");

        } catch (Exception e) {
            System.err.println("Erreur sauvegarde rappel : " + e.getMessage());
        }

        return rappel;
    }

    @Override
    public List<Rappel> findByPlanningId(int planningId) {

        List<Rappel> rappels = new ArrayList<>();

        String sql = """
            SELECT *
            FROM rappel
            WHERE planning_id = ?
        """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, planningId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    rappels.add(mapperRappel(rs));
                }
            }

        } catch (Exception e) {
            System.err.println("Erreur liste rappels : " + e.getMessage());
        }

        return rappels;
    }

    @Override
    public void activer(int rappelId) {
        changerEtat(rappelId, true);
    }

    @Override
    public void desactiver(int rappelId) {
        changerEtat(rappelId, false);
    }

    private void changerEtat(int rappelId, boolean actif) {

        String sql = """
            UPDATE rappel
            SET actif = ?
            WHERE id = ?
        """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setBoolean(1, actif);
            ps.setInt(2, rappelId);

            ps.executeUpdate();

        } catch (Exception e) {
            System.err.println("Erreur changement état rappel : " + e.getMessage());
        }
    }

    private Rappel mapperRappel(ResultSet rs) throws Exception {

        Rappel rappel = new Rappel();

        rappel.setId(rs.getInt("id"));
        rappel.setHeureRappel(
                rs.getTime("heure_rappel")
                        .toLocalTime()
        );        rappel.setMessage(rs.getString("message"));
        rappel.setActif(rs.getBoolean("actif"));
        rappel.setPlanningId(
                rs.getInt("planning_id")
        );
        return rappel;
    }
    
    
}