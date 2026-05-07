package org.odk.model;

import java.time.LocalDate;
import org.odk.enums.EnumStatut;

public class ObjectifSport extends Objectif{


    protected String type_activite;
    protected int duree_seance;
    protected int nb_seances_realisees;
    protected int frequence_hebdo;
    protected String niveauSportif;

    public ObjectifSport(
		int id, 
		String nom_objectif, 
		String description, 
		LocalDate date_debut, 
		LocalDate date_fin, 
		EnumStatut status,
		
		int duree_seance, 
		int frequence_hebdo, 
		int nb_seances_realisees, 
		String niveauSportif, 
		String type_activite
	) {
        super(id, nom_objectif, description, date_debut, date_fin, status);
        this.duree_seance = duree_seance;
        this.frequence_hebdo = frequence_hebdo;
        this.nb_seances_realisees = nb_seances_realisees;
        this.niveauSportif = niveauSportif;
        this.type_activite = type_activite;
    }

  

	public String getType_activite() {
		return type_activite;
	}

	public void setType_activite(String type_activite) {
		this.type_activite = type_activite;
	}

	public int getDuree_seance() {
		return duree_seance;
	}

	public void setDuree_seance(int duree_seance) {
		this.duree_seance = duree_seance;
	}

	public int getNb_seances_realisees() {
		return nb_seances_realisees;
	}

	public void setNb_seances_realisees(int nb_seances_realisees) {
		this.nb_seances_realisees = nb_seances_realisees;
	}

	public int getFrequence_hebdo() {
		return frequence_hebdo;
	}

	public void setFrequence_hebdo(int frequence_hebdo) {
		this.frequence_hebdo = frequence_hebdo;
	}

	public String getNiveauSportif() {
		return niveauSportif;
	}

	public void setNiveauSportif(String niveauSportif) {
		this.niveauSportif = niveauSportif;
	}

}
