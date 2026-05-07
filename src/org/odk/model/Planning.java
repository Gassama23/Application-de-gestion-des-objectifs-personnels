package org.odk.model;

import java.util.Date;
import java.util.List;

public class Planning {

    private int id;
    private String titre;
    private Date dateCreation;
    private boolean actif;

    // Clé étrangère (relation 1-1 avec Objectif)
    private Objectif objectif;

    // Relations 1-N
    private List<PlanningDetail> sections;
    private List<ActionQuotidienne> actions;
    private List<Rappel> rappels;

    public Planning() {
    }

    public Planning(int id, String titre, Date dateCreation, boolean actif, Objectif objectif,
                    List<PlanningDetail> sections,
                    List<ActionQuotidienne> actions,
                    List<Rappel> rappels) {
        this.id = id;
        this.titre = titre;
        this.dateCreation = dateCreation;
        this.actif = actif;
        this.objectif = objectif;
        this.sections = sections;
        this.actions = actions;
        this.rappels = rappels;
    }

    // ===== GETTERS & SETTERS =====

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

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public Objectif getObjectif() {
        return objectif;
    }

    public void setObjectif(Objectif objectif) {
        this.objectif = objectif;
    }

    public List<PlanningDetail> getSections() {
        return sections;
    }

    public void setSections(List<PlanningDetail> sections) {
        this.sections = sections;
    }

    public List<ActionQuotidienne> getActions() {
        return actions;
    }

    public void setActions(List<ActionQuotidienne> actions) {
        this.actions = actions;
    }

    public List<Rappel> getRappels() {
        return rappels;
    }

    public void setRappels(List<Rappel> rappels) {
        this.rappels = rappels;
    }
}