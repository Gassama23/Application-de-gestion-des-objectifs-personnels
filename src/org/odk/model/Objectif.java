package org.odk.model;

import java.time.LocalDate;

import org.odk.enums.EnumStatut;

public abstract class Objectif {
	protected int id;
	protected String nom_objectif,description;
	protected LocalDate date_debut,date_fin; 
	protected EnumStatut status; 
	public Objectif() {
		
	}
	public Objectif(String nom_objectif,String description,LocalDate date_debut,LocalDate date_fin,EnumStatut status ) {
		this.nom_objectif=nom_objectif;
		this.description=description;
		this.date_debut=date_debut;
		this.date_fin=date_fin;
		this.status=status;
	}
	public Objectif(int id,String nom_objectif ,String description,LocalDate date_debut,LocalDate date_fin,EnumStatut status ) {
		this.id=id;
		this.nom_objectif=nom_objectif;
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
	
	
	

}
