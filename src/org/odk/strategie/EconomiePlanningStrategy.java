package org.odk.strategie;

import java.util.Date;

import org.odk.model.AttentesUtilisateur;
import org.odk.model.Objectif;
import org.odk.model.ObjectifEconomie;
import org.odk.model.Planning;

public class EconomiePlanningStrategy implements PlanningStrategy {

    @Override
    public Planning genererPlanning( Objectif objectif, AttentesUtilisateur attentes) {

        Planning planning = new Planning();

        planning.setTitre("Planning Économie : " + objectif.getNom_objectif());

        planning.setDateCreation(new Date());

        planning.setActif(true);

        planning.setObjectifId( objectif.getId());

        System.out.println( "✓ Génération planning économie.");

        System.out.println("Conseil : Épargner chaque semaine.");

        return planning;
    }

	@Override
	public String genererConseil(Objectif objectif, AttentesUtilisateur attentes) {
		
		return "Épargnez régulièrement et évitez les dépenses non essentielles.";
	}

	@Override
	public String genererDescriptionAction(Objectif objectif, AttentesUtilisateur attentes) {
		
		 double montantRestant = attentes.getMontantVise() - attentes.getEpargneActuelle();

	        if (montantRestant < 0) {
	            montantRestant = 0;
	        }

	        double montantParJour = montantRestant / 30;

	        return "Épargner environ "
	                + String.format("%.0f", montantParJour)
	                + " FCFA aujourd’hui.";
	}

	@Override
	public boolean supporte(Objectif objectif) {
		
		return objectif instanceof ObjectifEconomie;
	}
}