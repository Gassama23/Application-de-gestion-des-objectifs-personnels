package org.odk.service;

import org.odk.model.User;
import org.odk.repository.UserRepository;

/**
 * Service pour la gestion des utilisateurs
 * Contient la logique métier
 */
public class UserService {
    
    private UserRepository userRepository;
    
    public UserService() {
        this.userRepository = new UserRepository();
    }
    
    /**
     * Inscrire un nouvel utilisateur
     * @param user L'utilisateur à inscrire
     * @return true si succès, false sinon
     */
    public boolean inscrire(User user) {
        // Validation métier
        if (user == null) {
            System.err.println("Erreur : L'utilisateur ne peut pas être null");
            return false;
        }
        
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            System.err.println("Erreur : L'email est obligatoire");
            return false;
        }
        
        if (user.getMotDePasse() == null || user.getMotDePasse().length() < 6) {
            System.err.println("Erreur : Le mot de passe doit contenir au moins 6 caractères");
            return false;
        }
        
        // Vérifier si l'email existe déjà
        if (userRepository.emailExiste(user.getEmail())) {
            System.err.println("Erreur : Cet email est déjà utilisé : " + user.getEmail());
            return false;
        }
        
        // Appeler le repository pour l'inscription
        return userRepository.inscrireUtilisateur(user);
    }
    
    /**
     * Connecter un utilisateur
     * @param email L'email de l'utilisateur
     * @param motDePasse Le mot de passe
     * @return L'utilisateur connecté ou null si échec
     */
    public User connecter(String email, String motDePasse) {
        // Validation métier
        if (email == null || email.trim().isEmpty()) {
            System.err.println("Erreur : L'email est obligatoire");
            return null;
        }
        
        if (motDePasse == null || motDePasse.trim().isEmpty()) {
            System.err.println("Erreur : Le mot de passe est obligatoire");
            return null;
        }
        
        // Appeler le repository pour la connexion
        User user = userRepository.connecterUtilisateur(email, motDePasse);
        
        if (user != null) {
            System.out.println("✓ Connexion réussie pour : " + user.getEmail());
        } else {
            System.err.println("✗ Échec de la connexion. Vérifiez vos identifiants.");
        }
        
        return user;
    }
    
    /**
     * Vérifier si un email existe
     * @param email L'email à vérifier
     * @return true si l'email existe, false sinon
     */
    public boolean emailExiste(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return userRepository.emailExiste(email);
    }
    
    /**
     * Valider le format d'un email
     * @param email L'email à valider
     * @return true si valide, false sinon
     */
    public boolean validerEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        // Regex simple pour validation email
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
    }
    
    /**
     * Valider la force d'un mot de passe
     * @param motDePasse Le mot de passe à valider
     * @return true si valide, false sinon
     */
    public boolean validerMotDePasse(String motDePasse) {
        if (motDePasse == null) {
            return false;
        }
        // Au moins 6 caractères
        return motDePasse.length() >= 6;
    }
}
