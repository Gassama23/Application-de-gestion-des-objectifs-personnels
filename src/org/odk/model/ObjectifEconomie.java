package org.odk.model;

import java.time.LocalDate;

import org.odk.enums.EnumStatut;


public class ObjectifEconomie extends Objectif {

    private String type_economie;

    
    private double montant_cible;

    
    private double montant_epargne;

    
    private int delai_mois;

    public ObjectifEconomie() {
        super();
    }

    public ObjectifEconomie(
            int id,
            String nom_objectif,
            int utilisateur_id,
            String description,
            LocalDate date_debut,
            LocalDate date_fin,
            EnumStatut status,

            int delai_mois,
            double montant_cible,
            double montant_epargne,
            String type_economie
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

        this.delai_mois = delai_mois;
        this.montant_cible = montant_cible;
        this.montant_epargne = montant_epargne;
        this.type_economie = type_economie;
    }

    public ObjectifEconomie(
            String nom_objectif,
            int utilisateur_id,
            String description,
            LocalDate date_debut,
            LocalDate date_fin,
            EnumStatut status,

            int delai_mois,
            double montant_cible,
            double montant_epargne,
            String type_economie
    ) {

        super(
                nom_objectif,
                utilisateur_id,
                description,
                date_debut,
                date_fin,
                status
        );

        this.delai_mois = delai_mois;
        this.montant_cible = montant_cible;
        this.montant_epargne = montant_epargne;
        this.type_economie = type_economie;
    }

    
    @Override
    public double calculerPourcentage() {

        if (montant_cible <= 0) {
            return 0;
        }

        double pourcentage =
                (montant_epargne / montant_cible) * 100;

        
        return Math.min(pourcentage, 100);
    }

    public String getType_economie() {
        return type_economie;
    }

    public void setType_economie(String type_economie) {
        this.type_economie = type_economie;
    }

    public double getMontant_cible() {
        return montant_cible;
    }

    public void setMontant_cible(double montant_cible) {
        this.montant_cible = montant_cible;
    }

    public double getMontant_epargne() {
        return montant_epargne;
    }

    public void setMontant_epargne(double montant_epargne) {
        this.montant_epargne = montant_epargne;
    }

    public int getDelai_mois() {
        return delai_mois;
    }

    public void setDelai_mois(int delai_mois) {
        this.delai_mois = delai_mois;
    }

    
}