package org.odk.model;

import java.time.LocalDate;
import org.odk.enums.EnumStatut;

public abstract class Objectif {
	protected int id;
	protected int utilsateur_id;
	protected String nom_objectif,description;
	protected LocalDate date_debut,date_fin; 
	protected EnumStatut status; 
	
	public Objectif() {
		
	}
	
	public Objectif(
		int id,
		String nom_objectif ,
		int utilsateur_id,
		String description,
		LocalDate date_debut,
		LocalDate date_fin,
		EnumStatut status) {
		this.id=id;
		this.nom_objectif=nom_objectif;
		this.utilsateur_id=utilsateur_id;
		this.description=description;
		this.date_debut=date_debut;
		this.date_fin=date_fin;
		this.status=status;
	}
	public String getNom_objectif() {
		return nom_objectif;
	}
	public void setNom_objectif(String nom_objectif) {
		this.nom_objectif = nom_objectif;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(LocalDate date_debut) {
		this.date_debut = date_debut;
	}
	public LocalDate getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(LocalDate date_fin) {
		this.date_fin = date_fin;
	}
	public EnumStatut getStatus() {
		return status;
	}
	public void setStatus(EnumStatut status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUtilsateur_id() {
		return utilsateur_id;
	}
	public void setUtilsateur_id(int utilsateur_id) {
		this.utilsateur_id = utilsateur_id;
	}
	
	

}
