package org.odk.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.odk.DatabaseConnection;
import org.odk.model.ActionQuotidienne;
import org.odk.model.Planning;
import org.odk.repository.interfaces.ActionQuotidienneRepository;

public class ActionQuotidienneRepositoryJdbc implements ActionQuotidienneRepository {

    private final Connection connection;

    public ActionQuotidienneRepositoryJdbc() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void save(ActionQuotidienne action) {

        String sql = "INSERT INTO action_quotidienne (description, date_prevue, date_realisation, statut, commentaire, planning_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, action.getDescription());

            if (action.getDatePrevue() != null) {
                stmt.setDate(2, new java.sql.Date(action.getDatePrevue().getTime()));
            } else {
                stmt.setDate(2, null);
            }

            if (action.getDateRealisation() != null) {
                stmt.setDate(3, new java.sql.Date(action.getDateRealisation().getTime()));
            } else {
                stmt.setDate(3, null);
            }

            stmt.setString(4, action.getStatut());
            stmt.setString(5, action.getCommentaire());
            stmt.setInt(6, action.getPlanning().getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ActionQuotidienne action) {

        String sql = "UPDATE action_quotidienne SET description=?, date_prevue=?, date_realisation=?, statut=?, commentaire=?, planning_id=? WHERE id=?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, action.getDescription());

            if (action.getDatePrevue() != null) {
                stmt.setDate(2, new java.sql.Date(action.getDatePrevue().getTime()));
            } else {
                stmt.setDate(2, null);
            }

            if (action.getDateRealisation() != null) {
                stmt.setDate(3, new java.sql.Date(action.getDateRealisation().getTime()));
            } else {
                stmt.setDate(3, null);
            }

            stmt.setString(4, action.getStatut());
            stmt.setString(5, action.getCommentaire());
            stmt.setInt(6, action.getPlanning().getId());
            stmt.setInt(7, action.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

        String sql = "DELETE FROM action_quotidienne WHERE id=?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ActionQuotidienne findById(int id) {

        String sql = "SELECT * FROM action_quotidienne WHERE id=?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    ActionQuotidienne action = new ActionQuotidienne();

                    action.setId(rs.getInt("id"));
                    action.setDescription(rs.getString("description"));
                    action.setDatePrevue(rs.getDate("date_prevue"));
                    action.setDateRealisation(rs.getDate("date_realisation"));
                    action.setStatut(rs.getString("statut"));
                    action.setCommentaire(rs.getString("commentaire"));

                    Planning planning = new Planning();
                    planning.setId(rs.getInt("planning_id"));

                    action.setPlanning(planning);

                    return action;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<ActionQuotidienne> findAll() {

        String sql = "SELECT * FROM action_quotidienne";
        List<ActionQuotidienne> list = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                ActionQuotidienne action = new ActionQuotidienne();

                action.setId(rs.getInt("id"));
                action.setDescription(rs.getString("description"));
                action.setDatePrevue(rs.getDate("date_prevue"));
                action.setDateRealisation(rs.getDate("date_realisation"));
                action.setStatut(rs.getString("statut"));
                action.setCommentaire(rs.getString("commentaire"));

                Planning planning = new Planning();
                planning.setId(rs.getInt("planning_id"));

                action.setPlanning(planning);

                list.add(action);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<ActionQuotidienne> findByPlanningId(int planningId) {

        String sql = "SELECT * FROM action_quotidienne WHERE planning_id=?";
        List<ActionQuotidienne> list = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, planningId);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    ActionQuotidienne action = new ActionQuotidienne();

                    action.setId(rs.getInt("id"));
                    action.setDescription(rs.getString("description"));
                    action.setDatePrevue(rs.getDate("date_prevue"));
                    action.setDateRealisation(rs.getDate("date_realisation"));
                    action.setStatut(rs.getString("statut"));
                    action.setCommentaire(rs.getString("commentaire"));

                    Planning planning = new Planning();
                    planning.setId(rs.getInt("planning_id"));

                    action.setPlanning(planning);

                    list.add(action);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<ActionQuotidienne> findByStatut(String statut) {

        String sql = "SELECT * FROM action_quotidienne WHERE statut=?";
        List<ActionQuotidienne> list = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, statut);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    ActionQuotidienne action = new ActionQuotidienne();

                    action.setId(rs.getInt("id"));
                    action.setDescription(rs.getString("description"));
                    action.setDatePrevue(rs.getDate("date_prevue"));
                    action.setDateRealisation(rs.getDate("date_realisation"));
                    action.setStatut(rs.getString("statut"));
                    action.setCommentaire(rs.getString("commentaire"));

                    Planning planning = new Planning();
                    planning.setId(rs.getInt("planning_id"));

                    action.setPlanning(planning);

                    list.add(action);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}