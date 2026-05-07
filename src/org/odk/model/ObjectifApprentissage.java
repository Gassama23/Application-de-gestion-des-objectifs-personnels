package org.odk.model;

import org.odk.enums.EnumStatut;

import java.time.LocalDate;

public class ObjectifApprentissage extends Objectif{

    protected String type_apprentissage;
    protected String ressource;
    protected int duree_etudeParJour;
    protected int jours_etudies;
    protected String sujet;

    public ObjectifApprentissage(
            String nom_objectif,
            String description,
            LocalDate date_debut,
            LocalDate date_fin,
            EnumStatut status,

            String type_apprentissage,
            String ressource,
            int duree_etudeParJour,
            int jours_etudies,
            String sujet
            )
    {
        super(nom_objectif,description,date_debut,date_fin,status);
        this.type_apprentissage=type_apprentissage;
        this.ressource=ressource;
        this.duree_etudeParJour=duree_etudeParJour;
        this.jours_etudies=jours_etudies;
        this.sujet=sujet;
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
