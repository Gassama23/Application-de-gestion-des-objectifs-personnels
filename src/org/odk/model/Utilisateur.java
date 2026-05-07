package org.odk.model;

import java.util.Date;
import org.odk.enums.EnumRole;
import org.odk.service.UtilisateurService;

/**
 * Classe Utilisateur - Hérite de User
 * Représente un utilisateur régulier du système
 */
public class Utilisateur extends User {
    
    private UtilisateurService utilisateurService;
    
    public Utilisateur() {
        super();
        this.setRole(EnumRole.UTILISATEUR);
        this.utilisateurService = new UtilisateurService();
    }
    
    public Utilisateur(String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse, EnumRole.UTILISATEUR);
        this.utilisateurService = new UtilisateurService();
    }
    
    // Implémentation de la méthode abstraite sInscrire()
    @Override
    public void sInscrire() {
        System.out.println("=== Inscription Utilisateur ===");
        
        // Définir la date d'inscription
        this.setDateInscription(new Date());
        
        // Appeler le service pour l'inscription
        if (utilisateurService.inscrireUtilisateur(this)) {
            System.out.println("✓ Inscription réussie pour : " + this.getNom() + " " + this.getPrenom());
            System.out.println("  ID: " + this.getId());
            System.out.println("  Email: " + this.getEmail());
        } else {
            System.out.println("✗ Échec de l'inscription.");
        }
    }
    
    // Méthode de connexion spécifique à Utilisateur
    @Override
    public void seConnecter() {
        System.out.println("=== Connexion Utilisateur ===");
        
        // Appeler le service pour la connexion
        Utilisateur utilisateur = utilisateurService.connecterUtilisateur(this.getEmail(), this.getMotDePasse());
        
        if (utilisateur != null) {
            // Copier les données de l'utilisateur connecté
            this.setId(utilisateur.getId());
            this.setNom(utilisateur.getNom());
            this.setPrenom(utilisateur.getPrenom());
            this.setDateInscription(utilisateur.getDateInscription());
        }
    }
    
    // Méthodes spécifiques à Utilisateur selon l'UML
    
    /**
     * Modifier le profil de l'utilisateur
     */
    public void modifierProfil() {
        utilisateurService.modifierProfil(this);
    }
    
    /**
     * Créer un nouvel objectif
     */
    public void creerObjectif() {
        utilisateurService.creerObjectif(this);
    }
    
    /**
     * Consulter ses objectifs
     */
    public void consulterObjectif() {
        utilisateurService.consulterObjectifs(this);
    }
    
    /**
     * Consulter son historique
     */
    public void consulterHistorique() {
        utilisateurService.consulterHistorique(this);
    }
    
    /**
     * Consulter ses notifications
     */
    public void consulterNotification() {
        utilisateurService.consulterNotifications(this);
    }
    
    /**
     * Consulter ses badges
     */
    public void consulterBadges() {
        utilisateurService.consulterBadges(this);
    }
    
    /**
     * Configurer un rappel
     */
    public void configurerRappel() {
        utilisateurService.configurerRappel(this);
    }
    
    /**
     * Demander des conseils
     */
    public void demanderConseils() {
        utilisateurService.demanderConseils(this);
    }
    
    /**
     * Valider une étape
     */
    public void validerEtape() {
        utilisateurService.validerEtape(this);
    }
}
