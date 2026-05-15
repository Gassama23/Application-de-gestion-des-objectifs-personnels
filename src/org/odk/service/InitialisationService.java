package org.odk.service;

import org.odk.model.Admin;
import org.odk.model.User;

public class InitialisationService {

    private final UserService userService;

    public InitialisationService() {
        this.userService = new UserService();
    }

    public void creerAdminParDefaut() {

        String emailAdmin = "admin@gmail.com";

        if (userService.emailExiste(emailAdmin)) {
            System.out.println("ℹ Admin par défaut déjà existant.");
            return;
        }

        Admin admin = new Admin(
                "Admin",
                "Principal",
                emailAdmin,
                "admin123"
        );

        User adminCree = userService.inscrire(admin);

        if (adminCree != null) {
            System.out.println("✓ Admin par défaut créé au démarrage.");
           // System.out.println("Email : admin@gmail.com");
           // System.out.println("Mot de passe : admin123");
        }
    }
}