package org.odk.service;

import java.time.LocalDate;
import java.util.List;

import org.odk.model.Badge;
import org.odk.model.BadgeUtilisateur;
import org.odk.model.Utilisateur;
import org.odk.repository.interfaces.BadgeUtilisateurRepository;
import org.odk.repository.jdbc.JdbcBadgeUtilisateurRepository;

public class BadgeUtilisateurService {

    private final BadgeUtilisateurRepository badgeUtilisateurRepository;
    private final BadgeService badgeService;
    private final NotificationService notificationService;

    public BadgeUtilisateurService() {
        this.badgeUtilisateurRepository = new JdbcBadgeUtilisateurRepository();
        this.badgeService = new BadgeService();
        this.notificationService = new NotificationService();
    }

    public BadgeUtilisateur attribuerBadge(
            Utilisateur utilisateur,
            Badge badge
    ) {
        if (utilisateur == null || utilisateur.getId() <= 0) {
            System.err.println("Erreur : utilisateur invalide.");
            return null;
        }

        if (badge == null || badge.getId() <= 0) {
            System.err.println("Erreur : badge invalide.");
            return null;
        }

        boolean dejaAttribue =
                badgeUtilisateurRepository.badgeDejaAttribue(
                        utilisateur.getId(),
                        badge.getId()
                );

        if (dejaAttribue) {
            System.out.println("Ce badge est déjà attribué à cet utilisateur.");
            return null;
        }

        BadgeUtilisateur badgeUtilisateur = new BadgeUtilisateur();

        badgeUtilisateur.setUtilisateurId(utilisateur.getId());
        badgeUtilisateur.setBadgeId(badge.getId());
        badgeUtilisateur.setDateObtention(LocalDate.now());

        BadgeUtilisateur sauvegarde =
                badgeUtilisateurRepository.sauvegarder(badgeUtilisateur);

        notificationService.notifierBadge(
                utilisateur,
                badge.getNom()
        );

        return sauvegarde;
    }

    public void verifierEtAttribuerBadge(
            Utilisateur utilisateur,
            int nombreActionsReussies
    ) {
        if (utilisateur == null || utilisateur.getId() <= 0) {
            System.err.println("Erreur : utilisateur invalide.");
            return;
        }

        List<Badge> badges = badgeService.listerBadges();

        for (Badge badge : badges) {

            boolean conditionAtteinte =
                    nombreActionsReussies >= badge.getConditionStreak();

            boolean dejaAttribue =
                    badgeUtilisateurRepository.badgeDejaAttribue(
                            utilisateur.getId(),
                            badge.getId()
                    );

            if (conditionAtteinte && !dejaAttribue) {
                attribuerBadge(utilisateur, badge);

                System.out.println(
                        "Nouveau badge obtenu : " + badge.getNom()
                );
            }
        }
    }

    public boolean badgeDejaAttribue(
            int utilisateurId,
            int badgeId
    ) {
        if (utilisateurId <= 0 || badgeId <= 0) {
            return false;
        }

        return badgeUtilisateurRepository.badgeDejaAttribue(
                utilisateurId,
                badgeId
        );
    }

    public List<BadgeUtilisateur> listerBadgesUtilisateur(
            int utilisateurId
    ) {
        if (utilisateurId <= 0) {
            System.err.println("Erreur : utilisateur invalide.");
            return List.of();
        }

        return badgeUtilisateurRepository.findByUtilisateurId(
                utilisateurId
        );
    }
}