package org.odk.repository.interfaces;

import java.util.List;
import org.odk.model.Planning;

public interface PlanningRepository {

    Planning save(Planning planning);

    Planning findById(int id);

    Planning findByObjectifId(int objectifId);

    List<Planning> findByUtilisateurId(int utilisateurId);
}