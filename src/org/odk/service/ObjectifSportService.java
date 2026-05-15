package org.odk.service;

import org.odk.model.ObjectifSport;
import org.odk.repository.jdbc.JdbcObjectifSportRepository;

public class ObjectifSportService {

    private final JdbcObjectifSportRepository objectifSportRepository;

    public ObjectifSportService() {
        this.objectifSportRepository = new JdbcObjectifSportRepository();
    }

    public ObjectifSport creerObjectifSport(ObjectifSport objectifSport) {

        if (objectifSport == null) {
            System.err.println("Erreur : objectif sport invalide.");
            return null;
        }

        if (objectifSport.getUtilisateur_id() <= 0) {
            System.err.println("Erreur : utilisateur invalide.");
            return null;
        }

        if (objectifSport.getNom_objectif() == null || objectifSport.getNom_objectif().isBlank()) {
            System.err.println("Erreur : nom de l'objectif obligatoire.");
            return null;
        }

        if (objectifSport.getFrequence_hebdo() <= 0) {
            System.err.println("Erreur : fréquence hebdomadaire invalide.");
            return null;
        }

        return objectifSportRepository.save(objectifSport);
    }

    public ObjectifSport trouverParObjectifId(int objectifId) {

        if (objectifId <= 0) {
            System.err.println("Erreur : objectif invalide.");
            return null;
        }

        return objectifSportRepository.findByObjectifId(objectifId);
    }
}