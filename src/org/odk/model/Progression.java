package org.odk.model;

import java.util.Date;

public class Progression {
	private int id;
	
	private Date date;
	private boolean etat;
	private String commentaire;
	
	//Constructeur	
	public Progression(Date date, boolean etat, String commentaire) {
		super();
		this.date = date;
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
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
