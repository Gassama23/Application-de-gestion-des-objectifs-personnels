package org.odk.model;

import java.util.List;


public class AttentesUtilisateur {

	 private double revenuMensuel;
	    private double epargneActuelle;
	    private double montantVise;
	    private int delaiMois;
	    private int comportementFinancier;
	    private List<String> categoriesDepenses;
	    private int situationDettes;
	    private double montantDettes;
	    private int fondsUrgence;
	    
	    private int objectifSport;
	    private double poidsAPerdre;
	    private int niveau;
	    private int frequenceHebdo;
	    private int dureeSeance;
	    private boolean accesSalle;
	    private int typeEntrainement;
	    private int contraintesSante;

	    private String sujet;
	    private int objectifFinal;
	    private int tempsQuotidien;
	    private int styleApprentissage;
	    private int historique;

	    private int domainePrioritaire;
	    private int niveauDiscipline;
	    private int typeDefi;

	    public AttentesUtilisateur() {
	    }
	 // Getters & Setters

	    // ECONOMIE
	    public double getRevenuMensuel() {
	        return revenuMensuel;
	    }

	    public void setRevenuMensuel(double revenuMensuel) {
	        this.revenuMensuel = revenuMensuel;
	    }

	    public double getEpargneActuelle() {
	        return epargneActuelle;
	    }

	    public void setEpargneActuelle(double epargneActuelle) {
	        this.epargneActuelle = epargneActuelle;
	    }

	    public double getMontantVise() {
	        return montantVise;
	    }

	    public void setMontantVise(double montantVise) {
	        this.montantVise = montantVise;
	    }

	    public int getDelaiMois() {
	        return delaiMois;
	    }

	    public void setDelaiMois(int delaiMois) {
	        this.delaiMois = delaiMois;
	    }

	    public int getComportementFinancier() {
	        return comportementFinancier;
	    }

	    public void setComportementFinancier(int comportementFinancier) {
	        this.comportementFinancier = comportementFinancier;
	    }

	    public List<String> getCategoriesDepenses() {
	        return categoriesDepenses;
	    }

	    public void setCategoriesDepenses(List<String> categoriesDepenses) {
	        this.categoriesDepenses = categoriesDepenses;
	    }

	    public int getSituationDettes() {
	        return situationDettes;
	    }

	    public void setSituationDettes(int situationDettes) {
	        this.situationDettes = situationDettes;
	    }

	    public double getMontantDettes() {
	        return montantDettes;
	    }

	    public void setMontantDettes(double montantDettes) {
	        this.montantDettes = montantDettes;
	    }

	    public int getFondsUrgence() {
	        return fondsUrgence;
	    }

	    public void setFondsUrgence(int fondsUrgence) {
	        this.fondsUrgence = fondsUrgence;
	    }

	    // SPORT
	    public int getObjectifSport() {
	        return objectifSport;
	    }

	    public void setObjectifSport(int objectifSport) {
	        this.objectifSport = objectifSport;
	    }

	    public double getPoidsAPerdre() {
	        return poidsAPerdre;
	    }

	    public void setPoidsAPerdre(double poidsAPerdre) {
	        this.poidsAPerdre = poidsAPerdre;
	    }

	    public int getNiveau() {
	        return niveau;
	    }

	    public void setNiveau(int niveau) {
	        this.niveau = niveau;
	    }

	    public int getFrequenceHebdo() {
	        return frequenceHebdo;
	    }

	    public void setFrequenceHebdo(int frequenceHebdo) {
	        this.frequenceHebdo = frequenceHebdo;
	    }

	    public int getDureeSeance() {
	        return dureeSeance;
	    }

	    public void setDureeSeance(int dureeSeance) {
	        this.dureeSeance = dureeSeance;
	    }

	    public boolean isAccesSalle() {
	        return accesSalle;
	    }

	    public void setAccesSalle(boolean accesSalle) {
	        this.accesSalle = accesSalle;
	    }

	    public int getTypeEntrainement() {
	        return typeEntrainement;
	    }

	    public void setTypeEntrainement(int typeEntrainement) {
	        this.typeEntrainement = typeEntrainement;
	    }

	    public int getContraintesSante() {
	        return contraintesSante;
	    }

	    public void setContraintesSante(int contraintesSante) {
	        this.contraintesSante = contraintesSante;
	    }

	    // APPRENTISSAGE
	    public String getSujet() {
	        return sujet;
	    }

	    public void setSujet(String sujet) {
	        this.sujet = sujet;
	    }

	    public int getObjectifFinal() {
	        return objectifFinal;
	    }

	    public void setObjectifFinal(int objectifFinal) {
	        this.objectifFinal = objectifFinal;
	    }

	    public int getTempsQuotidien() {
	        return tempsQuotidien;
	    }

	    public void setTempsQuotidien(int tempsQuotidien) {
	        this.tempsQuotidien = tempsQuotidien;
	    }

	    public int getStyleApprentissage() {
	        return styleApprentissage;
	    }

	    public void setStyleApprentissage(int styleApprentissage) {
	        this.styleApprentissage = styleApprentissage;
	    }

	    public int getHistorique() {
	        return historique;
	    }

	    public void setHistorique(int historique) {
	        this.historique = historique;
	    }

	    // DEVELOPPEMENT PERSONNEL
	    public int getDomainePrioritaire() {
	        return domainePrioritaire;
	    }

	    public void setDomainePrioritaire(int domainePrioritaire) {
	        this.domainePrioritaire = domainePrioritaire;
	    }

	    public int getNiveauDiscipline() {
	        return niveauDiscipline;
	    }

	    public void setNiveauDiscipline(int niveauDiscipline) {
	        this.niveauDiscipline = niveauDiscipline;
	    }

	    public int getTypeDefi() {
	        return typeDefi;
	    }

	    public void setTypeDefi(int typeDefi) {
	        this.typeDefi = typeDefi;
	    }

	    // Méthode demandée
	    public AttentesUtilisateur obtenir() {
	        return this;
	    }
	}


