package org.odk.strategie;

import java.util.Date;

import org.odk.model.AttentesUtilisateur;
import org.odk.model.Objectif;
import org.odk.model.ObjectifApprentissage;
import org.odk.model.Planning;

public class ApprentissagePlanningStrategy implements PlanningStrategy {

    @Override
    public Planning genererPlanning(Objectif objectif, AttentesUtilisateur attentes) {

    	Planning planning = new Planning();

        planning.setTitre(
                "Planning Apprentissage : "
                        + objectif.getNom_objectif()
        );

        planning.setDateCreation(new Date());
        planning.setActif(true);
        planning.setObjectifId(objectif.getId());

        return planning;
    }

	@Override
	public String genererConseil(Objectif objectif, AttentesUtilisateur attentes) {
		
        return "Étudiez régulièrement et pratiquez un peu chaque jour.";

	}

	@Override
	public String genererDescriptionAction(Objectif objectif, AttentesUtilisateur attentes) {
		
		ObjectifApprentissage apprentissage =
                (ObjectifApprentissage) objectif;

        return "Étudier "
                + objectif.getNom_objectif()
                + " pendant "
                + apprentissage.getDuree_etudeParJour()
                + " minutes aujourd’hui.";
	}

	@Override
	public boolean supporte(Objectif objectif) {
		
		return objectif instanceof ObjectifApprentissage;
	}
}