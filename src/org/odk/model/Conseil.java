package org.odk.model;

import java.util.Date;

public class Conseil {
	private int id;
	private String titre;
	private String contenu;
	private Categorie categorie;
	private TypeConseil type;
	private Date datepublication;
	private String domainecible;
	private String icone;
	private Boolean lu;
	private int priorite;

	
	public Conseil(int id, String titre, String contenu, Categorie categorie, TypeConseil type, 
			       Date datepublication, String domainecible, String icone, Boolean lu, int priorite){
	this.id = id;
	this.titre = titre;
	this.contenu = contenu;
	this.categorie = categorie;
	this.type = type;
	this.datepublication = datepublication;
	this.domainecible = domainecible;
	this.icone = icone;
	this.lu = lu;
	this.priorite = priorite;
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


	public Categorie getCategorie() {
		return categorie;
	}


	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}


	public TypeConseil getType() {
		return type;
	}


	public void setType(TypeConseil type) {
		this.type = type;
	}


	public Date getDatepublication() {
		return datepublication;
	}


	public void setDatepublication(Date datepublication) {
		this.datepublication = datepublication;
	}


	public String getDomainecible() {
		return domainecible;
	}


	public void setDomainecible(String domainecible) {
		this.domainecible = domainecible;
	}


	public String getIcone() {
		return icone;
	}


	public void setIcone(String icone) {
		this.icone = icone;
	}


	public Boolean getLu() {
		return lu;
	}


	public void setLu(Boolean lu) {
		this.lu = lu;
	}


	public int getPriorite() {
		return priorite;
	}


	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}
 
}
