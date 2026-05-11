package org.odk.model;

import java.time.LocalDate;

import org.odk.enums.EnumStatut;


public class ObjectifApprentissage extends Objectif {

    private String type_apprentissage;

    private String ressource;

    private int duree_etudeParJour;

    private int jours_etudies;

    private String sujet;

    
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
            String ressource,
            String sujet,
            String type_apprentissage
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
        this.sujet = sujet;
        this.type_apprentissage = type_apprentissage;
    }

    public String getType_apprentissage() {
        return type_apprentissage;
    }

    public void setType_apprentissage(
            String type_apprentissage
    ) {
        this.type_apprentissage = type_apprentissage;
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

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
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

    @Override
    public String toString() {

        return super.toString() +

                "\nObjectifApprentissage {" +

                "\n type_apprentissage = '"
                + type_apprentissage + '\'' +

                "\n ressource = '"
                + ressource + '\'' +

                "\n duree_etudeParJour = "
                + duree_etudeParJour +

                "\n jours_etudies = "
                + jours_etudies +

                "\n sujet = '"
                + sujet + '\'' +

                "\n progression = "
                + calculerPourcentage() + "%" +

                "\n}";
    }
}