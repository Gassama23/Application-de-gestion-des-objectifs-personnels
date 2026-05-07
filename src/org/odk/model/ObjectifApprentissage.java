package org.odk.model;

import java.time.LocalDate;
import org.odk.enums.EnumStatut;

public class ObjectifApprentissage extends Objectif{

    protected String type_apprentissage;
    protected String ressource;
    protected int duree_etudeParJour;
    protected int jours_etudies;
    protected String sujet;


	public ObjectifApprentissage(
		int id, 
		String nom_objectif, 
		String description, 
		LocalDate date_debut,
		LocalDate date_fin, 
		EnumStatut status, 
		
		String type_apprentissage, 
		String ressource, 
		int duree_etudeParJour, 
		int jours_etudies, 
		String sujet) {
		super(id, nom_objectif, description, date_debut, date_fin, status);
		this.type_apprentissage = type_apprentissage;
		this.ressource = ressource;
		this.duree_etudeParJour = duree_etudeParJour;
		this.jours_etudies = jours_etudies;
		this.sujet = sujet;	
	}

	public String getType_apprentissage() {
		return type_apprentissage;
	}

	public void setType_apprentissage(String type_apprentissage) {
		this.type_apprentissage = type_apprentissage;
	}

	public String getRessource() {
		return ressource;
	}

	public void setRessource(String ressource) {
		this.ressource = ressource;
	}

	public int getDuree_etudeParJour() {
		return duree_etudeParJour;
	}

	public void setDuree_etudeParJour(int duree_etudeParJour) {
		this.duree_etudeParJour = duree_etudeParJour;
	}

	public int getJours_etudies() {
		return jours_etudies;
	}

	public void setJours_etudies(int jours_etudies) {
		this.jours_etudies = jours_etudies;
	}

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}
}
