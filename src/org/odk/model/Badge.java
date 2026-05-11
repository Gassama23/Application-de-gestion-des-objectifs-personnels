package org.odk.model;

public class Badge {

    // Attributs
    private int id;
    private String nom;
    private String description;
    private int conditionStreak;
    
    public Badge() {
    	
    }

    // Constructeur
    public Badge(int id, String nom, String description, int conditionStreak) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.conditionStreak = conditionStreak;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getConditionStreak() {
        return conditionStreak;
    }

    public void setConditionStreak(int conditionStreak) {
        this.conditionStreak = conditionStreak;
    }

    // Méthode
    public void attribuer() {
        System.out.println("Badge attribué !");
    }
}