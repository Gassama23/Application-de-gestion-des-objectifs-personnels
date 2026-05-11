package org.odk.repository.interfaces;

import java.util.List;

import org.odk.model.PlanningDetail;

public interface PlanningDetailRepository {
	
	 PlanningDetail save(
	            PlanningDetail planningDetail
	    );

	    PlanningDetail findById(int id);

	    List<PlanningDetail> findByPlanningId(
	            int planningId
	    );

	    void delete(int id);

}
