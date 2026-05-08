package org.odk.model;

import java.time.LocalDate;
import org.odk.enums.EnumStatut;

public class ObjectifEconomie extends Objectif{

    protected String type_economie;
    protected int montant_cible;
    protected int montant_epargne;
    protected int delai_mois;

    public ObjectifEconomie(
		int id, 
		String nom_objectif, 
		int utilsateur_id, 
		String description, 
		LocalDate date_debut, 
		LocalDate date_fin, 
		EnumStatut status,

		int delai_mois, 
		int montant_cible, 
		int montant_epargne, 
		String type_economie
		) {
        super(id, nom_objectif, utilsateur_id, description, date_debut, date_fin, status);
        this.delai_mois = delai_mois;
        this.montant_cible = montant_cible;
        this.montant_epargne = montant_epargne;
        this.type_economie = type_economie;
    }



	public String getType_economie() {
		return type_economie;
	}

	public void setType_economie(String type_economie) {
		this.type_economie = type_economie;
	}

	public int getMontant_cible() {
		return montant_cible;
	}

	public void setMontant_cible(int montant_cible) {
		this.montant_cible = montant_cible;
	}

	public int getMontant_epargne() {
		return montant_epargne;
	}

	public void setMontant_epargne(int montant_epargne) {
		this.montant_epargne = montant_epargne;
	}

	public int getDelai_mois() {
		return delai_mois;
	}

	public void setDelai_mois(int delai_mois) {
		this.delai_mois = delai_mois;
	}
}
