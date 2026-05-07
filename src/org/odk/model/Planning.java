package org.odk.model;

import java.util.Date;
import java.util.List;

public class Planning {
	private int id;
    private String titre;
    private Date dateCreation;
    private List<String> sections;
    private List<String> lignes;

    public Planning() {
    }

    // Constructeur avec paramètres
    public Planning(int id, String titre, Date dateCreation, List<String> sections, List<String> lignes) {
        this.id = id;
        this.titre = titre;
        this.dateCreation = dateCreation;
        this.sections = sections;
        this.lignes = lignes;
    }

    // Getters & Setters

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

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<String> getSections() {
        return sections;
    }

    public void setSections(List<String> sections) {
        this.sections = sections;
    }

    public List<String> getLignes() {
        return lignes;
    }

    public void setLignes(List<String> lignes) {
        this.lignes = lignes;
    }


}
