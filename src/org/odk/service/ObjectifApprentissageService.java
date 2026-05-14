package org.odk.service;

import org.odk.model.ObjectifApprentissage;
import org.odk.repository.jdbc.JdbcObjectifApprentissageRepository;

public class ObjectifApprentissageService {

    private final JdbcObjectifApprentissageRepository objectifApprentissageRepository;

    public ObjectifApprentissageService() {
        this.objectifApprentissageRepository = new JdbcObjectifApprentissageRepository();
    }

    public ObjectifApprentissage creerObjectifApprentissage(ObjectifApprentissage objectifApprentissage) {

        if (objectifApprentissage == null) {
            System.err.println("Erreur : objectif apprentissage invalide.");
            return null;
        }

        if (objectifApprentissage.getUtilisateur_id() <= 0) {
            System.err.println("Erreur : utilisateur invalide.");
            return null;
        }

        if (objectifApprentissage.getNom_objectif() == null || objectifApprentissage.getNom_objectif().isBlank()) {
            System.err.println("Erreur : nom de l'objectif obligatoire.");
            return null;
        }

        return objectifApprentissageRepository.save(objectifApprentissage);
    }

    public ObjectifApprentissage trouverParObjectifId(int objectifId) {

        if (objectifId <= 0) {
            System.err.println("Erreur : objectif invalide.");
            return null;
        }

        return objectifApprentissageRepository.findByObjectifId(objectifId);
    }
}