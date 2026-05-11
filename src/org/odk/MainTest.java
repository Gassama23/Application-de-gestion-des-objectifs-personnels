package org.odk;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import org.odk.enums.EnumStatut;
import org.odk.model.Admin;
import org.odk.model.ObjectifEconomie;
import org.odk.model.Utilisateur;
import org.odk.service.ObjectifEconomieService;

public class MainTest {
    
	public static int c2;
    static ObjectifEconomieService objectifEconomieService = new ObjectifEconomieService();
	private static Scanner scanner = new Scanner(System.in);
    private static Utilisateur utilisateurConnecte = null;
    private static Admin adminConnecte = null;
    private static void afficherMenuObjectif() {
        System.out.println("\n┌─── TYPE OBJECTIF ─────────────────────────────────────────┐");
        System.out.println("│  1. Objectif Économie                                      │");
        System.out.println("│  2. Objectif Sport                                        │");
        System.out.println("│  3. Objectif Apprentissage                                │");
        System.out.println("│  4. Objectif Développement Personnel                      │");
        System.out.println("└───────────────────────────────────────────────────────────┘");
        System.out.print("➤ Votre choix objectif : ");
    }
private static void insererObjectifEconomie() {
        
        ObjectifEconomie economie = new ObjectifEconomie();
        
        System.out.println("\n┌─── CRÉATION OBJECTIF ÉCONOMIE ──────────────────────────┐");
        
        // Consommer le retour chariot
        scanner.nextLine();
        
        System.out.print("│ nom_objectif : ");
        String nom_objectif = scanner.nextLine();
        
        System.out.print("│ description : ");
        String description = scanner.nextLine();
        
        System.out.print("│ date_debut (AAAA MM JJ) : ");
        int annee = scanner.nextInt();
        int mois = scanner.nextInt();
        int jour = scanner.nextInt();
        LocalDate date_debut = LocalDate.of(annee, mois, jour);
        
        System.out.print("│ date_fin (AAAA MM JJ) : ");
        int anneef = scanner.nextInt();
        int moisf = scanner.nextInt();
        int jourf = scanner.nextInt();
        LocalDate date_fin = LocalDate.of(anneef, moisf, jourf);
        
        System.out.print("│ montant cible : ");
        int montant = scanner.nextInt();
        
        System.out.print("│ montant epargne : ");
        int montant_epargne = scanner.nextInt();
        
        System.out.print("│ delai (mois) : ");
        int delaismois = scanner.nextInt();
        
        // l'attribution automatique du status statut
       
        EnumStatut status = ObjectifEconomieService.attributionAutomatiqueStatu(date_debut,date_fin);
        
        // Remplir l'objet
        economie.setNom_objectif(nom_objectif);
        economie.setDescription(description);
        economie.setMontant_cible(montant);
        economie.setMontant_epargne(montant_epargne);
        economie.setDelai_mois(delaismois);
        economie.setStatus(status);
        economie.setDate_debut(date_debut);
        economie.setDate_fin(date_fin);
        economie.setUtilsateur_id(utilisateurConnecte.getId());
        // Appel service
        try {
			objectifEconomieService.creerObjectifEconomie(economie);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println("└───────────────────────────────────────────────────────────┘\n");
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
        System.out.println(" Connecté en tant que : " + utilisateurConnecte.getEmail() + " (Utilisateur)");
    } else if (adminConnecte != null) {
        System.out.println(" Connecté en tant que : " + adminConnecte.getEmail() + " (Admin)");
    } else {
        System.out.println(" Non connecté");
    }
    
    System.out.print("\n Votre choix : ");
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
    System.out.print(" Votre choix : ");
    
    int choix = lireChoix();
    System.out.println();
    
    switch (choix) {
        case 1:
        	afficherMenuObjectif();
            c2 = scanner.nextInt();
            
            switch (c2) {
                case 1:
                    insererObjectifEconomie();
                    break;
                case 2:
                    System.out.println("⏳ Objectif Sport - À implémenter");
                    break;
                case 3:
                    System.out.println("⏳ Objectif Apprentissage - À implémenter");
                    break;
                case 4:
                    System.out.println("⏳ Objectif Développement Personnel - À implémenter");
                    break;
                default:
                    System.out.println("❌ Choix invalide pour le type d'objectif");
                    break;
            }
            break;
            
        case 2:
            //utilisateurConnecte.consulterObjectif();
		try {
			ObjectifEconomieService.ListeObjectifForOneUser(utilisateurConnecte.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        	System.out.println("choisx 2 ");
            break;
        case 3:
            utilisateurConnecte.consulterBadges();
            break;
        case 4:
           // utilisateurConnecte.consulterProgression();
            break;
        case 5:
            utilisateurConnecte.consulterNotification();
            break;
        case 6:
            //utilisateurConnecte.consulterConseil();
            break;
        case 7:
           // utilisateurConnecte.consulterPlanning();
            break;
        case 8:
            //utilisateurConnecte.consulterActionQuotidienne();
            break;
        case 9:
            //utilisateurConnecte.consulterAttentes();
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
        System.out.println("\n Déconnexion réussie.\n");
    } else {
        System.out.println("\n❌ Vous n'êtes pas connecté.\n");
    }
}
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
    
   
}
