package org.odk;

import org.odk.model.Admin;
import org.odk.model.Utilisateur;

public class MainTest {

    public static void main(String[] args) {
        System.out.println("=== Application de gestion des objectifs personnels ===");
        System.out.println("=== Test d'inscription et connexion avec JDBC MySQL ===\n");
        
        // Test 1: Inscription d'un utilisateur
        System.out.println("--- Test 1: Inscription d'un utilisateur ---");
        Utilisateur user1 = new Utilisateur("Aicha", "Traore", "aicha.traore@email.com", "password123");
        user1.sInscrire();
        System.out.println();
        
        // Test 2: Inscription d'un admin
        System.out.println("--- Test 2: Inscription d'un admin ---");
        Admin admin1 = new Admin("Idrissa", "Gassama", "idrissa.gassama@email.com", "admin123");
        admin1.sInscrire();
        System.out.println();
        
        // Test 3: Tentative d'inscription avec un email existant
        System.out.println("--- Test 3: Tentative d'inscription avec email existant ---");
        Utilisateur user2 = new Utilisateur("Durand", "Pierre", "aicha.traore@email.com", "password456");
        user2.sInscrire();
        System.out.println();
        
        // Test 4: Connexion d'un utilisateur
        System.out.println("--- Test 4: Connexion d'un utilisateur ---");
        Utilisateur userLogin = new Utilisateur();
        userLogin.setEmail("aicha.traore@email.com");
        userLogin.setMotDePasse("password123");
        userLogin.seConnecter();
        System.out.println();
        
        // Test 5: Connexion d'un admin
        System.out.println("--- Test 5: Connexion d'un admin ---");
        Admin adminLogin = new Admin();
        adminLogin.setEmail("idrissa.gassama@email.com");
        adminLogin.setMotDePasse("admin123");
        adminLogin.seConnecter();
        System.out.println();
        
        // Test 6: Connexion avec mauvais mot de passe
        System.out.println("--- Test 6: Connexion avec mauvais mot de passe ---");
        Utilisateur userBadPassword = new Utilisateur();
        userBadPassword.setEmail("aicha.traore@email.com");
        userBadPassword.setMotDePasse("wrongpassword");
        userBadPassword.seConnecter();
        System.out.println();
        
        // // Test 7: Utilisation des méthodes utilisateur après connexion
        // System.out.println("--- Test 7: Utilisation des méthodes après connexion ---");
        // if (userLogin.getId() > 0) {
        //     userLogin.creerObjectif();
        //     userLogin.consulterObjectif();
        //     userLogin.consulterBadges();
        // }
        // System.out.println();
        
        // // Test 8: Utilisation des méthodes admin après connexion
        // System.out.println("--- Test 8: Utilisation des méthodes admin ---");
        // if (adminLogin.getId() > 0) {
        //     adminLogin.voirStatistique();
        //     adminLogin.voirObjectif();
        //     adminLogin.testerUtilisateur();
        // }
        
        // // Fermer la connexion à la base de données
        // DatabaseConnection.closeConnection();
        
        System.out.println("\n=== Fin des tests ===");
    }
}
