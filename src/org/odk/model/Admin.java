package org.odk.model;

import org.odk.enums.EnumRole;
import org.odk.service.UserService;

public class Admin extends User {

    private final UserService userService;

    public Admin() {
        super();
        this.role = EnumRole.ADMIN;
        this.userService = new UserService();
    }

    public Admin(String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse, EnumRole.ADMIN);
        this.userService = new UserService();
    }

    @Override
    public void sInscrire() {
        User adminInscrit = userService.inscrire(this);

        if (adminInscrit != null) {
            this.id = adminInscrit.getId();
            System.out.println("✓ Admin créé avec succès.");
        }
    }

    @Override
    public void seConnecter() {
        User userConnecte = userService.connecter(this.email, this.motDePasse);

        if (userConnecte != null && userConnecte.getRole() == EnumRole.ADMIN) {
            this.id = userConnecte.getId();
            this.nom = userConnecte.getNom();
            this.prenom = userConnecte.getPrenom();
            this.dateInscription = userConnecte.getDateInscription();

            System.out.println("✓ Connexion administrateur réussie.");
        } else {
            System.out.println("✗ Connexion administrateur échouée.");
        }
    }

    public void voirStatistiques() {
        System.out.println("Affichage des statistiques globales...");
    }

    public void gererUtilisateurs() {
        System.out.println("Gestion des utilisateurs...");
    }
}