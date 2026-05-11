package org.odk.strategie;

import org.odk.model.AttentesUtilisateur;
import org.odk.model.Objectif;
import org.odk.model.Planning;

public interface PlanningStrategy {

    Planning genererPlanning(
            Objectif objectif,
            AttentesUtilisateur attentes
    );
}