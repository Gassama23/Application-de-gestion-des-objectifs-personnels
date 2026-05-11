package org.odk.service;

import java.util.Date;
import java.util.List;

import org.odk.model.Progression;
import org.odk.repository.interfaces.ProgressionRepository;
import org.odk.repository.jdbc.JdbcProgressionRepository;

/**
 * Service métier pour gérer la progression.
 *
 * Rôle :
 * - créer une progression lorsqu'une action est réalisée
 * - afficher l'historique de progression d'un objectif
 * - calculer le pourcentage de progression
 */
public class ProgressionService {

    private final ProgressionRepository progressionRepository;

    public ProgressionService() {
        this.progressionRepository = new JdbcProgressionRepository();
    }

    /**
     * Enregistrer une progression positive.
     */
    public Progression enregistrerProgression(
            int objectifId,
            int actionQuotidienneId,
            String commentaire
    ) {
        if (objectifId <= 0) {
            System.err.println("Erreur : objectif invalide.");
            return null;
        }

        if (actionQuotidienneId <= 0) {
            System.err.println("Erreur : action quotidienne invalide.");
            return null;
        }

        Progression progression = new Progression(
                new Date(),
                true,
                commentaire
        );

        progression.setObjectif_id(objectifId);
        progression.setAction_quotidienne_id(actionQuotidienneId);

        return progressionRepository.sauvegarder(progression);
    }

    /**
     * Récupérer les progressions d'un objectif.
     */
    public List<Progression> trouverProgressionsParObjectif(int objectifId) {
        if (objectifId <= 0) {
            System.err.println("Erreur : objectif invalide.");
            return List.of();
        }

        return progressionRepository.afficher(objectifId);
    }

    /**
     * Calculer le pourcentage de progression d'un objectif.
     */
    public double calculerPourcentage(int objectifId) {
        if (objectifId <= 0) {
            System.err.println("Erreur : objectif invalide.");
            return 0;
        }

        return progressionRepository.calculerPourcentage(objectifId);
    }

    /**
     * Afficher la progression d'un objectif.
     */
    public void afficherProgressionObjectif(int objectifId) {
        double pourcentage = calculerPourcentage(objectifId);

        System.out.println("\n===== PROGRESSION OBJECTIF =====");
        System.out.println("Objectif ID : " + objectifId);
        System.out.println("Progression : " + String.format("%.2f", pourcentage) + " %");
        System.out.println("================================");
    }

    /**
     * Afficher l'historique des progressions d'un objectif.
     */
    public void afficherHistoriqueObjectif(int objectifId) {
        List<Progression> progressions = trouverProgressionsParObjectif(objectifId);

        if (progressions.isEmpty()) {
            System.out.println("Aucune progression trouvée pour cet objectif.");
            return;
        }

        System.out.println("\n===== HISTORIQUE PROGRESSION =====");

        for (Progression progression : progressions) {
            System.out.println("----------------------------------");
            System.out.println("ID : " + progression.getId());
            System.out.println("Date : " + progression.getDate_progression()
            );
            System.out.println("État : " + (progression.isEtat() ? "Réalisé" : "Non réalisé"));
            System.out.println("Commentaire : " + progression.getCommentaire());
            System.out.println("Action ID : " + progression.getAction_quotidienne_id());
        }

        System.out.println("----------------------------------");
    }
    
    public int compterActionsReussies(int utilisateurId) {

        if (utilisateurId <= 0) {
            System.err.println("Erreur : utilisateur invalide.");
            return 0;
        }

        return progressionRepository.compterActionsReussiesParUtilisateur(utilisateurId);
    }
}