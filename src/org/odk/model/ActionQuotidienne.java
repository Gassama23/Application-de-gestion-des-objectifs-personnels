package org.odk.model;

import java.util.Date;

import org.odk.enums.EnumStatutAction;

public class ActionQuotidienne {

    private int id;
    private String description;
    private Date datePrevue;
    private Date dateRealisation;
    private String commentaire;
    private EnumStatutAction statut;

    // Clé étrangère vers Planning
    private Planning planning;
    
   

    public ActionQuotidienne() {
    }

    public ActionQuotidienne(int id, String description, Date datePrevue, Date dateRealisation,
    		 EnumStatutAction statut, String commentaire, Planning planning) {
        this.id = id;
        this.description = description;
        this.datePrevue = datePrevue;
        this.dateRealisation = dateRealisation;
        this.statut = statut;
        this.commentaire = commentaire;
        this.planning = planning;
    }

    // ===== GETTERS & SETTERS =====

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatePrevue() {
        return datePrevue;
    }

    public void setDatePrevue(Date datePrevue) {
        this.datePrevue = datePrevue;
    }

    public Date getDateRealisation() {
        return dateRealisation;
    }

    public void setDateRealisation(Date dateRealisation) {
        this.dateRealisation = dateRealisation;
    }

    public EnumStatutAction getStatut() {
		return statut;
	}

	public void setStatut(EnumStatutAction statut) {
		this.statut = statut;
	}

	public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Planning getPlanning() {
        return planning;
    }

    public void setPlanning(Planning planning) {
        this.planning = planning;
    }
    
    /**
     * Vérifie si l'action est terminée.
     */
    public boolean estTerminee() {
        return statut == EnumStatutAction.TERMINEE;
    }

    /**
     * Marquer l'action comme réalisée.
     */
    public void marquerCommeRealisee(String commentaire) {

        this.statut = EnumStatutAction.TERMINEE;

        this.dateRealisation = new Date();

        this.commentaire = commentaire;
    }

    @Override
    public String toString() {

        return "\nActionQuotidienne {" +
                "\n id = " + id +
                "\n description = '" + description + '\'' +
                "\n datePrevue = " + datePrevue +
                "\n dateRealisation = " + dateRealisation +
                "\n statut = " + statut +
                "\n commentaire = '" + commentaire + '\'' +
                "\n}";
    }
}