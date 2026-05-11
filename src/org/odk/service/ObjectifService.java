package org.odk.service;

import org.odk.model.AttentesUtilisateur;
import org.odk.model.Objectif;
import org.odk.model.ObjectifApprentissage;
import org.odk.model.ObjectifDevPersonnel;
import org.odk.model.ObjectifEconomie;
import org.odk.model.ObjectifSport;
import org.odk.model.Planning;

import org.odk.repository.interfaces.AttentesUtilisateurRepository;
import org.odk.repository.interfaces.ObjectifRepository;

import org.odk.repository.jdbc.AttentesUtilisateurRepositoryJdbc;
import org.odk.repository.jdbc.JdbcObjectifApprentissageRepository;
import org.odk.repository.jdbc.JdbcObjectifDevPersonnelRepository;
import org.odk.repository.jdbc.JdbcObjectifEconomieRepository;
import org.odk.repository.jdbc.JdbcObjectifRepository;
import org.odk.repository.jdbc.JdbcObjectifSportRepository;

import org.odk.ui.AttentesUtilisateurConsoleView;

public class ObjectifService {

    private final ObjectifRepository objectifRepository;

    private final JdbcObjectifSportRepository objectifSportRepository;
    private final JdbcObjectifEconomieRepository objectifEconomieRepository;
    private final JdbcObjectifApprentissageRepository objectifApprentissageRepository;
    private final JdbcObjectifDevPersonnelRepository objectifDevPersonnelRepository;

    private final AttentesUtilisateurRepository attentesRepository;
    private final PlanningService planningService;
    private final AttentesUtilisateurConsoleView attentesView;

    public ObjectifService() {
        this.objectifRepository = new JdbcObjectifRepository();

        this.objectifSportRepository = new JdbcObjectifSportRepository();
        this.objectifEconomieRepository = new JdbcObjectifEconomieRepository();
        this.objectifApprentissageRepository = new JdbcObjectifApprentissageRepository();
        this.objectifDevPersonnelRepository = new JdbcObjectifDevPersonnelRepository();

        this.attentesRepository = new AttentesUtilisateurRepositoryJdbc();
        this.planningService = new PlanningService();
        this.attentesView = new AttentesUtilisateurConsoleView();
    }

    public Planning creerObjectifAvecPlanning(
            Objectif objectif,
            int utilisateurId
    ) {
        if (objectif == null) {
            throw new IllegalArgumentException("Objectif invalide.");
        }

        if (utilisateurId <= 0) {
            throw new IllegalArgumentException("Utilisateur invalide.");
        }

        objectif.setUtilisateur_id(utilisateurId);

        Objectif objectifSauvegarde =
                sauvegarderObjectifSelonType(objectif);

        AttentesUtilisateur attentes =
                attentesView.collecterAttentes(
                        objectifSauvegarde,
                        utilisateurId
                );

        attentesRepository.save(attentes);

        return planningService.genererPlanning(
                objectifSauvegarde,
                attentes
        );
    }

    private Objectif sauvegarderObjectifSelonType(Objectif objectif) {

        if (objectif instanceof ObjectifSport) {
            return objectifSportRepository.save((ObjectifSport) objectif);
        }

        if (objectif instanceof ObjectifEconomie) {
            return objectifEconomieRepository.save((ObjectifEconomie) objectif);
        }

        if (objectif instanceof ObjectifApprentissage) {
            return objectifApprentissageRepository.save((ObjectifApprentissage) objectif);
        }

        if (objectif instanceof ObjectifDevPersonnel) {
            return objectifDevPersonnelRepository.save((ObjectifDevPersonnel) objectif);
        }

        return objectifRepository.save(objectif);
    }
}