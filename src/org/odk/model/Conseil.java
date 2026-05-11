package org.odk.model;

import java.util.Date;

import org.odk.enums.EnumTypeConseil;

public class Conseil {
	private int id;
	private String titre;
	private String contenu;
	private String domaineCible;
	private EnumTypeConseil typeConseil;
	
	
	// constructeur avec paramètres
	public Conseil(String titre, String contenu,
			     String domaineCible, EnumTypeConseil typeConseil){
	this.titre = titre;
	this.contenu = contenu;
	this.domaineCible = domaineCible;
	this.typeConseil = typeConseil;
			
	}
	// constructeur par défauta
	public Conseil() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public String getContenu() {
		return contenu;
	}


	public void setContenu(String contenu) {
		this.contenu = contenu;
	}	
	public EnumTypeConseil gettypeConseil() {
		return typeConseil;
	}


	public String getDomaineCible() {
		return domaineCible;
	}


	public void setDomaineCible(String domaineCible) {
		this.domaineCible = domaineCible;
	}


	public EnumTypeConseil getTypeConseil() {
		return typeConseil;
	}


	public void setTypeConseil(EnumTypeConseil typeConseil) {
		this.typeConseil = typeConseil;
	}
	
	
 
}
