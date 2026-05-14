package org.odk.service;

import java.util.List;

import org.odk.model.Badge;
import org.odk.repository.interfaces.BadgeRepository;
import org.odk.repository.jdbc.JdbcBadgeRepository;


public class BadgeService {

    private final BadgeRepository badgeRepository;

    public BadgeService() {
        this.badgeRepository = new JdbcBadgeRepository();
    }

    public Badge creerBadge(Badge badge) {

        if (badge == null) {
            System.err.println("Erreur : badge invalide.");
            return null;
        }

        if (badge.getNom() == null || badge.getNom().isBlank()) {
            System.err.println("Erreur : nom du badge obligatoire.");
            return null;
        }

        if (badge.getConditionStreak() <= 0) {
            System.err.println("Erreur : condition de succès invalide.");
            return null;
        }

        return badgeRepository.sauvergarder(badge);
    }

    public Badge trouverParId(int id) {

        if (id <= 0) {
            System.err.println("Erreur : ID badge invalide.");
            return null;
        }

        return badgeRepository.findById(id);
    }

    public List<Badge> listerBadges() {
        return badgeRepository.findAll();
    }

    public void afficherBadges() {

        List<Badge> badges = listerBadges();

        if (badges == null || badges.isEmpty()) {
            System.out.println("Aucun badge disponible.");
            return;
        }

        System.out.println("\n===== LISTE DES BADGES =====");

        for (Badge badge : badges) {
            System.out.println("----------------------------");
            System.out.println("ID          : " + badge.getId());
            System.out.println("Nom         : " + badge.getNom());
            System.out.println("Description : " + badge.getDescription());
            System.out.println("Condition   : " + badge.getConditionStreak() + " action(s)");
        }

        System.out.println("----------------------------");
    }
}