package org.odk.model;

import java.time.LocalDate;
import org.odk.enums.EnumStatut;

public class ObjectifSport extends Objectif {

    private String type_activite;
    private int duree_seance;
    private int nb_seances_realisees;
    private int frequence_hebdo;
    private String niveauSportif;

    public ObjectifSport() {
        super();
    }

    public ObjectifSport(
            int id,
            String nom_objectif,
            int utilisateur_id,
            String description,
            LocalDate date_debut,
            LocalDate date_fin,
            EnumStatut status,
            int duree_seance,
            int frequence_hebdo,
            int nb_seances_realisees,
            String niveauSportif,
            String type_activite
    ) {
        super(id, nom_objectif, utilisateur_id, description, date_debut, date_fin, status);
        this.duree_seance = duree_seance;
        this.frequence_hebdo = frequence_hebdo;
        this.nb_seances_realisees = nb_seances_realisees;
        this.niveauSportif = niveauSportif;
        this.type_activite = type_activite;
    }

    @Override
    public double calculerPourcentage() {
        if (frequence_hebdo <= 0) {
            return 0;
        }

        double pourcentage = ((double) nb_seances_realisees / frequence_hebdo) * 100;

        return Math.min(pourcentage, 100);
    }

    public String getType_activite() {
        return type_activite;
    }

    public void setType_activite(String type_activite) {
        this.type_activite = type_activite;
    }

    public int getDuree_seance() {
        return duree_seance;
    }

    public void setDuree_seance(int duree_seance) {
        this.duree_seance = duree_seance;
    }

    public int getNb_seances_realisees() {
        return nb_seances_realisees;
    }

    public void setNb_seances_realisees(int nb_seances_realisees) {
        this.nb_seances_realisees = nb_seances_realisees;
    }

    public int getFrequence_hebdo() {
        return frequence_hebdo;
    }

    public void setFrequence_hebdo(int frequence_hebdo) {
        this.frequence_hebdo = frequence_hebdo;
    }

    public String getNiveauSportif() {
        return niveauSportif;
    }

    public void setNiveauSportif(String niveauSportif) {
        this.niveauSportif = niveauSportif;
    }
}