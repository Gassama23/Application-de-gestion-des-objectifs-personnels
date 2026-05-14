package org.odk.strategie;

import java.util.Date;

import org.odk.model.AttentesUtilisateur;
import org.odk.model.Objectif;
import org.odk.model.ObjectifDevPersonnel;
import org.odk.model.Planning;

public class DeveloppementPersonnelPlanningStrategy implements PlanningStrategy {

    @Override
    public Planning genererPlanning(Objectif objectif, AttentesUtilisateur attentes) {

        Planning planning = new Planning();

        planning.setTitre(
                "Planning Développement Personnel : "
                        + objectif.getNom_objectif()
        );

        planning.setDateCreation( new Date());

        planning.setActif(true);

        planning.setObjectifId( objectif.getId());

        System.out.println("✓ Génération planning développement personnel.");

        System.out.println("Conseil : Maintenir une habitude quotidienne.");

        return planning;
    }

	@Override
	public String genererConseil(Objectif objectif, AttentesUtilisateur attentes) {
		
        return "Maintenez une habitude quotidienne et restez discipliné.";

	}

	@Override
	public String genererDescriptionAction(Objectif objectif, AttentesUtilisateur attentes) {
		
        return "Réaliser une petite action positive liée à votre développement personnel aujourd’hui.";

	}

	@Override
	public boolean supporte(Objectif objectif) {
		
		return objectif instanceof ObjectifDevPersonnel;
	}
}