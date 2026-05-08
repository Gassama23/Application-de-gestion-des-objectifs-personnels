package org.odk.ui;

import org.odk.model.Admin;
import org.odk.service.AdminService;
import org.odk.util.SaisieHelper;

/**
 * Interface console administrateur.
 *
 * Rôle :
 * - afficher le menu admin
 * - gérer les actions admin
 * - interagir avec AdminService
 */
public class AdminConsoleView {

    private final AdminService adminService;

    private Admin adminConnecte;

    public AdminConsoleView() {
        this.adminService = new AdminService();
    }

    /**
     * Menu principal administrateur.
     */
    public void afficherMenu() {

        int choix;

        do {

            afficherHeader();

            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║         MENU ADMINISTRATEUR         ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║ 1. Se connecter                     ║");
            System.out.println("║ 2. Voir statistiques                ║");
            System.out.println("║ 3. Voir tous les objectifs          ║");
            System.out.println("║ 4. Tester fonctionnalités           ║");
            System.out.println("║ 0. Retour                           ║");
            System.out.println("╚══════════════════════════════════════╝");

            choix = SaisieHelper.lireChoix(
                    "\nVotre choix : ",
                    0,
                    4
            );

            switch (choix) {

                case 1 -> seConnecter();

                case 2 -> voirStatistiques();

                case 3 -> voirTousLesObjectifs();

                case 4 -> testerFonctionnalites();

                case 0 -> {
                    System.out.println("\nRetour au menu principal...");
                }
            }

            if (choix != 0) {
                SaisieHelper.pause();
            }

        } while (choix != 0);
    }

    /**
     * Connexion admin.
     */
    private void seConnecter() {

        System.out.println("\n══════════════════════════════════════");
        System.out.println("       CONNEXION ADMINISTRATEUR");
        System.out.println("══════════════════════════════════════");

        String email =
                SaisieHelper.lireTexte("Email : ");

        String motDePasse =
                SaisieHelper.lireTexte("Mot de passe : ");

        Admin admin =
                adminService.connecterAdmin(
                        email,
                        motDePasse
                );

        if (admin != null) {

            this.adminConnecte = admin;

            System.out.println("\n✓ Connexion réussie.");
            System.out.println("Bienvenue "
                    + admin.getPrenom()
                    + " "
                    + admin.getNom());

        } else {

            System.out.println("\n✗ Connexion échouée.");
        }
    }

    /**
     * Voir statistiques globales.
     */
    private void voirStatistiques() {

        if (!adminConnecte()) {
            return;
        }

        System.out.println("\n══════════════════════════════════════");
        System.out.println("         STATISTIQUES GLOBALES");
        System.out.println("══════════════════════════════════════");

        adminService.voirStatistiques(adminConnecte);
    }

    /**
     * Voir tous les objectifs.
     */
    private void voirTousLesObjectifs() {

        if (!adminConnecte()) {
            return;
        }

        System.out.println("\n══════════════════════════════════════");
        System.out.println("          TOUS LES OBJECTIFS");
        System.out.println("══════════════════════════════════════");

        adminService.voirTousLesObjectifs(adminConnecte);
    }

    /**
     * Tester les fonctionnalités.
     */
    private void testerFonctionnalites() {

        if (!adminConnecte()) {
            return;
        }

        System.out.println("\n══════════════════════════════════════");
        System.out.println("     TEST FONCTIONNALITÉS USER");
        System.out.println("══════════════════════════════════════");

        adminService.testerFonctionnalitesUtilisateur(
                adminConnecte
        );
    }

    /**
     * Vérifie si admin connecté.
     */
    private boolean adminConnecte() {

        if (adminConnecte == null) {

            System.out.println(
                    "\n✗ Veuillez d'abord vous connecter."
            );

            return false;
        }

        return true;
    }

    /**
     * Header esthétique.
     */
    private void afficherHeader() {

        System.out.println("\n");
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║      APPLICATION OBJECTIFS          ║");
        System.out.println("║           ESPACE ADMIN              ║");
        System.out.println("╚══════════════════════════════════════╝");
    }
}