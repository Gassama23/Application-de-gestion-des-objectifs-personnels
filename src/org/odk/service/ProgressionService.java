package org.odk.service;

import java.util.List;

import org.odk.model.Progression;
import org.odk.repository.interfaces.ProgressionRepository;

public class ProgressionService {
	 private ProgressionRepository repository;

	    // Injection dépendance
	    public ProgressionService(
	            ProgressionRepository repository
	    ) {

	        this.repository = repository;
	    }

	    // AJOUT
	    public void ajouterProgression(
	            Progression progression
	    ) {

	        repository.sauvegarder(progression);
	    }

	    // AFFICHAGE
	    public List<Progression> afficherProgressions() {

	        return repository.afficher();
	    }

	   

}
