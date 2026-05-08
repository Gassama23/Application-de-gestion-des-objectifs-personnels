package org.odk.model;

import java.time.LocalDate;

import org.odk.enums.EnumStatut;


public abstract class Objectif {

    protected int id;

    protected int utilisateur_id;

    protected String nom_objectif;

    protected String description;

    protected LocalDate date_debut;

    protected LocalDate date_fin;

    protected EnumStatut status;

    public Objectif() {
    }

    public Objectif(
            int id,
            String nom_objectif,
            int utilisateur_id,
            String description,
            LocalDate date_debut,
            LocalDate date_fin,
            EnumStatut status
    ) {
        this.id = id;
        this.nom_objectif = nom_objectif;
        this.utilisateur_id = utilisateur_id;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.status = status;
    }

    public Objectif(
            String nom_objectif,
            int utilisateur_id,
            String description,
            LocalDate date_debut,
            LocalDate date_fin,
            EnumStatut status
    ) {
        this.nom_objectif = nom_objectif;
        this.utilisateur_id = utilisateur_id;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.status = status;
    }

    public abstract double calculerPourcentage();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(int utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    public String getNom_objectif() {
        return nom_objectif;
    }

    public void setNom_objectif(String nom_objectif) {
        this.nom_objectif = nom_objectif;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    public EnumStatut getStatus() {
        return status;
    }

    public void setStatus(EnumStatut status) {
        this.status = status;
    }

    @Override
    public String toString() {

        return "\nObjectif {" +
                "\n id = " + id +
                "\n nom_objectif = '" + nom_objectif + '\'' +
                "\n description = '" + description + '\'' +
                "\n date_debut = " + date_debut +
                "\n date_fin = " + date_fin +
                "\n status = " + status +
                "\n utilisateur_id = " + utilisateur_id +
                "\n}";
    }
}