package org.odk.strategie;

import java.util.Date;

import org.odk.model.AttentesUtilisateur;
import org.odk.model.Objectif;
import org.odk.model.Planning;

public class ApprentissagePlanningStrategy
        implements PlanningStrategy {

    @Override
    public Planning genererPlanning(
            Objectif objectif,
            AttentesUtilisateur attentes
    ) {

        Planning planning =
                new Planning();

        planning.setTitre(
                "Planning Apprentissage : "
                        + objectif.getNom_objectif()
        );

        planning.setDateCreation(
                new Date()
        );

        planning.setActif(true);

        planning.setObjectifId(
                objectif.getId()
        );

        System.out.println(
                "✓ Génération planning apprentissage."
        );

        System.out.println(
                "Conseil : Étudier "
                        + attentes.getTempsQuotidien()
                        + " minutes par jour."
        );

        return planning;
    }
}