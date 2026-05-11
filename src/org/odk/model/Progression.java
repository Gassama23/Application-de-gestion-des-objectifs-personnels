package org.odk.model;

import java.util.Date;

public class Progression {

    private int id;

    private Date date_progression;

    private boolean etat;

    private String commentaire;

    private int objectif_id;

    private int action_quotidienne_id;

    /*
     * Constructeur vide
     */
    public Progression() {
    }

    /*
     * Constructeur avec paramètres
     */
    public Progression(
            Date date_progression,
            boolean etat,
            String commentaire
    ) {

        this.date_progression = date_progression;
        this.etat = etat;
        this.commentaire = commentaire;
    }

    /*
     * Getters & Setters
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_progression() {
        return date_progression;
    }

    public void setDate_progression(
            Date date_progression
    ) {
        this.date_progression = date_progression;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(
            String commentaire
    ) {
        this.commentaire = commentaire;
    }

    public int getObjectif_id() {
        return objectif_id;
    }

    public void setObjectif_id(
            int objectif_id
    ) {
        this.objectif_id = objectif_id;
    }

    public int getAction_quotidienne_id() {
        return action_quotidienne_id;
    }

    public void setAction_quotidienne_id(
            int action_quotidienne_id
    ) {
        this.action_quotidienne_id =
                action_quotidienne_id;
    }
}