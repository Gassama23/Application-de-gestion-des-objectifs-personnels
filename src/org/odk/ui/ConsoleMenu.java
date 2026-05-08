package org.odk.ui;

import org.odk.enums.EnumRole;
import org.odk.model.Admin;
import org.odk.model.User;
import org.odk.model.Utilisateur;
import org.odk.util.SaisieHelper;

/**
 * ConsoleMenu
 *
 * Rôle :
 * - lancer l'application
 * - afficher le menu principal
 * - rediriger selon le rôle : UTILISATEUR ou ADMIN
 */
public class ConsoleMenu {

    private final AuthConsoleView authConsoleView;
    private final AdminConsoleView adminConsoleView;

    private User utilisateurConnecte;

    public ConsoleMenu() {
        this.authConsoleView = new AuthConsoleView();
        this.adminConsoleView = new AdminConsoleView();
    }

    public void lancer() {

        boolean continuer = true;

        while (continuer) {

            if (utilisateurConnecte == null) {
                continuer = afficherMenuVisiteur();
            } else {
                continuer = afficherMenuConnecte();
            }
        }

        afficherAuRevoir();
    }

    private boolean afficherMenuVisiteur() {

        afficherHeader();

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║             MENU PRINCIPAL          ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ 1. Authentification                 ║");
        System.out.println("║ 0. Quitter                          ║");
        System.out.println("╚══════════════════════════════════════╝");

        int choix = SaisieHelper.lireChoix("Votre choix : ", 0, 1);

        switch (choix) {
            case 1:
                utilisateurConnecte = authConsoleView.afficherMenuAuthentification();
                return true;

            case 0:
                return false;

            default:
                return true;
        }
    }

    private boolean afficherMenuConnecte() {

        if (utilisateurConnecte.getRole() == EnumRole.ADMIN) {
            return afficherMenuAdmin();
        }

        if (utilisateurConnecte.getRole() == EnumRole.UTILISATEUR) {
            return afficherMenuUtilisateur();
        }

        System.out.println("Rôle inconnu.");
        utilisateurConnecte = null;
        return true;
    }

    private boolean afficherMenuUtilisateur() {

        Utilisateur utilisateur = (Utilisateur) utilisateurConnecte;

        afficherHeaderUtilisateur(utilisateur);

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║           MENU UTILISATEUR          ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ 1. Créer un objectif                ║");
        System.out.println("║ 2. Consulter mes objectifs          ║");
        System.out.println("║ 3. Consulter mon historique         ║");
        System.out.println("║ 4. Consulter mes notifications      ║");
        System.out.println("║ 5. Consulter mes badges             ║");
        System.out.println("║ 6. Configurer un rappel             ║");
        System.out.println("║ 7. Demander des conseils            ║");
        System.out.println("║ 8. Valider une étape                ║");
        System.out.println("║ 9. Se déconnecter                   ║");
        System.out.println("║ 0. Quitter                          ║");
        System.out.println("╚══════════════════════════════════════╝");

        int choix = SaisieHelper.lireChoix("Votre choix : ", 0, 9);

        switch (choix) {
            case 1 -> utilisateur.creerObjectif();
            case 2 -> utilisateur.consulterObjectif();
            case 3 -> utilisateur.consulterHistorique();
            case 4 -> utilisateur.consulterNotification();
            case 5 -> utilisateur.consulterBadges();
            case 6 -> utilisateur.configurerRappel();
            case 7 -> utilisateur.demanderConseils();
            case 8 -> utilisateur.validerEtape();

            case 9 -> {
                utilisateurConnecte = null;
                System.out.println("✓ Déconnexion réussie.");
                return true;
            }

            case 0 -> {
                return false;
            }
        }

        SaisieHelper.pause();
        return true;
    }

    private boolean afficherMenuAdmin() {

        Admin admin = (Admin) utilisateurConnecte;

        afficherHeaderAdmin(admin);

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║          MENU ADMINISTRATEUR        ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ 1. Voir statistiques                ║");
        System.out.println("║ 2. Voir tous les objectifs          ║");
        System.out.println("║ 3. Tester fonctionnalités user      ║");
        System.out.println("║ 4. Espace admin complet             ║");
        System.out.println("║ 9. Se déconnecter                   ║");
        System.out.println("║ 0. Quitter                          ║");
        System.out.println("╚══════════════════════════════════════╝");

        int choix = SaisieHelper.lireChoix("Votre choix : ", 0, 9);

        switch (choix) {
            case 1 -> admin.voirStatistiques();
            case 2 -> admin.voirTousLesObjectifs();
            case 3 -> admin.testerFonctionnalitesUtilisateur();
            case 4 -> adminConsoleView.afficherMenu();

            case 9 -> {
                utilisateurConnecte = null;
                System.out.println("✓ Déconnexion réussie.");
                return true;
            }

            case 0 -> {
                return false;
            }

            default -> System.out.println("Choix invalide.");
        }

        SaisieHelper.pause();
        return true;
    }

    private void afficherHeader() {

        System.out.println();
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║       APPLICATION GESTION OBJECTIFS        ║");
        System.out.println("║          Coach personnel console           ║");
        System.out.println("╚════════════════════════════════════════════╝");
    }

    private void afficherHeaderUtilisateur(Utilisateur utilisateur) {

        System.out.println();
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║              ESPACE UTILISATEUR            ║");
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.println("║ Bienvenue : " + utilisateur.getPrenom() + " " + utilisateur.getNom());
        System.out.println("╚════════════════════════════════════════════╝");
    }

    private void afficherHeaderAdmin(Admin admin) {

        System.out.println();
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║              ESPACE ADMIN                  ║");
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.println("║ Administrateur : " + admin.getPrenom() + " " + admin.getNom());
        System.out.println("╚════════════════════════════════════════════╝");
    }

    private void afficherAuRevoir() {

        System.out.println();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║        Merci et à bientôt !          ║");
        System.out.println("╚══════════════════════════════════════╝");
    }
}