package org.odk.service;

import org.odk.model.ObjectifDevPersonnel;
import org.odk.repository.jdbc.JdbcObjectifDevPersonnelRepository;

public class ObjectifDevPersonnelService {

    private final JdbcObjectifDevPersonnelRepository objectifDevPersonnelRepository;

    public ObjectifDevPersonnelService() {
        this.objectifDevPersonnelRepository = new JdbcObjectifDevPersonnelRepository();
    }

    public ObjectifDevPersonnel creerObjectifDevPersonnel(ObjectifDevPersonnel objectifDevPersonnel) {

        if (objectifDevPersonnel == null) {
            System.err.println("Erreur : objectif développement personnel invalide.");
            return null;
        }

        if (objectifDevPersonnel.getUtilisateur_id() <= 0) {
            System.err.println("Erreur : utilisateur invalide.");
            return null;
        }

        if (objectifDevPersonnel.getNom_objectif() == null || objectifDevPersonnel.getNom_objectif().isBlank()) {
            System.err.println("Erreur : nom de l'objectif obligatoire.");
            return null;
        }

        if (objectifDevPersonnel.getHabitude_cible() == null || objectifDevPersonnel.getHabitude_cible().isBlank()) {
            System.err.println("Erreur : habitude cible obligatoire.");
            return null;
        }

        return objectifDevPersonnelRepository.save(objectifDevPersonnel);
    }

    public ObjectifDevPersonnel trouverParObjectifId(int objectifId) {

        if (objectifId <= 0) {
            System.err.println("Erreur : objectif invalide.");
            return null;
        }

        return objectifDevPersonnelRepository.findByObjectifId(objectifId);
    }
}