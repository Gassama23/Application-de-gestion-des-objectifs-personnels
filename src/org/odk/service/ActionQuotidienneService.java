package org.odk.service;

import java.util.List;

import org.odk.model.ActionQuotidienne;
import org.odk.repository.interfaces.ActionQuotidienneRepository;
import org.odk.repository.jdbc.ActionQuotidienneRepositoryJdbc;

/**
 * Service métier des actions quotidiennes.
 *
 * Rôle :
 * - gérer la logique métier
 * - appeler le repository JDBC
 * - valider les actions
 */
public class ActionQuotidienneService {

    private final ActionQuotidienneRepository actionRepository;

    public ActionQuotidienneService() {

        this.actionRepository =
                new ActionQuotidienneRepositoryJdbc();
    }

    
    public void ajouterAction(ActionQuotidienne action) {

        if (action == null) {

            System.err.println(
                    "Erreur : action invalide."
            );

            return;
        }

        if (action.getDescription() == null
                || action.getDescription().isBlank()) {

            System.err.println(
                    "Erreur : description obligatoire."
            );

            return;
        }

        actionRepository.save(action);
    }

    
    public void modifierAction(ActionQuotidienne action) {

        if (action == null || action.getId() <= 0) {

            System.err.println(
                    "Erreur : action invalide."
            );

            return;
        }

        actionRepository.update(action);
    }

   
    public void supprimerAction(int id) {

        if (id <= 0) {

            System.err.println(
                    "Erreur : identifiant invalide."
            );

            return;
        }

        actionRepository.delete(id);
    }

    
    public ActionQuotidienne trouverParId(int id) {

        if (id <= 0) {

            System.err.println(
                    "Erreur : identifiant invalide."
            );

            return null;
        }

        return actionRepository.findById(id);
    }

    
    public List<ActionQuotidienne> trouverToutesLesActions() {

        return actionRepository.findAll();
    }

    
    public List<ActionQuotidienne> trouverParPlanning(
            int planningId
    ) {

        if (planningId <= 0) {

            System.err.println(
                    "Erreur : planning invalide."
            );

            return null;
        }

        return actionRepository.findByPlanningId(
                planningId
        );
    }

    
    public void marquerCommeRealisee(
            int actionId,
            String commentaire
    ) {

        if (actionId <= 0) {

            System.err.println(
                    "Erreur : action invalide."
            );

            return;
        }

        actionRepository.marquerCommeRealisee(
                actionId,
                commentaire
        );

        /*
         * Ici plus tard :
         * - calcul progression
         * - mise à jour streak
         * - badge automatique
         * - notification
         */
    }
}