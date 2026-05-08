package org.odk.model;
import java.time.LocalDate;
import org.odk.enums.EnumStatut;

class ObjectifDevPersonnel extends Objectif {
    protected String type_dev_personnel;
    protected int duree_dev_personnel;
    protected int jours_realises;
    protected String habitude_cible;

    public ObjectifDevPersonnel( 
		int id, 
		String nom_objectif, 
		int utilsateur_id, 
		String description, 
		LocalDate date_debut, 
		LocalDate date_fin, 
		EnumStatut status,
		
		int duree_dev_personnel, 
		String habitude_cible, 
		int jours_realises, 
		String type_dev_personnel) {
        super(id, nom_objectif, utilsateur_id, description, date_debut, date_fin, status);
        this.duree_dev_personnel = duree_dev_personnel;
        this.habitude_cible = habitude_cible;
        this.jours_realises = jours_realises;
        this.type_dev_personnel = type_dev_personnel;
    }


	public String getType_dev_personnel() {
		return type_dev_personnel;
	}

	public void setType_dev_personnel(String type_dev_personnel) {
		this.type_dev_personnel = type_dev_personnel;
	}

	public int getDuree_dev_personnel() {
		return duree_dev_personnel;
	}

	public void setDuree_dev_personnel(int duree_dev_personnel) {
		this.duree_dev_personnel = duree_dev_personnel;
	}

	public int getJours_realises() {
		return jours_realises;
	}

	public void setJours_realises(int jours_realises) {
		this.jours_realises = jours_realises;
	}

	public String getHabitude_cible() {
		return habitude_cible;
	}

	public void setHabitude_cible(String habitude_cible) {
		this.habitude_cible = habitude_cible;
	}
}