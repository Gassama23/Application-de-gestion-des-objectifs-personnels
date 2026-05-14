package org.odk.service;

import java.util.Date;
import java.util.List;

import org.odk.enums.EnumStatutAction;
import org.odk.model.ActionQuotidienne;
import org.odk.model.AttentesUtilisateur;
import org.odk.model.Objectif;
import org.odk.model.Planning;
import org.odk.repository.interfaces.PlanningRepository;
import org.odk.repository.jdbc.PlanningRepositoryJdbc;
import org.odk.strategie.PlanningStrategy;
import org.odk.strategie.PlanningStrategyFactory;

public class PlanningService {

    private final PlanningRepository planningRepository;
    private final ActionQuotidienneService actionService;

    public PlanningService() {
        this.planningRepository = new PlanningRepositoryJdbc();
        this.actionService = new ActionQuotidienneService();
    }

    public Planning genererPlanning(Objectif objectif, AttentesUtilisateur attentes) {

        if (objectif == null) {
            System.err.println("Erreur : objectif invalide.");
            return null;
        }

        if (attentes == null) {
            System.err.println("Erreur : attentes utilisateur invalides.");
            return null;
        }

        PlanningStrategy strategy = PlanningStrategyFactory.getStrategy(objectif);

        Planning planning = strategy.genererPlanning(objectif, attentes);

        Planning planningSauvegarde = planningRepository.save(planning);

        if (planningSauvegarde == null || planningSauvegarde.getId() <= 0) {
            System.err.println("Erreur : planning non sauvegardé.");
            return null;
        }

        genererActionQuotidienne( planningSauvegarde, objectif, attentes, strategy);

        System.out.println("💡 Action quotidienne : " + strategy.genererConseil(objectif, attentes));

        return planningSauvegarde;
    }

    private void genererActionQuotidienne(Planning planning, Objectif objectif, AttentesUtilisateur attentes, PlanningStrategy strategy) {

        String description = strategy.genererDescriptionAction(objectif, attentes);

        ActionQuotidienne action = new ActionQuotidienne();

        action.setDescription(description);
        action.setDatePrevue(new Date());
        action.setStatut(EnumStatutAction.A_FAIRE);

        Planning planningAction = new Planning();
        planningAction.setId(planning.getId());

        action.setPlanning(planningAction);

        actionService.ajouterAction(action);

        System.out.println("✓ Action quotidienne générée.");
    }

    public Planning trouverPlanningObjectif(int objectifId) {

        if (objectifId <= 0) {
            System.err.println("Erreur : objectif invalide.");
            return null;
        }

        return planningRepository.findByObjectifId(objectifId);
    }

    public void afficherPlanningsUtilisateur(int utilisateurId) {

        if (utilisateurId <= 0) {
            System.err.println("Erreur : utilisateur invalide.");
            return;
        }

        List<Planning> plannings = planningRepository.findByUtilisateurId(utilisateurId);

        if (plannings == null || plannings.isEmpty()) {
            System.out.println("Aucun planning trouvé.");
            return;
        }

        System.out.println("\n===== MES PLANNINGS =====");

        for (Planning planning : plannings) {
            System.out.println("ID : " + planning.getId());
            System.out.println("Titre : " + planning.getTitre());
            System.out.println("Date création : " + planning.getDateCreation());
            System.out.println("Actif : " + (planning.isActif() ? "Oui" : "Non"));
            System.out.println("--------------------------");
        }
    }

    public void changerEtatPlanning(Planning planning, boolean actif) {

        if (planning == null || planning.getId() <= 0) {
            System.err.println("Planning invalide.");
            return;
        }

        planning.setActif(actif);

        System.out.println("✓ État planning mis à jour.");
    }
}