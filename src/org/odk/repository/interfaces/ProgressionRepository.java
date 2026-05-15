package org.odk.repository.interfaces;

import java.util.List;

import org.odk.model.Progression;

public interface ProgressionRepository {
	 
    Progression sauvegarder(Progression progression);

    List<Progression> afficher(int objectifId);

    double calculerPourcentage(int objectifId);
    
    int compterActionsReussiesParUtilisateur(int utilisateurId);
}
