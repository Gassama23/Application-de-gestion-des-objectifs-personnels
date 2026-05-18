package org.odk.ui;

import java.util.List;

import org.odk.model.Notification;
import org.odk.model.Utilisateur;
import org.odk.service.NotificationService;
import org.odk.util.SaisieHelper;

public class NotificationConsoleView {

    private final NotificationService notificationService;

    public NotificationConsoleView() {
        this.notificationService = new NotificationService();
    }

    public void afficherNotifications(Utilisateur utilisateur) {

        if (utilisateur == null || utilisateur.getId() <= 0) {
            System.out.println(" Utilisateur non connecté.");
            return;
        }

        List<Notification> notifications =
                notificationService.listerNotificationsUtilisateur(
                        utilisateur.getId()
                );

        if (notifications == null || notifications.isEmpty()) {
            System.out.println("Aucune notification disponible.");
            return;
        }

        System.out.println("\n===== MES NOTIFICATIONS =====");

        for (Notification notification : notifications) {

            System.out.println("--------------------------------");
            System.out.println("ID      : " + notification.getId());
            System.out.println("Titre   : " + notification.getTitre());
            System.out.println("Message : " + notification.getMessage());
            System.out.println("Type    : " + notification.getType());
            System.out.println("Date    : " + notification.getDateEnvoi());
            System.out.println("Lu      : " + (notification.isLu() ? "Oui" : "Non"));
        }

        System.out.println("--------------------------------");

        int choix = SaisieHelper.lireChoix(
                "Voulez-vous marquer une notification comme lue ? 1.Oui 0.Non : ",
                0,
                1
        );

        if (choix == 1) {
            int notificationId =
                    SaisieHelper.lireEntier("ID notification : ");

            notificationService.marquerCommeLue(notificationId);
        }
    }
}