package org.odk.ui;

import java.util.List;

import org.odk.model.ActionQuotidienne;
import org.odk.model.Planning;
import org.odk.model.Rappel;
import org.odk.model.Utilisateur;
import org.odk.service.ActionQuotidienneService;
import org.odk.service.PlanningService;
import org.odk.service.ProgressionService;
import org.odk.service.RappelService;
import org.odk.util.SaisieHelper;

public class PlanningConsoleView {

    private final PlanningService planningService;
    private final ActionQuotidienneService actionService;
    private final ProgressionService progressionService;
    private final RappelService rappelService;

    public PlanningConsoleView() {
        this.planningService = new PlanningService();
        this.actionService = new ActionQuotidienneService();
        this.progressionService = new ProgressionService();
        this.rappelService = new RappelService();
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
            System.out.println("║ 2. Voir les actions d'un planning   ║");
            System.out.println("║ 3. Marquer une action réalisée      ║");
            System.out.println("║ 4. Voir les rappels d'un planning   ║");
            System.out.println("║ 5. Voir progression d'un objectif   ║");
            System.out.println("║ 0. Retour                           ║");
            System.out.println("╚══════════════════════════════════════╝");

            choix = SaisieHelper.lireChoix("Votre choix : ", 0, 5);

            switch (choix) {
                case 1 -> afficherPlannings(utilisateur);
                case 2 -> afficherActionsPlanning();
                case 3 -> marquerActionRealisee(utilisateur);
                case 4 -> afficherRappelsPlanning();
                case 5 -> afficherProgressionObjectif();
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

        planningService.afficherPlanningsUtilisateur(utilisateur.getId());
    }

    private void afficherActionsPlanning() {

        int planningId = SaisieHelper.lireEntier("ID du planning : ");

        List<ActionQuotidienne> actions = actionService.trouverParPlanning(planningId);

        if (actions == null || actions.isEmpty()) {
            System.out.println("Aucune action trouvée pour ce planning.");
            return;
        }

        System.out.println("\n══════════════════════════════════════");
        System.out.println("        ACTIONS DU PLANNING");
        System.out.println("══════════════════════════════════════");

        for (ActionQuotidienne action : actions) {
            afficherAction(action);
        }
    }

    private void marquerActionRealisee(Utilisateur utilisateur) {

    if (!utilisateurEstValide(utilisateur)) {
        return;
    }

    List<ActionQuotidienne> actions =
            actionService.trouverActionsUtilisateur(
                    utilisateur.getId()
            );

    if (actions == null || actions.isEmpty()) {
        System.out.println("Aucune action disponible.");
        return;
    }

    System.out.println("\n===== MES ACTIONS À RÉALISER =====");

    for (ActionQuotidienne action : actions) {

        System.out.println("--------------------------------------");
        System.out.println("Domaine     : " + action.getTypeObjectif());
        System.out.println("Objectif    : " + action.getNomObjectif());
        System.out.println("ID action   : " + action.getId());
        System.out.println("Action      : " + action.getDescription());
        System.out.println("Date prévue : " + action.getDatePrevue());
        System.out.println("Statut      : " + action.getStatut());
    }

    System.out.println("--------------------------------------");

    int actionId = SaisieHelper.lireEntier("Choisissez l'ID de l'action réalisée : ");

    ActionQuotidienne action =
            actionService.trouverParId(actionId);

    if (action == null) {
        System.out.println("Action introuvable.");
        return;
    }

    if (action.getPlanning() == null || action.getPlanning().getObjectifId() <= 0) {

        System.out.println("Impossible de retrouver l'objectif lié à cette action." );

        return;
    }

    int objectifId = action.getPlanning().getObjectifId();

    String commentaire = SaisieHelper.lireTexte("Commentaire : ");

    actionService.marquerCommeRealisee(
            actionId,
            commentaire,
            utilisateur,
            objectifId
    );
}

    private void afficherRappelsPlanning() {

        int planningId = SaisieHelper.lireEntier("ID du planning : ");

        List<Rappel> rappels = rappelService.listerRappelsPlanning(planningId);

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

    private void afficherProgressionObjectif() {

        int objectifId = SaisieHelper.lireEntier("ID de l'objectif : ");

        progressionService.afficherProgressionObjectif(objectifId);
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
            System.out.println("Utilisateur non connecté.");
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