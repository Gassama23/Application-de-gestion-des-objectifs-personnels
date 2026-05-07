package org.odk.model;

import java.util.Date;

import org.odk.enums.EnumRole;

public class User {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private EnumRole role;
    private Date dateInscription;
    
    // Constructeurs
    public User() {
    }
    
    public User(String nom, String prenom, String email, String motDePasse, EnumRole role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
        this.dateInscription = new Date();
    }
    
    // Méthodes de l'UML
    public void seConnecter() {
        System.out.println("Connexion de l'utilisateur : " + this.email);
        // TODO: Implémenter la logique de connexion
    }
    
    public void sInscrire() {
        System.out.println("Inscription de l'utilisateur : " + this.nom + " " + this.prenom);
        this.dateInscription = new Date();
        // TODO: Implémenter la logique d'inscription
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
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMotDePasse() {
        return motDePasse;
    }
    
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    public EnumRole getRole() {
        return role;
    }
    
    public void setRole(EnumRole role) {
        this.role = role;
    }
    
    public Date getDateInscription() {
        return dateInscription;
    }
    
    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }
}
