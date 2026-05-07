package org.odk.model;

import org.odk.enums.EnumStatut;

import java.time.LocalDate;

public class ObjectifSport extends Objectif{


    protected String type_activite;
    protected int duree_seance;
    protected int nb_seances_realisees;
    protected int frequence_hebdo;
    protected String niveauSportif;

    public ObjectifSport(
            String nom_objectif,
            String description,
            LocalDate date_debut,
            LocalDate date_fin,
            EnumStatut status,

            String type_activite,
            int duree_seance,
            int nb_seances_realisees,
            int frequence_hebdo,
            String niveauSportif
    ) {
        super(nom_objectif,description,date_debut,date_fin,status);
        this.type_activite = type_activite;
        this.duree_seance = duree_seance;
        this.nb_seances_realisees = nb_seances_realisees;
        this.frequence_hebdo = frequence_hebdo;
        this.niveauSportif = niveauSportif;
    }

    @Override
    public void ajouter() {

    }

    @Override
    public void modifier(int id) {

    }

    @Override
    public void supprimer(int id) {

    }

    @Override
    public void voirObjectif(int id) {

    }

    @Override
    public void calculerPourcentage() {

    }
}
