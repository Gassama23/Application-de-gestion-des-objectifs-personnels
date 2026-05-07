package org.odk.model;
import org.odk.enums.EnumStatut;
import java.time.LocalDate;

class ObjectifDevPersonnel extends Objectif {
    protected String type_dev_personnel;
    protected int duree_dev_personnel;
    protected int jours_realises;
    protected String habitude_cible;

    public ObjectifDevPersonnel(
            String nom_objectif,String description,LocalDate date_debut,LocalDate date_fin,EnumStatut status,
            String type_dev_personnel,
            int duree_dev_personnel,
            int jours_realises,
            String habitude_cible

    ) {
        super(nom_objectif,description,date_debut,date_fin,status);
        this.type_dev_personnel = type_dev_personnel;
        this.duree_dev_personnel = duree_dev_personnel;
        this.jours_realises = jours_realises;
        this.habitude_cible = habitude_cible;

    }

    @Override
    public void ajouter() {
        //
    }

    @Override
    public void modifier(int id) {
        //
    }

    @Override
    public void supprimer(int id) {
        //
    }

    @Override
    public void voirObjectif(int id) {
        //
    }

    @Override
    public void calculerPourcentage() {
        //
    }
}