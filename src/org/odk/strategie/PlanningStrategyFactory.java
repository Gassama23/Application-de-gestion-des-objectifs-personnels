package org.odk.strategie;

import org.odk.model.Objectif;
import org.odk.model.ObjectifApprentissage;
import org.odk.model.ObjectifDevPersonnel;
import org.odk.model.ObjectifEconomie;
import org.odk.model.ObjectifSport;

public class PlanningStrategyFactory {

    public static PlanningStrategy
    getStrategy(Objectif objectif) {

        if (objectif instanceof ObjectifSport) {

            return new SportPlanningStrategy();
        }

        if (objectif instanceof ObjectifEconomie) {

            return new EconomiePlanningStrategy();
        }

        if (objectif instanceof ObjectifApprentissage) {

            return new ApprentissagePlanningStrategy();
        }

        if (objectif instanceof ObjectifDevPersonnel) {

            return new DeveloppementPersonnelPlanningStrategy();
        }

        throw new IllegalArgumentException(
                "Type objectif non supporté."
        );
    }
}