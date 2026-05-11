package org.odk.model;

import org.odk.enums.EnumRole;
import org.odk.service.UserService;

public class Utilisateur extends User {

    private int streakActuel;
    private int meilleurStreak;

    private final UserService userService;

    public Utilisateur() {
        super();
        this.role = EnumRole.UTILISATEUR;
        this.streakActuel = 0;
        this.meilleurStreak = 0;
        this.userService = new UserService();
    }

    public Utilisateur(String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse, EnumRole.UTILISATEUR);
        this.streakActuel = 0;
        this.meilleurStreak = 0;
        this.userService = new UserService();
    }

    @Override
    public void sInscrire() {
        User userInscrit = userService.inscrire(this);

        if (userInscrit != null) {
            this.id = userInscrit.getId();
            System.out.println("✓ Inscription utilisateur réussie.");
        }
    }

    @Override
    public void seConnecter() {
        User userConnecte = userService.connecter(this.email, this.motDePasse);

        if (userConnecte != null && userConnecte.getRole() == EnumRole.UTILISATEUR) {
            this.id = userConnecte.getId();
            this.nom = userConnecte.getNom();
            this.prenom = userConnecte.getPrenom();
            this.dateInscription = userConnecte.getDateInscription();

            System.out.println("✓ Connexion utilisateur réussie.");
        } else {
            System.out.println("✗ Connexion utilisateur échouée.");
        }
    }

    public int getStreakActuel() {
        return streakActuel;
    }

    public void setStreakActuel(int streakActuel) {
        this.streakActuel = streakActuel;
    }

    public int getMeilleurStreak() {
        return meilleurStreak;
    }

    public void setMeilleurStreak(int meilleurStreak) {
        this.meilleurStreak = meilleurStreak;
    }
}