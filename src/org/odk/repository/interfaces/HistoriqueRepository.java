package org.odk.repository.interfaces;

import java.util.List;

import org.odk.model.Historique;

public interface HistoriqueRepository {
	
	 Historique sauvegarder(Historique historique);

	 List<Historique> findByUtilisateurId(int utilisateurId);

}
