package org.odk.ui;

import java.util.List;

import org.odk.model.ActionQuotidienne;
import org.odk.model.Planning;
import org.odk.model.Rappel;
import org.odk.model.Utilisateur;
import org.odk.service.PlanningService;
import org.odk.service.ProgressionService;
import org.odk.util.SaisieHelper;

/**
 * PlanningConsoleView
 *
 * Rôle :
 * - afficher les plannings de l'utilisateur
 * - afficher les actions quotidiennes
 * - permettre de valider une action
 * - afficher les rappels
 */
public class PlanningConsoleView {

    private final PlanningService planningService;
    private final ProgressionService progressionService;

    public PlanningConsoleView() {
        this.planningService = new PlanningService();
        this.progressionService = new ProgressionService();
    }

    public void afficherMenu(Utilisateur utilisateur) {

        if (!utilisateurEstValide(utilisateur)) {
            return;
        }

        int choix;

        do {
            afficherHeader();

            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║             MENU PLANNING           ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║ 1. Voir mes plannings               ║");
            System.out.println("║ 2. Voir mes actions du jour         ║");
            System.out.println("║ 3. Marquer une action réalisée      ║");
            System.out.println("║ 4. Voir les rappels d’un planning   ║");
            System.out.println("║ 0. Retour                           ║");
            System.out.println("╚══════════════════════════════════════╝");

            choix = SaisieHelper.lireChoix("Votre choix : ", 0, 4);

            switch (choix) {
                case 1 -> afficherPlannings(utilisateur);
                case 2 -> afficherActionsDuJour(utilisateur);
                case 3 -> marquerActionRealisee(utilisateur);
                case 4 -> afficherRappelsPlanning();
                case 0 -> System.out.println("Retour...");
                default -> System.out.println("Choix invalide.");
            }

            if (choix != 0) {
                SaisieHelper.pause();
            }

        } while (choix != 0);
    }

    public void afficherPlannings(Utilisateur utilisateur) {

        if (!utilisateurEstValide(utilisateur)) {
            return;
        }

        System.out.println("\n══════════════════════════════════════");
        System.out.println("            MES PLANNINGS");
        System.out.println("══════════════════════════════════════");

        List<Planning> plannings =
                planningService.trouverPlanningsParUtilisateur(
                        utilisateur.getId()
                );

        if (plannings == null || plannings.isEmpty()) {
            System.out.println("Aucun planning disponible.");
            return;
        }

        for (Planning planning : plannings) {
            afficherPlanning(planning);
        }
    }

    public void afficherActionsDuJour(Utilisateur utilisateur) {

        if (!utilisateurEstValide(utilisateur)) {
            return;
        }

        System.out.println("\n══════════════════════════════════════");
        System.out.println("          ACTIONS DU JOUR");
        System.out.println("══════════════════════════════════════");

        List<ActionQuotidienne> actions =
                planningService.trouverActionsDuJour(
                        utilisateur.getId()
                );

        if (actions == null || actions.isEmpty()) {
            System.out.println("Aucune action prévue aujourd'hui.");
            return;
        }

        for (ActionQuotidienne action : actions) {
            afficherAction(action);
        }
    }

    private void marquerActionRealisee(Utilisateur utilisateur) {

        if (!utilisateurEstValide(utilisateur)) {
            return;
        }

        afficherActionsDuJour(utilisateur);

        int actionId =
                SaisieHelper.lireEntier(
                        "\nID de l'action à valider : "
                );

        String commentaire =
                SaisieHelper.lireTexte(
                        "Commentaire : "
                );

        progressionService.marquerActionCommeRealisee(
                actionId,
                commentaire,
                utilisateur.getId()
        );

        System.out.println("✓ Action marquée comme réalisée.");
    }

    private void afficherRappelsPlanning() {

        int planningId =
                SaisieHelper.lireEntier(
                        "ID du planning : "
                );

        List<Rappel> rappels =
                planningService.trouverRappelsParPlanning(
                        planningId
                );

        if (rappels == null || rappels.isEmpty()) {
            System.out.println("Aucun rappel pour ce planning.");
            return;
        }

        System.out.println("\n══════════════════════════════════════");
        System.out.println("        RAPPELS DU PLANNING");
        System.out.println("══════════════════════════════════════");

        for (Rappel rappel : rappels) {
            System.out.println("--------------------------------------");
            System.out.println("ID      : " + rappel.getId());
            System.out.println("Heure   : " + rappel.getHeureRappel());
            System.out.println("Message : " + rappel.getMessage());
            System.out.println("Actif   : " + (rappel.isActif() ? "Oui" : "Non"));
        }
    }

    private void afficherPlanning(Planning planning) {

        System.out.println("\n--------------------------------------");
        System.out.println("ID       : " + planning.getId());
        System.out.println("Titre    : " + planning.getTitre());
        System.out.println("Créé le  : " + planning.getDateCreation());
        System.out.println("Actif    : " + (planning.isActif() ? "Oui" : "Non"));
        System.out.println("--------------------------------------");

        System.out.println(planning.afficherPlanning());
    }

    private void afficherAction(ActionQuotidienne action) {

        System.out.println("--------------------------------------");
        System.out.println("ID              : " + action.getId());
        System.out.println("Description     : " + action.getDescription());
        System.out.println("Date prévue     : " + action.getDatePrevue());
        System.out.println("Statut          : " + action.getStatut());

        if (action.getDateRealisation() != null) {
            System.out.println("Date réalisation: " + action.getDateRealisation());
        }

        if (action.getCommentaire() != null
                && !action.getCommentaire().isBlank()) {
            System.out.println("Commentaire     : " + action.getCommentaire());
        }
    }

    private boolean utilisateurEstValide(Utilisateur utilisateur) {

        if (utilisateur == null || utilisateur.getId() <= 0) {
            System.out.println("✗ Utilisateur non connecté.");
            return false;
        }

        return true;
    }

    private void afficherHeader() {

        System.out.println();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║          ESPACE PLANNING            ║");
        System.out.println("╚══════════════════════════════════════╝");
    }
}