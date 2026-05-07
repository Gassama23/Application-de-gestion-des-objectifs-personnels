package org.odk.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.odk.DatabaseConnection;
import org.odk.model.Planning;
import org.odk.repository.interfaces.PlanningRepository;

public class PlanningRepositoryJdbc implements PlanningRepository {

    private final Connection connection;

    public PlanningRepositoryJdbc() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void save(Planning planning) {
        String sql = "INSERT INTO planning (titre, date_creation, actif, objectif_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, planning.getTitre());

            if (planning.getDateCreation() != null) {
                stmt.setDate(2, new java.sql.Date(planning.getDateCreation().getTime()));
            } else {
                stmt.setDate(2, null);
            }

            stmt.setBoolean(3, planning.isActif());
            stmt.setInt(4, planning.getObjectifId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Planning planning) {
        String sql = "UPDATE planning SET titre = ?, date_creation = ?, actif = ?, objectif_id = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, planning.getTitre());

            if (planning.getDateCreation() != null) {
                stmt.setDate(2, new java.sql.Date(planning.getDateCreation().getTime()));
            } else {
                stmt.setDate(2, null);
            }

            stmt.setBoolean(3, planning.isActif());
            stmt.setInt(4, planning.getObjectifId());
            stmt.setInt(5, planning.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM planning WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Planning findById(int id) {
        String sql = "SELECT * FROM planning WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Planning planning = new Planning();
                    planning.setId(rs.getInt("id"));
                    planning.setTitre(rs.getString("titre"));
                    planning.setDateCreation(rs.getDate("date_creation"));
                    planning.setActif(rs.getBoolean("actif"));
                    planning.setObjectifId(rs.getInt("objectif_id"));
                    return planning;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Planning> findAll() {
        String sql = "SELECT * FROM planning";
        List<Planning> liste = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Planning planning = new Planning();
                planning.setId(rs.getInt("id"));
                planning.setTitre(rs.getString("titre"));
                planning.setDateCreation(rs.getDate("date_creation"));
                planning.setActif(rs.getBoolean("actif"));
                planning.setObjectifId(rs.getInt("objectif_id"));
                liste.add(planning);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;
    }

    @Override
    public Planning findByObjectifId(int objectifId) {
        String sql = "SELECT * FROM planning WHERE objectif_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, objectifId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Planning planning = new Planning();
                    planning.setId(rs.getInt("id"));
                    planning.setTitre(rs.getString("titre"));
                    planning.setDateCreation(rs.getDate("date_creation"));
                    planning.setActif(rs.getBoolean("actif"));
                    planning.setObjectifId(rs.getInt("objectif_id"));
                    return planning;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}