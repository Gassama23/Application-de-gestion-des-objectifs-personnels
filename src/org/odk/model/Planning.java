package org.odk.model;

import java.util.Date;

public class Planning {

    private int id;
    private String titre;
    private Date dateCreation;
    private boolean actif;
    private int objectifId;
    

    public Planning() {
    }

    public Planning(int id, String titre, Date dateCreation, boolean actif, int objectifId) {
        this.id = id;
        this.titre = titre;
        this.dateCreation = dateCreation;
        this.actif = actif;
        this.objectifId = objectifId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public int getObjectifId() {
        return objectifId;
    }

    public void setObjectifId(int objectifId) {
        this.objectifId = objectifId;
    }
}