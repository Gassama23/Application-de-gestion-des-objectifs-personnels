package org.odk.model;

import java.util.Date;

public class Historique {

    private int id;
    private Date dateHistorique;
    private String action;
    private int utilisateurId;
    private int objectifId;

    public Historique() {
    }

    public Historique(Date dateHistorique, String action, int utilisateurId, int objectifId) {
        this.dateHistorique = dateHistorique;
        this.action = action;
        this.utilisateurId = utilisateurId;
        this.objectifId = objectifId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Date getDateHistorique() {
        return dateHistorique;
    }

    public void setDateHistorique(Date dateHistorique) {
        this.dateHistorique = dateHistorique;
    }


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


    public int getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(int utilisateurId) {
        this.utilisateurId = utilisateurId;
    }


    public int getObjectifId() {
        return objectifId;
    }

    public void setObjectifId(int objectifId) {
        this.objectifId = objectifId;
    }
}