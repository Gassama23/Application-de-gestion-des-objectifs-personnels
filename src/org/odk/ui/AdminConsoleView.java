package org.odk.ui;

import org.odk.model.Admin;
import org.odk.service.AdminService;
import org.odk.util.SaisieHelper;

/**
 * Interface console administrateur.
 *
 * Rôle :
 * - afficher les actions admin
 * - interagir avec AdminService
 */
public class AdminConsoleView {

    private final AdminService adminService;

    public AdminConsoleView() {
        this.adminService = new AdminService();
    }

    public void afficherMenu(Admin adminConnecte) {

        if (adminConnecte == null || adminConnecte.getId() <= 0) {
            System.out.println("✗ Administrateur non connecté.");
            return;
        }

        int choix;

        do {
            afficherHeader(adminConnecte);

            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║         MENU ADMINISTRATEUR         ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║ 1. Voir statistiques                ║");
            System.out.println("║ 2. Voir tous les objectifs          ║");
            System.out.println("║ 3. Voir les utilisateurs          ║");
            System.out.println("║ 0. Retour                           ║");
            System.out.println("╚══════════════════════════════════════╝");

            choix = SaisieHelper.lireChoix("\nVotre choix : ", 0, 3);

            switch (choix) {
                case 1 -> voirStatistiques(adminConnecte);
                case 2 -> voirTousLesObjectifs(adminConnecte);
                case 3 -> voirUtilisateurs(adminConnecte);
                case 0 -> System.out.println("\nRetour au menu principal...");
            }

            if (choix != 0) {
                SaisieHelper.pause();
            }

        } while (choix != 0);
    }

    private void voirStatistiques(Admin adminConnecte) {
        afficherSection("STATISTIQUES GLOBALES");
        adminService.voirStatistiques(adminConnecte);
    }

    private void voirTousLesObjectifs(Admin adminConnecte) {
        afficherSection("TOUS LES OBJECTIFS");
        adminService.voirTousLesObjectifs(adminConnecte);
    }

    private void voirUtilisateurs(Admin adminConnecte) {
        afficherSection("TEST FONCTIONNALITÉS USER");
        adminService.voirUtilisateurs(adminConnecte);
    }

    private void afficherHeader(Admin adminConnecte) {
        System.out.println();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║      APPLICATION OBJECTIFS          ║");
        System.out.println("║           ESPACE ADMIN              ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ Admin : " + adminConnecte.getPrenom() + " " + adminConnecte.getNom());
        System.out.println("╚══════════════════════════════════════╝");
    }

    private void afficherSection(String titre) {
        System.out.println();
        System.out.println("══════════════════════════════════════");
        System.out.println("        " + titre);
        System.out.println("══════════════════════════════════════");
    }
}