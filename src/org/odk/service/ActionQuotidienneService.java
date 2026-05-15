package org.odk.service;

import java.util.List;

import org.odk.enums.EnumStatutAction;
import org.odk.model.ActionQuotidienne;
import org.odk.model.Utilisateur;
import org.odk.repository.interfaces.ActionQuotidienneRepository;
import org.odk.repository.jdbc.ActionQuotidienneRepositoryJdbc;

public class ActionQuotidienneService {

    private final ActionQuotidienneRepository actionRepository;
    private final NotificationService notificationService;
    private final HistoriqueService historiqueService;
    private final ProgressionService progressionService;
    private final BadgeUtilisateurService badgeUtilisateurService;

    public ActionQuotidienneService() {
        this.actionRepository = new ActionQuotidienneRepositoryJdbc();
        this.notificationService = new NotificationService();
        this.historiqueService = new HistoriqueService();
        this.progressionService = new ProgressionService();
        this.badgeUtilisateurService = new BadgeUtilisateurService();
    }

    public void ajouterAction(ActionQuotidienne action) {
        if (action == null) {
            System.err.println("Erreur : action invalide.");
            return;
        }

        if (action.getDescription() == null || action.getDescription().isBlank()) {
            System.err.println("Erreur : description obligatoire.");
            return;
        }

        actionRepository.save(action);
    }

    public void modifierAction(ActionQuotidienne action) {
        if (action == null || action.getId() <= 0) {
            System.err.println("Erreur : action invalide.");
            return;
        }

        actionRepository.update(action);
    }

    public void supprimerAction(int id) {
        if (id <= 0) {
            System.err.println("Erreur : identifiant invalide.");
            return;
        }

        actionRepository.delete(id);
    }

    public ActionQuotidienne trouverParId(int id) {
        if (id <= 0) {
            System.err.println("Erreur : identifiant invalide.");
            return null;
        }

        return actionRepository.findById(id);
    }

    public List<ActionQuotidienne> trouverToutesLesActions() {
        return actionRepository.findAll();
    }

    public List<ActionQuotidienne> trouverParPlanning(int planningId) {
        if (planningId <= 0) {
            System.err.println("Erreur : planning invalide.");
            return List.of();
        }

        return actionRepository.findByPlanningId(planningId);
    }

    public void marquerCommeRealisee(
            int actionId,
            String commentaire,
            Utilisateur utilisateur,
            int objectifId
    ) {
        if (actionId <= 0) {
            System.err.println("Erreur : action invalide.");
            return;
        }

        if (utilisateur == null || utilisateur.getId() <= 0) {
            System.err.println("Erreur : utilisateur invalide.");
            return;
        }

        if (objectifId <= 0) {
            System.err.println("Erreur : objectif invalide.");
            return;
        }

        ActionQuotidienne action = actionRepository.findById(actionId);

        if (action == null) {
            System.err.println("Erreur : action introuvable.");
            return;
        }

        if (action.getStatut() == EnumStatutAction.TERMINEE) {
            System.out.println("Cette action est déjà réalisée. Impossible de la valider une deuxième fois.");
            return;
        }

        actionRepository.marquerCommeRealisee(
                actionId,
                commentaire
        );

        progressionService.enregistrerProgression(
                objectifId,
                actionId,
                commentaire
        );

        historiqueService.enregistrerHistorique(
                utilisateur.getId(),
                objectifId,
                "Action réalisée : " + commentaire
        );

        notificationService.notifierReussite(
                utilisateur,
                "Bravo ! Vous avez réalisé une action."
        );

        int nombreActionsReussies = progressionService.compterActionsReussies(
                        utilisateur.getId()
                );

        badgeUtilisateurService.verifierEtAttribuerBadge(
                utilisateur,
                nombreActionsReussies
        );
        
        utilisateur.setStreakActuel(
                utilisateur.getStreakActuel() + 1
        );

        if (utilisateur.getStreakActuel()
                > utilisateur.getMeilleurStreak()) {

            utilisateur.setMeilleurStreak(
                    utilisateur.getStreakActuel()
            );
        }

        UserService userService = new UserService();

        userService.mettreAJourStreak(utilisateur);

        System.out.println("✓ Action marquée comme réalisée.");
    }
}