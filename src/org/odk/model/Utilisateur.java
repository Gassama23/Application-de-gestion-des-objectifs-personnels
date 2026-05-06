package org.odk.model;

public class Utilisateur extends User {
    
    public Utilisateur() {
        super();
        this.setRole(EnumRole.UTILISATEUR);
    }
    
    public Utilisateur(String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse, EnumRole.UTILISATEUR);
    }
    
    // Méthodes spécifiques à Utilisateur selon l'UML
    public void modifierProfil() {
        System.out.println("Modification du profil de " + this.getNom());
        // TODO: Implémenter la logique
    }
    
    public void creerObjectif() {
        System.out.println("Création d'un objectif");
        // TODO: Implémenter la logique
    }
    
    public void consulterObjectif() {
        System.out.println("Consultation des objectifs");
        // TODO: Implémenter la logique
    }
    
    public void consulterHistorique() {
        System.out.println("Consultation de l'historique");
        // TODO: Implémenter la logique
    }
    
    public void consulterNotification() {
        System.out.println("Consultation des notifications");
        // TODO: Implémenter la logique
    }
    
    public void consulterBadges() {
        System.out.println("Consultation des badges");
        // TODO: Implémenter la logique
    }
    
    public void configurerRappel() {
        System.out.println("Configuration des rappels");
        // TODO: Implémenter la logique
    }
    
    public void demanderConseils() {
        System.out.println("Demande de conseils");
        // TODO: Implémenter la logique
    }
    
    public void validerEtape() {
        System.out.println("Validation d'une étape");
        // TODO: Implémenter la logique
    }
}
