package org.odk.service;

import java.util.List;

import org.odk.enums.EnumRole;
import org.odk.model.Admin;
import org.odk.model.Objectif;
import org.odk.model.User;
import org.odk.model.Utilisateur;
import org.odk.repository.jdbc.JdbcObjectifRepository;
import org.odk.repository.jdbc.JdbcRepositoryUser;

public class AdminService {

    private final UserService userService;

    public AdminService() {
        this.userService = new UserService();
    }

    /**
     * Connexion administrateur.
     */
    public Admin connecterAdmin(String email, String motDePasse) {
        User user = userService.connecter(email, motDePasse);
        if (user == null) {
            System.out.println("Email ou mot de passe incorrect.");

            return null;
        }

        /*
         * Vérification rôle admin.
         */
        if (user.getRole() != EnumRole.ADMIN) {
            System.out.println("Accès refusé : administrateur uniquement.");
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
    public void voirStatistiques(Admin admin) {
        if (admin == null) {
            System.out.println("Administrateur invalide.");
            return;
        }

        System.out.println("\n===== STATISTIQUES =====");

        /*
         * À compléter plus tard.
         */
        System.out.println("Nombre utilisateurs : [À implémenter]");

        System.out.println("Nombre objectifs : [À implémenter]");

        System.out.println("Nombre actions : [À implémenter]");
    }

    /**
     * Voir tous les objectifs.
     */
    public void voirTousLesObjectifs(Admin admin) {

        if (admin == null) {

            System.out.println(" Administrateur invalide.");

            return;
        }

        System.out.println("\n===== TOUS LES OBJECTIFS =====");

        List<Objectif>objectifs=(new JdbcObjectifRepository()).findAll();
        if(objectifs.size()==0) {
        	System.out.println("aucun ojectif pour le momment");
        	return;
        }
        int cpt=1;
        for(Objectif o:objectifs) {
        	Utilisateur userUtilisateur=(new JdbcRepositoryUser()).trouverParId(o.getUtilisateur_id());
        	    System.out.println("Objectif :"+cpt);
        	        System.out.println("nom de l'objectif :"+o.getNom_objectif());
        	        System.out.println("description de l'objectif :"+o.getDescription());
        	        System.out.println("statu de l'objectif :"+o.getStatus());
        	        System.out.println("le proprieteur est :"+ userUtilisateur.getNomComplet());

        	        System.out.println("############################################");
        	        
         cpt++;
        }
        
        
    }
    

    /**
     * Tests techniques.
     */
    public void testerFonctionnalitesUtilisateur(Admin admin) {

        if (admin == null) {

            System.out.println(" Administrateur invalide.");

            return;
        }

        System.out.println( "\n===== TESTS UTILISATEUR =====");

        System.out.println(" Test connexion");

        System.out.println("Test création objectif");

        System.out.println( " Test progression");
    }
}