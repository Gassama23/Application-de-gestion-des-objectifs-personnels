package org.odk.service;

import java.util.Date;
import java.util.List;

import org.odk.enums.EnumTypeNotification;
import org.odk.model.Notification;
import org.odk.model.Utilisateur;
import org.odk.repository.interfaces.NotificationRepository;
import org.odk.repository.jdbc.JdbcNotificationRepository;


public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService() {

        this.notificationRepository =
                new JdbcNotificationRepository();
    }

    public Notification creerNotification(
            Utilisateur utilisateur,
            String titre,
            String message,
            EnumTypeNotification type
    ) {

        if (utilisateur == null
                || utilisateur.getId() <= 0) {

            System.err.println(
                    "Erreur : utilisateur invalide."
            );

            return null;
        }

        Notification notification =
                new Notification();

        notification.setUtilisateur_id(
                utilisateur.getId()
        );

        notification.setTitre(titre);

        notification.setMessage(message);

        notification.setDateEnvoi(
                new Date()
        );

        notification.setLu(false);

        notification.setType(type);

        return notificationRepository
                .sauvegarder(notification);
    }

    public void notifierBadge(
            Utilisateur utilisateur,
            String nomBadge
    ) {

        creerNotification(
                utilisateur,
                "Nouveau badge obtenu",
                "Félicitations ! Vous avez obtenu le badge : "
                        + nomBadge,
                EnumTypeNotification.BADGE
        );
    }

    public void notifierRappel(
            Utilisateur utilisateur,
            String messageRappel
    ) {

        creerNotification(
                utilisateur,
                "Rappel",
                messageRappel,
                EnumTypeNotification.RAPPEL
        );
    }

    public void notifierReussite(
            Utilisateur utilisateur,
            String messageReussite
    ) {

        creerNotification(
                utilisateur,
                "Objectif réussi",
                messageReussite,
                EnumTypeNotification.REUSSITE
        );
    }

    public List<Notification>
    listerNotificationsUtilisateur(
            int utilisateurId
    ) {

        if (utilisateurId <= 0) {

            System.err.println(
                    "Erreur : utilisateur invalide."
            );

            return List.of();
        }

        return notificationRepository
                .findByUtilisateurId(
                        utilisateurId
                );
    }

    public void marquerCommeLue(
            int notificationId
    ) {

        if (notificationId <= 0) {

            System.err.println(
                    "Erreur : notification invalide."
            );

            return;
        }

        notificationRepository
                .marquerCommeLue(notificationId);
    }
}