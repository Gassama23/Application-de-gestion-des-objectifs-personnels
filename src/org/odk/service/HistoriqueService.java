package org.odk.service;

import java.util.Date;
import java.util.List;

import org.odk.model.Historique;
import org.odk.repository.interfaces.HistoriqueRepository;
import org.odk.repository.jdbc.JdbcHistoriqueRepository;

public class HistoriqueService {

    private final HistoriqueRepository historiqueRepository;

    public HistoriqueService() {
        this.historiqueRepository = new JdbcHistoriqueRepository();
    }

    public void enregistrerHistorique(
            int utilisateurId,
            int objectifId,
            String action
    ) {
        if (utilisateurId <= 0 || action == null || action.isBlank()) {
            return;
        }

        Historique historique = new Historique();

        historique.setUtilisateurId(utilisateurId);
        historique.setObjectifId(objectifId);
        historique.setAction(action);
        historique.setDateHistorique(new Date());

        historiqueRepository.sauvegarder(historique);
    }

    public List<Historique> listerHistoriqueUtilisateur(int utilisateurId) {

        if (utilisateurId <= 0) {
            System.err.println("Erreur : utilisateur invalide.");
            return List.of();
        }

        return historiqueRepository.findByUtilisateurId(utilisateurId);
    }
}