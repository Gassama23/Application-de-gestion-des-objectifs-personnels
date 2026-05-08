package org.odk.repository.interfaces;

import java.util.List;
import org.odk.model.Planning;

public interface PlanningRepository {

    void save(Planning planning);

    void update(Planning planning);

    void delete(int id);

    Planning findById(int id);

    List<Planning> findAll();

    Planning findByObjectifId(int objectifId);
}