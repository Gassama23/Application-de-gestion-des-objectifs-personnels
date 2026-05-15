package org.odk.service;

import org.odk.model.ObjectifEconomie;
import org.odk.repository.jdbc.JdbcObjectifEconomieRepository;

public class ObjectifEconomieService {

    private final JdbcObjectifEconomieRepository objectifEconomieRepository;

    public ObjectifEconomieService() {
        this.objectifEconomieRepository =
                new JdbcObjectifEconomieRepository();
    }

    public ObjectifEconomie creerObjectifEconomie(
            ObjectifEconomie objectif
    ) {

        if (objectif == null) {
            System.err.println("Erreur : objectif économie invalide.");
            return null;
        }

        if (objectif.getUtilisateur_id() <= 0) {
            System.err.println("Erreur : utilisateur invalide.");
            return null;
        }

        if (objectif.getNom_objectif() == null
                || objectif.getNom_objectif().isBlank()) {
            System.err.println("Erreur : nom de l'objectif obligatoire.");
            return null;
        }

        if (objectif.getDescription() == null
                || objectif.getDescription().isBlank()) {
            System.err.println("Erreur : description obligatoire.");
            return null;
        }

        if (objectif.getDate_debut() == null
                || objectif.getDate_fin() == null) {
            System.err.println("Erreur : dates obligatoires.");
            return null;
        }

        if (objectif.getDate_fin().isBefore(objectif.getDate_debut())) {
            System.err.println("Erreur : date de fin invalide.");
            return null;
        }

        if (objectif.getMontant_cible() <= 0) {
            System.err.println("Erreur : montant cible invalide.");
            return null;
        }

        if (objectif.getDelai_mois() <= 0) {
            System.err.println("Erreur : délai en mois invalide.");
            return null;
        }

        return objectifEconomieRepository.save(objectif);
    }

    public ObjectifEconomie trouverParObjectifId(int objectifId) {

        if (objectifId <= 0) {
            System.err.println("Erreur : objectif invalide.");
            return null;
        }

        return objectifEconomieRepository.findByObjectifId(objectifId);
    }
}