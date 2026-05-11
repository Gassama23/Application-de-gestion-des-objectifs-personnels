package org.odk.ui;

import org.odk.enums.EnumRole;
import org.odk.model.Admin;
import org.odk.model.User;
import org.odk.model.Utilisateur;
import org.odk.service.AdminService;
import org.odk.service.UserService;
import org.odk.util.SaisieHelper;

public class AuthConsoleView {

    private final UserService userService;
    private final AdminService adminService;

    public AuthConsoleView() {
        this.userService = new UserService();
        this.adminService = new AdminService();
    }

    public User afficherMenuAuthentification() {

        int choix;

        do {
            afficherHeader();

            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║          AUTHENTIFICATION           ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║ 1. Créer un compte utilisateur      ║");
            System.out.println("║ 2. Se connecter utilisateur         ║");
            System.out.println("║ 3. Connexion administrateur         ║");
            System.out.println("║ 0. Retour                           ║");
            System.out.println("╚══════════════════════════════════════╝");

            choix = SaisieHelper.lireChoix("Votre choix : ", 0, 3);

            switch (choix) {
                case 1:
                    inscrireUtilisateur();
                    return null;

                case 2:
                    return connecterUtilisateur();

                case 3:
                    return connecterAdmin();

                case 0:
                    return null;

                default:
                    System.out.println("Choix invalide.");
            }

        } while (choix != 0);

        return null;
    }

    public void inscrireUtilisateur() {

        afficherSection("CRÉATION COMPTE UTILISATEUR");

        String nom = SaisieHelper.lireTexte("Nom : ");
        String prenom = SaisieHelper.lireTexte("Prénom : ");
        String email = SaisieHelper.lireTexte("Email : ");
        String motDePasse = SaisieHelper.lireTexte("Mot de passe : ");

        Utilisateur utilisateur =
                new Utilisateur(nom, prenom, email, motDePasse);

        User userInscrit =
                userService.inscrire(utilisateur);

        if (userInscrit instanceof Utilisateur) {
            System.out.println("\n✓ Compte utilisateur créé avec succès.");
            System.out.println("Veuillez maintenant vous connecter.");
            return;
        }

        System.out.println("\n✗ Échec de la création du compte.");
    }

    public Utilisateur connecterUtilisateur() {

        afficherSection("CONNEXION UTILISATEUR");

        String email = SaisieHelper.lireTexte("Email : ");
        String motDePasse = SaisieHelper.lireTexte("Mot de passe : ");

        User user = userService.connecter(email, motDePasse);

        if (user == null) {
            System.out.println("\n✗ Connexion échouée.");
            return null;
        }

        if (user.getRole() != EnumRole.UTILISATEUR) {
            System.out.println("\n✗ Ce compte n'est pas un compte utilisateur.");
            return null;
        }

        if (user instanceof Utilisateur utilisateur) {
            System.out.println("\n✓ Connexion utilisateur réussie.");
            return utilisateur;
        }

        System.out.println("\n✗ Connexion échouée : type utilisateur invalide.");
        return null;
    }

    public Admin connecterAdmin() {

        afficherSection("CONNEXION ADMINISTRATEUR");

        String email = SaisieHelper.lireTexte("Email admin : ");
        String motDePasse = SaisieHelper.lireTexte("Mot de passe : ");

        Admin admin = adminService.connecterAdmin(email, motDePasse);

        if (admin != null) {
            System.out.println("\n✓ Connexion administrateur réussie.");
            return admin;
        }

        System.out.println("\n✗ Connexion administrateur échouée.");
        return null;
    }

    private void afficherHeader() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║      APPLICATION OBJECTIFS          ║");
        System.out.println("║        AUTHENTIFICATION             ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    private void afficherSection(String titre) {
        System.out.println();
        System.out.println("══════════════════════════════════════");
        System.out.println("        " + titre);
        System.out.println("══════════════════════════════════════");
    }
}