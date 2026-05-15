/**
 * 
 */
/**
 * 
 */
module appGestionObjPerso {
	requires java.sql;
	 uses org.odk.strategie.PlanningStrategy;

	    provides org.odk.strategie.PlanningStrategy
	        with org.odk.strategie.SportPlanningStrategy,
	             org.odk.strategie.EconomiePlanningStrategy,
	             org.odk.strategie.ApprentissagePlanningStrategy,
	             org.odk.strategie.DeveloppementPersonnelPlanningStrategy;
}