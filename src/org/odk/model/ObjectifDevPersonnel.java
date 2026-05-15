package org.odk.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.odk.enums.EnumStatut;

public class ObjectifDevPersonnel extends Objectif {

    private String type_dev_personnel;
    private int duree_dev_personnel;
    private int jours_realises;
    private String habitude_cible;

    public ObjectifDevPersonnel() {
    }

    public ObjectifDevPersonnel(
            int id,
            String nom_objectif,
            int utilisateur_id,
            String description,
            LocalDate date_debut,
            LocalDate date_fin,
            EnumStatut status,

            int duree_dev_personnel,
            String habitude_cible,
            int jours_realises,
            String type_dev_personnel
    ) {
        super(id, nom_objectif, utilisateur_id, description, date_debut, date_fin, status);
        this.duree_dev_personnel = duree_dev_personnel;
        this.habitude_cible = habitude_cible;
        this.jours_realises = jours_realises;
        this.type_dev_personnel = type_dev_personnel;
    }

    @Override
    public double calculerPourcentage() {
        long totalJours = ChronoUnit.DAYS.between(date_debut, date_fin);

        if (totalJours <= 0) {
            return 0;
        }

        double pourcentage = ((double) jours_realises / totalJours) * 100;

        return Math.min(pourcentage, 100);
    }

    public String getType_dev_personnel() {
        return type_dev_personnel;
    }

    public void setType_dev_personnel(String type_dev_personnel) {
        this.type_dev_personnel = type_dev_personnel;
    }

    public int getDuree_dev_personnel() {
        return duree_dev_personnel;
    }

    public void setDuree_dev_personnel(int duree_dev_personnel) {
        this.duree_dev_personnel = duree_dev_personnel;
    }

    public int getJours_realises() {
        return jours_realises;
    }

    public void setJours_realises(int jours_realises) {
        this.jours_realises = jours_realises;
    }

    public String getHabitude_cible() {
        return habitude_cible;
    }

    public void setHabitude_cible(String habitude_cible) {
        this.habitude_cible = habitude_cible;
    }
}