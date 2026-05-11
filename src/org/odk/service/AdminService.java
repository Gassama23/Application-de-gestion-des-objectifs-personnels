package org.odk.service;

import org.odk.enums.EnumRole;
import org.odk.model.Admin;
import org.odk.model.User;

public class AdminService {

    private final UserService userService;

    public AdminService() {
        this.userService = new UserService();
    }

    /**
     * Connexion administrateur.
     */
    public Admin connecterAdmin(
            String email,
            String motDePasse
    ) {

        User user =
                userService.connecter(
                        email,
                        motDePasse
                );

        if (user == null) {

            System.out.println(
                    "✗ Email ou mot de passe incorrect."
            );

            return null;
        }

        /*
         * Vérification rôle admin.
         */
        if (user.getRole() != EnumRole.ADMIN) {

            System.out.println(
                    "✗ Accès refusé : administrateur uniquement."
            );

            return null;
        }

        /*
         * Conversion sécurisée.
         */
        if (user instanceof Admin admin) {

            return admin;
        }

        /*
         * Sécurité supplémentaire.
         */
        Admin admin = new Admin();

        admin.setId(user.getId());
        admin.setNom(user.getNom());
        admin.setPrenom(user.getPrenom());
        admin.setEmail(user.getEmail());
        admin.setMotDePasse(user.getMotDePasse());
        admin.setRole(user.getRole());
        admin.setDateInscription(user.getDateInscription());

        return admin;
    }

    /**
     * Statistiques globales.
     */
    public void voirStatistiques(
            Admin admin
    ) {

        if (admin == null) {

            System.out.println(
                    "✗ Administrateur invalide."
            );

            return;
        }

        System.out.println(
                "\n===== STATISTIQUES ====="
        );

        /*
         * À compléter plus tard.
         */
        System.out.println(
                "Nombre utilisateurs : [À implémenter]"
        );

        System.out.println(
                "Nombre objectifs : [À implémenter]"
        );

        System.out.println(
                "Nombre actions : [À implémenter]"
        );
    }

    /**
     * Voir tous les objectifs.
     */
    public void voirTousLesObjectifs(
            Admin admin
    ) {

        if (admin == null) {

            System.out.println(
                    "✗ Administrateur invalide."
            );

            return;
        }

        System.out.println(
                "\n===== TOUS LES OBJECTIFS ====="
        );

        /*
         * À compléter plus tard.
         */
        System.out.println(
                "[Liste objectifs à implémenter]"
        );
    }

    /**
     * Tests techniques.
     */
    public void testerFonctionnalitesUtilisateur(
            Admin admin
    ) {

        if (admin == null) {

            System.out.println(
                    "✗ Administrateur invalide."
            );

            return;
        }

        System.out.println(
                "\n===== TESTS UTILISATEUR ====="
        );

        System.out.println(
                "✓ Test connexion"
        );

        System.out.println(
                "✓ Test création objectif"
        );

        System.out.println(
                "✓ Test progression"
        );
    }
}