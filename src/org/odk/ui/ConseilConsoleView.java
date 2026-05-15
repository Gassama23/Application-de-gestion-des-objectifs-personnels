package org.odk.ui;

import org.odk.model.Objectif;
import org.odk.service.ConseilService;

public class ConseilConsoleView {

    private final ConseilService conseilService;

    public ConseilConsoleView() {
        this.conseilService = new ConseilService();
    }

    public void afficherConseil(Objectif objectif) {

        if (objectif == null || objectif.getId() <= 0) {
            System.out.println("Objectif invalide.");
            return;
        }

        String conseil =
                conseilService.genererConseil(objectif);

        System.out.println("\n===== CONSEIL PERSONNALISÉ =====");
        System.out.println("Objectif : " + objectif.getNom_objectif());
        System.out.println("Conseil  : " + conseil);
        System.out.println("================================");
    }
}