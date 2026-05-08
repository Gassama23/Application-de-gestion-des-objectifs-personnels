package org.odk.service;

import org.odk.model.Planning;

public class PlanningService {

    public void attribuer(Planning planning) {
        System.out.println("Planning attribué avec succès : "
                + planning.getTitre());
    }
}