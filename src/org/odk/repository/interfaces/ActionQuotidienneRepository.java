package org.odk.repository.interfaces;

import java.util.List;
import org.odk.model.ActionQuotidienne;

public interface ActionQuotidienneRepository {

    void save(ActionQuotidienne action);

    void update(ActionQuotidienne action);

    void delete(int id);

    ActionQuotidienne findById(int id);

    List<ActionQuotidienne> findAll();

    List<ActionQuotidienne> findByPlanningId(int planningId);

    List<ActionQuotidienne> findByStatut(String statut);
}