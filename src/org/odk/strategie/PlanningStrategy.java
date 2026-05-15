package org.odk.strategie;

import org.odk.model.AttentesUtilisateur;
import org.odk.model.Objectif;
import org.odk.model.Planning;

public interface PlanningStrategy {

    Planning genererPlanning(Objectif objectif, AttentesUtilisateur attentes);
    
    String genererConseil(Objectif objectif, AttentesUtilisateur attentes);
    
    String genererDescriptionAction(Objectif objectif, AttentesUtilisateur attentes);
    
    boolean supporte(Objectif objectif);
}