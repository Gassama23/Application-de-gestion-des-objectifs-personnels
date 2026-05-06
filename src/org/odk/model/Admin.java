package org.odk.model;

import org.odk.enums.EnumRole;

public class Admin extends User {
    
    public Admin() {
        super();
        this.setRole(EnumRole.ADMIN);
    }
    
    public Admin(String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse, EnumRole.ADMIN);
    }
    
    // Méthodes spécifiques à Admin selon l'UML
    @Override
    public void seConnecter() {
        System.out.println("Connexion Admin : " + this.getEmail());
        // TODO: Implémenter la logique de connexion admin
    }
    
    public void voirStatistique() {
        System.out.println("Affichage des statistiques globales");
        // TODO: Implémenter la logique pour voir les statistiques
    }
    
    public void voirObjectif() {
        System.out.println("Affichage de tous les objectifs des utilisateurs");
        // TODO: Implémenter la logique pour voir tous les objectifs
    }
    
    public void testerUtilisateur() {
        System.out.println("Test des fonctionnalités utilisateur");
        // TODO: Implémenter la logique de test
    }
}
