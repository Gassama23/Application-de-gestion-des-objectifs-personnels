package org.odk;

import java.util.Scanner;
import org.odk.database.DatabaseConnection;
import org.odk.model.Admin;
import org.odk.model.Utilisateur;

public class MainTest {
    
    private static Scanner scanner = new Scanner(System.in);
    private static Utilisateur utilisateurConnecte = null;
    private static Admin adminConnecte = null;

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║   Application de gestion des objectifs personnels          ║");
        System.out.println("║   Test d'inscription et connexion avec JDBC MySQL          ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        boolean continuer = true;
        
        while (continuer) {
            afficherMenu();
            int choix = lireChoix();
            
            switch (choix) {
                case 1:
                    inscrireUtilisateur();
                    break;
                case 2:
                    inscrireAdmin();
                    break;
                case 3:
                    connecterUtilisateur();
                    break;
                case 4:
                    connecterAdmin();
                    break;
                case 5:
                    menuUtilisateur();
                    break;
                case 6:
                    menuAdmin();
                    break;
                case 7:
                    deconnecter();
                    break;
                case 0:
                    continuer = false;
                    break;
                default:
                    System.out.println("❌ Choix invalide. Veuillez réessayer.\n");
            }
        }
        
        // Fermer la connexion à la base de données
        DatabaseConnection.closeConnection();
        scanner.close();
        
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║   Merci d'avoir utilisé l'application !                   ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
    
    private static void afficherMenu() {
        System.out.println("┌────────────────────────────────────────────────────────────┐");
        System.out.println("│                      MENU PRINCIPAL                        │");
        System.out.println("├────────────────────────────────────────────────────────────┤");
        System.out.println("│  1. Inscrire un utilisateur                                │");
        System.out.println("│  2. Inscrire un administrateur                             │");
        System.out.println("│  3. Se connecter (Utilisateur)                             │");
        System.out.println("│  4. Se connecter (Admin)                                   │");
        System.out.println("│  5. Menu Utilisateur                                       │");
        System.out.println("│  6. Menu Admin                                             │");
        System.out.println("│  7. Se déconnecter                                         │");
        System.out.println("│  0. Quitter                                                │");
        System.out.println("└────────────────────────────────────────────────────────────┘");
        
        if (utilisateurConnecte != null) {
            System.out.println("👤 Connecté en tant que : " + utilisateurConnecte.getEmail() + " (Utilisateur)");
        } else if (adminConnecte != null) {
            System.out.println("👤 Connecté en tant que : " + adminConnecte.getEmail() + " (Admin)");
        } else {
            System.out.println("👤 Non connecté");
        }
        
        System.out.print("\n➤ Votre choix : ");
    }
    
    private static int lireChoix() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void inscrireUtilisateur() {
        System.out.println("\n┌─── INSCRIPTION UTILISATEUR ───────────────────────────────┐");
        
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        
        System.out.print("Email : ");
        String email = scanner.nextLine();
        
        System.out.print("Mot de passe (min 6 caractères) : ");
        String motDePasse = scanner.nextLine();
        
        Utilisateur user = new Utilisateur(nom, prenom, email, motDePasse);
        user.sInscrire();
        
        System.out.println("└───────────────────────────────────────────────────────────┘\n");
    }
    
    private static void inscrireAdmin() {
        System.out.println("\n┌─── INSCRIPTION ADMINISTRATEUR ────────────────────────────┐");
        
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        
        System.out.print("Email : ");
        String email = scanner.nextLine();
        
        System.out.print("Mot de passe (min 6 caractères) : ");
        String motDePasse = scanner.nextLine();
        
        Admin admin = new Admin(nom, prenom, email, motDePasse);
        admin.sInscrire();
        
        System.out.println("└───────────────────────────────────────────────────────────┘\n");
    }
    
    private static void connecterUtilisateur() {
        System.out.println("\n┌─── CONNEXION UTILISATEUR ─────────────────────────────────┐");
        
        System.out.print("Email : ");
        String email = scanner.nextLine();
        
        System.out.print("Mot de passe : ");
        String motDePasse = scanner.nextLine();
        
        Utilisateur user = new Utilisateur();
        user.setEmail(email);
        user.setMotDePasse(motDePasse);
        user.seConnecter();
        
        if (user.getId() > 0) {
            utilisateurConnecte = user;
            System.out.println("✅ Connexion réussie !");
        } else {
            System.out.println("❌ Échec de la connexion.");
        }
        
        System.out.println("└───────────────────────────────────────────────────────────┘\n");
    }
    
    private static void connecterAdmin() {
        System.out.println("\n┌─── CONNEXION ADMINISTRATEUR ──────────────────────────────┐");
        
        System.out.print("Email : ");
        String email = scanner.nextLine();
        
        System.out.print("Mot de passe : ");
        String motDePasse = scanner.nextLine();
        
        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setMotDePasse(motDePasse);
        admin.seConnecter();
        
        if (admin.getId() > 0) {
            adminConnecte = admin;
            System.out.println("✅ Connexion réussie !");
        } else {
            System.out.println("❌ Échec de la connexion.");
        }
        
        System.out.println("└───────────────────────────────────────────────────────────┘\n");
    }
    
    private static void menuUtilisateur() {
        if (utilisateurConnecte == null) {
            System.out.println("\n❌ Vous devez être connecté en tant qu'utilisateur.\n");
            return;
        }
        
        System.out.println("\n┌─── MENU UTILISATEUR ──────────────────────────────────────┐");
        System.out.println("│  1. Créer un objectif                                      │");
        System.out.println("│  2. Consulter mes objectifs                                │");
        System.out.println("│  3. Consulter mes badges                                   │");
        System.out.println("│  4. Consulter ma progression                               │");
        System.out.println("│  5. Consulter mes notifications                            │");
        System.out.println("│  6. Consulter mes conseils                                 │");
        System.out.println("│  7. Consulter mon planning                                 │");
        System.out.println("│  8. Consulter mes actions quotidiennes                     │");
        System.out.println("│  9. Consulter mes attentes                                 │");
        System.out.println("│  0. Retour au menu principal                               │");
        System.out.println("└───────────────────────────────────────────────────────────┘");
        System.out.print("➤ Votre choix : ");
        
        int choix = lireChoix();
        System.out.println();
        
        switch (choix) {
            case 1:
                utilisateurConnecte.creerObjectif();
                break;
            case 2:
                utilisateurConnecte.consulterObjectif();
                break;
            case 3:
                utilisateurConnecte.consulterBadges();
                break;
            case 4:
                utilisateurConnecte.consulterProgression();
                break;
            case 5:
                utilisateurConnecte.consulterNotification();
                break;
            case 6:
                utilisateurConnecte.consulterConseil();
                break;
            case 7:
                utilisateurConnecte.consulterPlanning();
                break;
            case 8:
                utilisateurConnecte.consulterActionQuotidienne();
                break;
            case 9:
                utilisateurConnecte.consulterAttentes();
                break;
            case 0:
                System.out.println("Retour au menu principal...\n");
                break;
            default:
                System.out.println("❌ Choix invalide.\n");
        }
    }
    
    private static void menuAdmin() {
        if (adminConnecte == null) {
            System.out.println("\n❌ Vous devez être connecté en tant qu'administrateur.\n");
            return;
        }
        
        System.out.println("\n┌─── MENU ADMINISTRATEUR ───────────────────────────────────┐");
        System.out.println("│  1. Voir les statistiques                                  │");
        System.out.println("│  2. Voir les objectifs                                     │");
        System.out.println("│  3. Tester un utilisateur                                  │");
        System.out.println("│  4. Gérer les utilisateurs                                 │");
        System.out.println("│  0. Retour au menu principal                               │");
        System.out.println("└───────────────────────────────────────────────────────────┘");
        System.out.print("➤ Votre choix : ");
        
        int choix = lireChoix();
        System.out.println();
        
        switch (choix) {
            case 1:
                adminConnecte.voirStatistique();
                break;
            case 2:
                adminConnecte.voirObjectif();
                break;
            case 3:
                adminConnecte.testerUtilisateur();
                break;
            case 4:
                adminConnecte.gererUtilisateur();
                break;
            case 0:
                System.out.println("Retour au menu principal...\n");
                break;
            default:
                System.out.println("❌ Choix invalide.\n");
        }
    }
    
    private static void deconnecter() {
        if (utilisateurConnecte != null || adminConnecte != null) {
            utilisateurConnecte = null;
            adminConnecte = null;
            System.out.println("\n✅ Déconnexion réussie.\n");
        } else {
            System.out.println("\n❌ Vous n'êtes pas connecté.\n");
        }
    }
}
