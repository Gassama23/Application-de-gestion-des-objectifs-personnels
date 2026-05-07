package org.odk.service;

import org.odk.model.Utilisateur;
import org.odk.model.User;
import org.odk.enums.EnumRole;

/**
 * Service pour la gestion des utilisateurs réguliers
 * Contient la logique métier spécifique aux utilisateurs
 */
public class UtilisateurService {
    
    private UserService userService;
    
    public UtilisateurService() {
        this.userService = new UserService();
    }
    
    /**
     * Inscrire un nouvel utilisateur
     * @param utilisateur L'utilisateur à inscrire
     * @return true si succès, false sinon
     */
    public boolean inscrireUtilisateur(Utilisateur utilisateur) {
        // Utiliser la validation du UserService
        return userService.inscrire(utilisateur);
    }
    
    /**
     * Connecter un utilisateur
     * @param email L'email de l'utilisateur
     * @param motDePasse Le mot de passe
     * @return L'utilisateur connecté ou null si échec
     */
    public Utilisateur connecterUtilisateur(String email, String motDePasse) {
        User user = userService.connecter(email, motDePasse);
        
        // Vérifier que c'est bien un utilisateur régulier
        if (user != null && user.getRole() == EnumRole.UTILISATEUR) {
            if (user instanceof Utilisateur) {
                return (Utilisateur) user;
            }
        } else if (user != null) {
            System.err.println("Erreur : Cet utilisateur n'est pas un utilisateur régulier");
        }
        
        return null;
    }
    
    /**
     * Modifier le profil d'un utilisateur
     * @param utilisateur L'utilisateur dont on modifie le profil
     */
    public void modifierProfil(Utilisateur utilisateur) {
        if (utilisateur == null || utilisateur.getId() <= 0) {
            System.err.println("Erreur : Utilisateur non connecté");
            return;
        }
        
        System.out.println("Modification du profil de " + utilisateur.getNom());
        // TODO: Implémenter la logique de modification
    }
    
    /**
     * Créer un objectif pour un utilisateur
     * @param utilisateur L'utilisateur qui crée l'objectif
     */
    public void creerObjectif(Utilisateur utilisateur) {
        if (utilisateur == null || utilisateur.getId() <= 0) {
            System.err.println("Erreur : Utilisateur non connecté");
            return;
        }
        
        System.out.println("Création d'un objectif pour " + utilisateur.getPrenom());
        // TODO: Implémenter la logique de création d'objectif
    }
    
    /**
     * Consulter les objectifs d'un utilisateur
     * @param utilisateur L'utilisateur qui consulte ses objectifs
     */
    public void consulterObjectifs(Utilisateur utilisateur) {
        if (utilisateur == null || utilisateur.getId() <= 0) {
            System.err.println("Erreur : Utilisateur non connecté");
            return;
        }
        
        System.out.println("Consultation des objectifs de " + utilisateur.getPrenom());
        // TODO: Implémenter la logique de consultation
    }
    
    /**
     * Consulter l'historique d'un utilisateur
     * @param utilisateur L'utilisateur qui consulte son historique
     */
    public void consulterHistorique(Utilisateur utilisateur) {
        if (utilisateur == null || utilisateur.getId() <= 0) {
            System.err.println("Erreur : Utilisateur non connecté");
            return;
        }
        
        System.out.println("Consultation de l'historique de " + utilisateur.getPrenom());
        // TODO: Implémenter la logique de consultation
    }
    
    /**
     * Consulter les notifications d'un utilisateur
     * @param utilisateur L'utilisateur qui consulte ses notifications
     */
    public void consulterNotifications(Utilisateur utilisateur) {
        if (utilisateur == null || utilisateur.getId() <= 0) {
            System.err.println("Erreur : Utilisateur non connecté");
            return;
        }
        
        System.out.println("Consultation des notifications de " + utilisateur.getPrenom());
        // TODO: Implémenter la logique de consultation
    }
    
    /**
     * Consulter les badges d'un utilisateur
     * @param utilisateur L'utilisateur qui consulte ses badges
     */
    public void consulterBadges(Utilisateur utilisateur) {
        if (utilisateur == null || utilisateur.getId() <= 0) {
            System.err.println("Erreur : Utilisateur non connecté");
            return;
        }
        
        System.out.println("Consultation des badges de " + utilisateur.getPrenom());
        // TODO: Implémenter la logique de consultation
    }
    
    /**
     * Configurer un rappel pour un utilisateur
     * @param utilisateur L'utilisateur qui configure le rappel
     */
    public void configurerRappel(Utilisateur utilisateur) {
        if (utilisateur == null || utilisateur.getId() <= 0) {
            System.err.println("Erreur : Utilisateur non connecté");
            return;
        }
        
        System.out.println("Configuration d'un rappel pour " + utilisateur.getPrenom());
        // TODO: Implémenter la logique de configuration
    }
    
    /**
     * Demander des conseils pour un utilisateur
     * @param utilisateur L'utilisateur qui demande des conseils
     */
    public void demanderConseils(Utilisateur utilisateur) {
        if (utilisateur == null || utilisateur.getId() <= 0) {
            System.err.println("Erreur : Utilisateur non connecté");
            return;
        }
        
        System.out.println("Demande de conseils pour " + utilisateur.getPrenom());
        // TODO: Implémenter la logique de conseils
    }
    
    /**
     * Valider une étape pour un utilisateur
     * @param utilisateur L'utilisateur qui valide l'étape
     */
    public void validerEtape(Utilisateur utilisateur) {
        if (utilisateur == null || utilisateur.getId() <= 0) {
            System.err.println("Erreur : Utilisateur non connecté");
            return;
        }
        
        System.out.println("Validation d'une étape pour " + utilisateur.getPrenom());
        // TODO: Implémenter la logique de validation
    }
}
