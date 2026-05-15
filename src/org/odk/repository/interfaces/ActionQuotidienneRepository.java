package org.odk.repository.interfaces;

import java.util.List;

import org.odk.enums.EnumStatutAction;
import org.odk.model.ActionQuotidienne;

public interface ActionQuotidienneRepository {

    void save(ActionQuotidienne action);

    void update(ActionQuotidienne action);

    void delete(int id);

    ActionQuotidienne findById(int id);

    List<ActionQuotidienne> findAll();

    List<ActionQuotidienne> findByPlanningId(int planningId);

    List<ActionQuotidienne> findByStatut(EnumStatutAction statut);
    
    void marquerCommeRealisee(int id, String commentaire);
    
    List<ActionQuotidienne> findActionsByUtilisateurId(int utilisateurId);
}