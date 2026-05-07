package org.odk.model;

import java.util.Date;
import org.odk.enums.EnumRole;
import org.odk.service.AdminService;

/**
 * Classe Admin - Hérite de User
 * Représente un administrateur du système
 */
public class Admin extends User {
    
    private AdminService adminService;
    
    public Admin() {
        super();
        this.setRole(EnumRole.ADMIN);
        this.adminService = new AdminService();
    }
    
    public Admin(String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse, EnumRole.ADMIN);
        this.adminService = new AdminService();
    }
    
    // Implémentation de la méthode abstraite sInscrire()
    @Override
    public void sInscrire() {
        System.out.println("=== Inscription Admin ===");
        
        // Définir la date d'inscription
        this.setDateInscription(new Date());
        
        // Appeler le service pour l'inscription
        if (adminService.inscrireAdmin(this)) {
            System.out.println("✓ Inscription admin réussie pour : " + this.getNom() + " " + this.getPrenom());
            System.out.println("  ID: " + this.getId());
            System.out.println("  Email: " + this.getEmail());
        } else {
            System.out.println("✗ Échec de l'inscription admin.");
        }
    }
    
    // Méthode de connexion spécifique à Admin
    @Override
    public void seConnecter() {
        System.out.println("=== Connexion Admin ===");
        
        // Appeler le service pour la connexion
        Admin admin = adminService.connecterAdmin(this.getEmail(), this.getMotDePasse());
        
        if (admin != null) {
            // Copier les données de l'admin connecté
            this.setId(admin.getId());
            this.setNom(admin.getNom());
            this.setPrenom(admin.getPrenom());
            this.setDateInscription(admin.getDateInscription());
        }
    }
    
    /**
     * Voir les statistiques globales du système
     */
    public void voirStatistique() {
        adminService.voirStatistiques(this);
    }
    
    /**
     * Voir tous les objectifs de tous les utilisateurs
     */
    public void voirObjectif() {
        adminService.voirTousLesObjectifs(this);
    }
    
    /**
     * Tester les fonctionnalités utilisateur
     */
    public void testerUtilisateur() {
        adminService.testerFonctionnalitesUtilisateur(this);
    }
}
