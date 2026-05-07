package org.odk.model;

import org.odk.enums.EnumStatut;

import java.time.LocalDate;

public class ObjectifEconomie extends Objectif{

    protected String type_economie;
    protected int montant_cible;
    protected int montant_epargne;
    protected int delai_mois;

    public ObjectifEconomie(
            String nom_objectif,
            String description,
            LocalDate date_debut,
            LocalDate date_fin,
            EnumStatut status,

            String type_economie,
            int montant_cible,
            int montant_epargne,
            int delai_mois
    ) {
        super(nom_objectif,description,date_debut,date_fin,status);
        this.type_economie = type_economie;
        this.montant_cible = montant_cible;
        this.montant_epargne = montant_epargne;
        this.delai_mois = delai_mois;
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
