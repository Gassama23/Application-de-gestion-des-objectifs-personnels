package org.odk.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.odk.DatabaseConnection;
import org.odk.enums.EnumTypeNotification;
import org.odk.model.Notification;
import org.odk.repository.interfaces.NotificationRepository;

public class JdbcNotificationRepository implements NotificationRepository {

    @Override
    public Notification sauvegarder(
            Notification notification
    ) {

        String sql = """
            INSERT INTO notification(
                titre,
                message,
                date_envoi,
                lu,
                type,
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
                    notification.getTitre()
            );

            ps.setString(
                    2,
                    notification.getMessage()
            );

            ps.setDate(
                    3,
                    new java.sql.Date(
                            notification
                                    .getDateEnvoi()
                                    .getTime()
                    )
            );

            ps.setBoolean(
                    4,
                    notification.isLu()
            );

            ps.setString(
                    5,
                    notification.getType().name()
            );

            ps.setInt(
                    6,
                    notification.getUtilisateur_id()
            );

            ps.executeUpdate();

            try (ResultSet rs =
                         ps.getGeneratedKeys()) {

                if (rs.next()) {

                    notification.setId(
                            rs.getInt(1)
                    );
                }
            }

            System.out.println(
                    "✓ Notification sauvegardée."
            );

        } catch (Exception e) {

            System.err.println(
                    "Erreur sauvegarde notification : "
                            + e.getMessage()
            );
        }

        return notification;
    }

    @Override
    public List<Notification>
    findByUtilisateurId(
            int utilisateurId
    ) {

        List<Notification> notifications =
                new ArrayList<>();

        String sql = """
            SELECT *
            FROM notification
            WHERE utilisateur_id = ?
            ORDER BY date_envoi DESC
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

                    notifications.add(
                            mapperNotification(rs)
                    );
                }
            }

        } catch (Exception e) {

            System.err.println(
                    "Erreur liste notifications : "
                            + e.getMessage()
            );
        }

        return notifications;
    }

    @Override
    public void marquerCommeLue(
            int notificationId
    ) {

        String sql = """
            UPDATE notification
            SET lu = true
            WHERE id = ?
        """;

        try (

                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql)
        ) {

            ps.setInt(1, notificationId);

            ps.executeUpdate();

        } catch (Exception e) {

            System.err.println(
                    "Erreur lecture notification : "
                            + e.getMessage()
            );
        }
    }

    private Notification mapperNotification(
            ResultSet rs
    ) throws Exception {

        Notification notification = new Notification();

        notification.setId(
                rs.getInt("id")
        );

        notification.setTitre(
                rs.getString("titre")
        );

        notification.setMessage(
                rs.getString("message")
        );

        notification.setDateEnvoi(
                rs.getDate("date_envoi")
        );

        notification.setLu(
                rs.getBoolean("lu")
        );

        notification.setType(
                EnumTypeNotification.valueOf(
                        rs.getString("type")
                )
        );

        notification.setUtilisateur_id(
                rs.getInt("utilisateur_id")
        );

        return notification;
    }
}