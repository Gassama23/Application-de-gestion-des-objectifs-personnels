package org.odk.ui;

import java.util.List;

import org.odk.model.Historique;
import org.odk.model.Utilisateur;
import org.odk.service.HistoriqueService;

public class HistoriqueConsoleView {

    private final HistoriqueService historiqueService;

    public HistoriqueConsoleView() {
        this.historiqueService = new HistoriqueService();
    }

    public void afficherHistorique(Utilisateur utilisateur) {

        if (utilisateur == null || utilisateur.getId() <= 0) {
            System.out.println(" Utilisateur non connecté.");
            return;
        }

        List<Historique> historiques =
                historiqueService.listerHistoriqueUtilisateur(
                        utilisateur.getId()
                );

        if (historiques == null || historiques.isEmpty()) {
            System.out.println("Aucun historique disponible.");
            return;
        }

        System.out.println("\n===== MON HISTORIQUE =====");

        for (Historique historique : historiques) {
            System.out.println("--------------------------------");
            System.out.println("Date       : " + historique.getDateHistorique());
            System.out.println("Action     : " + historique.getAction());
            System.out.println("Objectif ID: " + historique.getObjectifId());
        }

        System.out.println("--------------------------------");
    }
}