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
    private String nomObjectif;
    private String typeObjectif;
    private Planning planning;
    
   

    public ActionQuotidienne() {
    }

    public ActionQuotidienne(int id, String description, Date datePrevue, Date dateRealisation,
    		 EnumStatutAction statut, String commentaire,String nomObjectif,String typeObjectif, Planning planning) {
        this.id = id;
        this.description = description;
        this.datePrevue = datePrevue;
        this.dateRealisation = dateRealisation;
        this.statut = statut;
        this.commentaire = commentaire;
        this.planning = planning;
        this.nomObjectif = nomObjectif;
        this.typeObjectif=typeObjectif;
    }

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
    
    public boolean estTerminee() {
        return statut == EnumStatutAction.TERMINEE;
    }

	public String getNomObjectif() {
		return nomObjectif;
	}

	public void setNomObjectif(String nomObjectif) {
		this.nomObjectif = nomObjectif;
	}

	public String getTypeObjectif() {
		return typeObjectif;
	}

	public void setTypeObjectif(String typeObjectif) {
		this.typeObjectif = typeObjectif;
	}

    
   
}