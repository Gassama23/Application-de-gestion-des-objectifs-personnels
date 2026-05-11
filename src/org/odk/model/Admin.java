package org.odk.model;

import java.time.LocalDate;
import java.util.Date;
import org.odk.enums.EnumRole;
import org.odk.service.AdminService;

/**
 * Classe Admin - Hérite de User
 * Représente un administrateur du système
 */
public class Admin extends User {
    
	 private final AdminService adminService;

	    public Admin() {
	        super();

	        this.role = EnumRole.ADMIN;

	        this.adminService = new AdminService();
	    }

	    public Admin(String nom, String prenom, String email, String motDePasse) {
	        super(nom, prenom, email, motDePasse, EnumRole.ADMIN
	        );

	        this.adminService = new AdminService();
	    }

	    @Override
	    public void sInscrire() {

	        Admin adminInscrit = adminService.inscrireAdmin(this);

	        if (adminInscrit != null) {

	            this.id = adminInscrit.getId();
	        }
	    }

	    @Override
	    public void seConnecter() {

	        Admin adminConnecte =
	                adminService.connecterAdmin(
	                        this.email,
	                        this.motDePasse
	                );

	        if (adminConnecte != null) {

	            this.id = adminConnecte.getId();
	        }
	    }

	    /*
	     * Méthodes spécifiques admin
	     */

	    public void voirStatistiques() {
	        adminService.voirStatistiques(this);
	    }

	    public void voirTousLesObjectifs() {
	        adminService.voirTousLesObjectifs(this);
	    }

	    public void testerFonctionnalitesUtilisateur() {
	        adminService.testerFonctionnalitesUtilisateur(this);
	    }
}
