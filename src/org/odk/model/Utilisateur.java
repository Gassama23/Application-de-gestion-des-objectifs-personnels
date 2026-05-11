package org.odk.model;

import java.time.LocalDate;
import java.util.Date;
import org.odk.enums.EnumRole;
import org.odk.service.UtilisateurService;

/**
 * Classe Utilisateur - Hérite de User
 * Représente un utilisateur régulier du système
 */
public class Utilisateur extends User {
	
	 private int streakActuel;
	    private int meilleurStreak;

	    private final UtilisateurService utilisateurService;

	    public Utilisateur() {
	        super();
	        this.role = EnumRole.UTILISATEUR;
	        this.streakActuel = 0;
	        this.meilleurStreak = 0;

	        this.utilisateurService = new UtilisateurService();
	    }

	    public Utilisateur(String nom, String prenom, String email, String motDePasse) {
	        super(nom, prenom, email, motDePasse, EnumRole.UTILISATEUR
	        );

	        this.streakActuel = 0;
	        this.meilleurStreak = 0;

	        this.utilisateurService = new UtilisateurService();
	    }

	    @Override
	    public void sInscrire() {

	        Utilisateur utilisateurInscrit =
	                utilisateurService.inscrireUtilisateur(this);

	        if (utilisateurInscrit != null) {

	            this.id = utilisateurInscrit.getId();

	        }
	    }

	    @Override
	    public void seConnecter() {

	        Utilisateur utilisateurConnecte =
	                utilisateurService.connecterUtilisateur(
	                        this.email,
	                        this.motDePasse
	                );

	        if (utilisateurConnecte != null) {

	            this.id = utilisateurConnecte.getId();

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
