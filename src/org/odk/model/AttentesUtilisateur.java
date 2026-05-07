package org.odk.model;

public class AttentesUtilisateur {

    private int id;

    // ===== Économie =====
    private double revenuMensuel;
    private double epargneActuelle;
    private double montantVise;
    private int delaiMois;
    private int comportementFinancier;
    private String categoriesDepenses;
    private int situationDettes;
    private double montantDettes;
    private int fondsUrgence;

    // ===== Sport =====
    private int objectifSport;
    private double poidsAPerdre;
    private int niveau;
    private int frequenceHebdo;
    private int dureeSeance;
    private boolean accesSalle;
    private int typeEntrainement;
    private int contraintesSante;

    // ===== Apprentissage =====
    private String sujet;
    private int objectifFinal;
    private int tempsQuotidien;
    private int styleApprentissage;
    private int historique;

    // ===== Développement personnel =====
    private int domainePrioritaire;
    private int niveauDiscipline;
    private int typeDefi;

    // ===== Relations (clés étrangères) =====
    private Utilisateur utilisateur;
    private Objectif objectif;

    public AttentesUtilisateur() {
    }

    public AttentesUtilisateur(int id,
                               double revenuMensuel,
                               double epargneActuelle,
                               double montantVise,
                               int delaiMois,
                               int comportementFinancier,
                               String categoriesDepenses,
                               int situationDettes,
                               double montantDettes,
                               int fondsUrgence,
                               int objectifSport,
                               double poidsAPerdre,
                               int niveau,
                               int frequenceHebdo,
                               int dureeSeance,
                               boolean accesSalle,
                               int typeEntrainement,
                               int contraintesSante,
                               String sujet,
                               int objectifFinal,
                               int tempsQuotidien,
                               int styleApprentissage,
                               int historique,
                               int domainePrioritaire,
                               int niveauDiscipline,
                               int typeDefi,
                               Utilisateur utilisateur,
                               Objectif objectif) {

        this.id = id;
        this.revenuMensuel = revenuMensuel;
        this.epargneActuelle = epargneActuelle;
        this.montantVise = montantVise;
        this.delaiMois = delaiMois;
        this.comportementFinancier = comportementFinancier;
        this.categoriesDepenses = categoriesDepenses;
        this.situationDettes = situationDettes;
        this.montantDettes = montantDettes;
        this.fondsUrgence = fondsUrgence;

        this.objectifSport = objectifSport;
        this.poidsAPerdre = poidsAPerdre;
        this.niveau = niveau;
        this.frequenceHebdo = frequenceHebdo;
        this.dureeSeance = dureeSeance;
        this.accesSalle = accesSalle;
        this.typeEntrainement = typeEntrainement;
        this.contraintesSante = contraintesSante;

        this.sujet = sujet;
        this.objectifFinal = objectifFinal;
        this.tempsQuotidien = tempsQuotidien;
        this.styleApprentissage = styleApprentissage;
        this.historique = historique;

        this.domainePrioritaire = domainePrioritaire;
        this.niveauDiscipline = niveauDiscipline;
        this.typeDefi = typeDefi;

        this.utilisateur = utilisateur;
        this.objectif = objectif;
    }

    // ===== GETTERS & SETTERS =====

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getCategoriesDepenses() {
        return categoriesDepenses;
    }

    public void setCategoriesDepenses(String categoriesDepenses) {
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

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Objectif getObjectif() {
        return objectif;
    }

    public void setObjectif(Objectif objectif) {
        this.objectif = objectif;
    }
}