package org.odk.repository.interfaces;

import java.util.List;

import org.odk.model.Rappel;

public interface RappelRepository {
	
	  Rappel sauvegarder(Rappel rappel);

	  List<Rappel> findByPlanningId(int planningId);

	  void activer(int rappelId);

	  void desactiver(int rappelId);

}
