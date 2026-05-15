package org.odk.strategie;

import java.util.ServiceLoader;

import org.odk.model.Objectif;

public class PlanningStrategyFactory {

	public static PlanningStrategy getStrategy(Objectif objectif) {

        ServiceLoader<PlanningStrategy> strategies = ServiceLoader.load(PlanningStrategy.class);

        for (PlanningStrategy strategy : strategies) {
            if (strategy.supporte(objectif)) {
                return strategy;
            }
        }

        throw new IllegalArgumentException("Type objectif non supporté.");
    }
}