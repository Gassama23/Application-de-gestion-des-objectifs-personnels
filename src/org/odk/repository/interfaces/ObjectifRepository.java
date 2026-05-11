package org.odk.repository.interfaces;

import java.util.List;

import org.odk.model.Objectif;

public interface ObjectifRepository {
	

    Objectif save(Objectif objectif);

    void update(Objectif objectif);

    void delete(int id);

    Objectif findById(int id);

    List<Objectif> findAll();

    List<Objectif> findByUtilisateurId(int utilisateurId);

    void updateStatut(int objectifId, String statut);

}
