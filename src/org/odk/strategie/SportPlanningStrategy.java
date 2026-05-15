package org.odk.strategie;

import java.util.Date;

import org.odk.model.AttentesUtilisateur;
import org.odk.model.Objectif;
import org.odk.model.ObjectifSport;
import org.odk.model.Planning;

public class SportPlanningStrategy implements PlanningStrategy {

    @Override
    public Planning genererPlanning(Objectif objectif, AttentesUtilisateur attentes
    ) {

        Planning planning = new Planning();

        planning.setTitre(
                "Planning Sport : "
                        + objectif.getNom_objectif()
        );

        planning.setDateCreation( new Date());

        planning.setActif(true);

        planning.setObjectifId( objectif.getId());

        System.out.println( " Génération planning sport."
        );

        System.out.println(
                "Conseil : Faire "
                        + attentes.getFrequenceHebdo()
                        + " séances par semaine."
        );

        return planning;
    }

	@Override
	public String genererConseil(Objectif objectif, AttentesUtilisateur attentes) {
		
        return "Respectez votre fréquence d’entraînement et progressez progressivement.";

	}

	@Override
	public String genererDescriptionAction(Objectif objectif, AttentesUtilisateur attentes) {
		
		 return "Faire une séance sportive de "
	                + attentes.getDureeSeance()
	                + " minutes aujourd’hui.";
	}

	@Override
	public boolean supporte(Objectif objectif) {
		
		return objectif instanceof ObjectifSport;
	}
}