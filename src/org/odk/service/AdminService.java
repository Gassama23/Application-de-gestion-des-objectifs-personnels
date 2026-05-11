package org.odk.service;

import org.odk.model.Admin;
import org.odk.model.User;
import org.odk.enums.EnumRole;

/**
 * Service pour la gestion des administrateurs
 * Contient la logique métier spécifique aux admins
 */
public class AdminService {
    
	 private final UserService userService;

	    public AdminService() {
	        this.userService = new UserService();
	    }

	    /**
	     * Inscrire un nouvel administrateur.
	     *
	     * @param admin admin à inscrire
	     * @return admin inscrit ou null si échec
	     */
	    public Admin inscrireAdmin(Admin admin) {

	        User userInscrit = userService.inscrire(admin);

	        if (userInscrit instanceof Admin) {
	            return (Admin) userInscrit;
	        }

	        return null;
	    }

	    /**
	     * Connecter un administrateur.
	     *
	     * @param email email de l'admin
	     * @param motDePasse mot de passe
	     * @return admin connecté ou null si échec
	     */
	    public Admin connecterAdmin(String email, String motDePasse) {

	        User user = userService.connecter(email, motDePasse);

	        if (user == null) {
	            return null;
	        }

	        if (user.getRole() != EnumRole.ADMIN) {
	            System.err.println("Erreur : ce compte n'est pas un administrateur.");
	            return null;
	        }

	        if (user instanceof Admin) {
	            return (Admin) user;
	        }

	        return null;
	    }

	    public void voirStatistiques(Admin admin) {

	        if (!adminEstValide(admin)) {
	            return;
	        }

	        System.out.println("=== Statistiques globales ===");
	        System.out.println("Nombre total d'utilisateurs : [À implémenter]");
	        System.out.println("Nombre d'objectifs créés : [À implémenter]");
	        System.out.println("Taux de complétion moyen : [À implémenter]");
	    }

	    public void voirTousLesObjectifs(Admin admin) {

	        if (!adminEstValide(admin)) {
	            return;
	        }

	        System.out.println("=== Tous les objectifs ===");
	        System.out.println("[À implémenter]");
	    }

	    public void testerFonctionnalitesUtilisateur(Admin admin) {

	        if (!adminEstValide(admin)) {
	            return;
	        }

	        System.out.println("=== Test des fonctionnalités utilisateur ===");
	        System.out.println("[À implémenter]");
	    }

	    /**
	     * Évite de répéter la même vérification dans toutes les méthodes admin.
	     */
	    private boolean adminEstValide(Admin admin) {

	        if (admin == null) {
	            System.err.println("Erreur : administrateur non connecté.");
	            return false;
	        }

	        if (admin.getRole() != EnumRole.ADMIN) {
	            System.err.println("Erreur : droits administrateur requis.");
	            return false;
	        }

	        return true;
	    }
}
