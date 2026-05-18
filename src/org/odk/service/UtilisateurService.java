package org.odk.service;

import org.odk.model.Utilisateur;
import org.odk.model.User;
import org.odk.enums.EnumRole;

/**
 * Service pour la gestion des utilisateurs réguliers
 * Contient la logique métier spécifique aux utilisateurs
 */
public class UtilisateurService {
    
	  private final UserService userService;

	    public UtilisateurService() {
	        this.userService = new UserService();
	    }

	    /**
	     * Inscrire un nouvel utilisateur régulier.
	     *
	     * @param utilisateur utilisateur à inscrire
	     * @return utilisateur inscrit, ou null si échec
	     */
	    public Utilisateur inscrireUtilisateur(Utilisateur utilisateur) {

	        User userInscrit = userService.inscrire(utilisateur);

	        if (userInscrit instanceof Utilisateur) {
	            return (Utilisateur) userInscrit;
	        }

	        return null;
	    }

	    /**
	     * Connecter un utilisateur régulier.
	     *
	     * @param email email de l'utilisateur
	     * @param motDePasse mot de passe saisi
	     * @return utilisateur connecté, ou null si échec
	     */
	    public Utilisateur connecterUtilisateur(String email, String motDePasse) {
	        User user = userService.connecter(email, motDePasse);
	        if (user == null) {
	            return null;
	        }
	        if (user.getRole() != EnumRole.UTILISATEUR) {
	            System.err.println("Erreur : ce compte n'est pas un utilisateur régulier.");
	            return null;
	        }

	        if (user instanceof Utilisateur) {
	            return (Utilisateur) user;
	        }

	        return null;
	    }

	    public void modifierProfil(Utilisateur utilisateur) {
	        if (!utilisateurEstValide(utilisateur)) {
	            return;
	        }

	        System.out.println("Modification du profil de " + utilisateur.getNom());
	        // TODO : appeler repository/service de modification du profil
	    }

	    public void creerObjectif(Utilisateur utilisateur) {
	        if (!utilisateurEstValide(utilisateur)) {
	            return;
	        }

	        System.out.println("Création d'un objectif pour " + utilisateur.getPrenom());
	        // TODO : appeler ObjectifService
	    }

	    public void consulterObjectifs(Utilisateur utilisateur) {
	        if (!utilisateurEstValide(utilisateur)) {
	            return;
	        }

	        System.out.println("Consultation des objectifs de " + utilisateur.getPrenom());
	        // TODO : appeler ObjectifService
	    }

	    public void consulterHistorique(Utilisateur utilisateur) {
	        if (!utilisateurEstValide(utilisateur)) {
	            return;
	        }

	        System.out.println("Consultation de l'historique de " + utilisateur.getPrenom());
	        // TODO : appeler ProgressionService
	    }

	    public void consulterNotifications(Utilisateur utilisateur) {
	        if (!utilisateurEstValide(utilisateur)) {
	            return;
	        }

	        System.out.println("Consultation des notifications de " + utilisateur.getPrenom());
	        // TODO : appeler NotificationService
	    }

	    public void consulterBadges(Utilisateur utilisateur) {
	        if (!utilisateurEstValide(utilisateur)) {
	            return;
	        }

	        System.out.println("Consultation des badges de " + utilisateur.getPrenom());
	        // TODO : appeler BadgeService
	    }

	    public void configurerRappel(Utilisateur utilisateur) {
	        if (!utilisateurEstValide(utilisateur)) {
	            return;
	        }

	        System.out.println("Configuration d'un rappel pour " + utilisateur.getPrenom());
	        // TODO : appeler RappelService
	    }

	    public void demanderConseils(Utilisateur utilisateur) {
	        if (!utilisateurEstValide(utilisateur)) {
	            return;
	        }

	        System.out.println("Demande de conseils pour " + utilisateur.getPrenom());
	        // TODO : appeler ConseilService
	    }

	    public void validerEtape(Utilisateur utilisateur) {
	        if (!utilisateurEstValide(utilisateur)) {
	            return;
	        }

	        System.out.println("Validation d'une étape pour " + utilisateur.getPrenom());
	        // TODO : appeler ProgressionService
	    }

	    /**
	     * Méthode privée pour éviter de répéter la même validation partout.
	     */
	    private boolean utilisateurEstValide(Utilisateur utilisateur) {
	        if (utilisateur == null || utilisateur.getId() <= 0) {
	            System.err.println("Erreur : utilisateur non connecté.");
	            return false;
	        }

	        return true;
	    }
}
