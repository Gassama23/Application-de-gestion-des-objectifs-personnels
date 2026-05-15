package org.odk.ui;

import java.util.List;

import org.odk.model.Badge;
import org.odk.model.BadgeUtilisateur;
import org.odk.model.Utilisateur;
import org.odk.service.BadgeService;
import org.odk.service.BadgeUtilisateurService;

public class BadgeConsoleView {

    private final BadgeUtilisateurService badgeUtilisateurService;
    private final BadgeService badgeService;

    public BadgeConsoleView() {
        this.badgeUtilisateurService = new BadgeUtilisateurService();
        this.badgeService = new BadgeService();
    }

    public void afficherBadgesUtilisateur(Utilisateur utilisateur) {

        if (utilisateur == null || utilisateur.getId() <= 0) {
            System.out.println("Utilisateur non connecté.");
            return;
        }

        List<BadgeUtilisateur> badgesUtilisateur =
                badgeUtilisateurService.listerBadgesUtilisateur(utilisateur.getId());

        if (badgesUtilisateur == null || badgesUtilisateur.isEmpty()) {
            System.out.println("\nAucun badge obtenu.");
            return;
        }

        System.out.println("\n===== MES BADGES =====");

        for (BadgeUtilisateur badgeUtilisateur : badgesUtilisateur) {

            Badge badge =
                    badgeService.trouverParId(
                            badgeUtilisateur.getBadgeId()
                    );

            System.out.println("--------------------------");

            if (badge != null) {
                System.out.println("Badge : " + badge.getNom());
                System.out.println("Description : " + badge.getDescription());
            } else {
                System.out.println("Badge ID : " + badgeUtilisateur.getBadgeId());
            }

            System.out.println("Date obtention : " + badgeUtilisateur.getDateObtention());
        }

        System.out.println("--------------------------");
    }
}