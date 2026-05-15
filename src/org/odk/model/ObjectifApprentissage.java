package org.odk.model;

import java.time.LocalDate;

import org.odk.enums.EnumStatut;


public class ObjectifApprentissage extends Objectif {

    private String ressource;

    private int duree_etudeParJour;

    private int jours_etudies;

    
    public ObjectifApprentissage() {
    }

    
    public ObjectifApprentissage(
            int id,
            String nom_objectif,
            int utilisateur_id,
            String description,
            LocalDate date_debut,
            LocalDate date_fin,
            EnumStatut status,

            int duree_etudeParJour,
            int jours_etudies,
            String ressource
    ) {

        super(
                id,
                nom_objectif,
                utilisateur_id,
                description,
                date_debut,
                date_fin,
                status
        );

        this.duree_etudeParJour = duree_etudeParJour;
        this.jours_etudies = jours_etudies;
        this.ressource = ressource;
    }

    public String getRessource() {
        return ressource;
    }

    public void setRessource(String ressource) {
        this.ressource = ressource;
    }

    public int getDuree_etudeParJour() {
        return duree_etudeParJour;
    }

    public void setDuree_etudeParJour(
            int duree_etudeParJour
    ) {
        this.duree_etudeParJour =
                duree_etudeParJour;
    }

    public int getJours_etudies() {
        return jours_etudies;
    }

    public void setJours_etudies(
            int jours_etudies
    ) {
        this.jours_etudies = jours_etudies;
    }


    @Override
    public double calculerPourcentage() {


        long totalJours =
                java.time.temporal.ChronoUnit.DAYS.between(
                        date_debut,
                        date_fin
                );

        if (totalJours <= 0) {
            return 0;
        }

        return ((double) jours_etudies
                / totalJours) * 100;
    }

   
}