package org.odk.model;

import java.util.Date;

public class Progression {
	private int id;
	private Date date_progression;
	private boolean etat;
	private String commentaire;
	private int objectif_id;
	private int action_quotidienne_id;

	public Date getDate_progression() {
		return date_progression;
	}

	public void setDate_progression(Date date_progression) {
		this.date_progression = date_progression;
	}

	public int getObjectif_id() {
		return objectif_id;
	}

	public void setObjectif_id(int objectif_id) {
		this.objectif_id = objectif_id;
	}

	public int getAction_quotidienne_id() {
		return action_quotidienne_id;
	}

	public void setAction_quotidienne_id(int action_quotidienne_id) {
		this.action_quotidienne_id = action_quotidienne_id;
	}

	//Constructeur	
	public Progression(Date date, boolean etat, String commentaire) {
		super();
		this.date_progression = date_progression;
		this.etat = etat;
		this.commentaire = commentaire;
	}
	// getters et setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date_progression;
	}

	public void setDate(Date date_progression) {
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

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	

}
