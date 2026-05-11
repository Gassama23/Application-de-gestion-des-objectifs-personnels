package org.odk.model;
import java.util.Date;

import org.odk.enums.EnumTypeNotification;

public class Notification {
    
    // Attributs privés (indiqués par le signe '-' )
    private int id;
    private String titre;
    private EnumTypeNotification type; // c'est le même nom que Awa a donné à l'EnumNotification
    private String message;
    private Date dateEnvoi;
    private boolean lu;
    private int utilisateur_id; //la clé étrangère

    /**
     * Constructeur pour initialiser une nouvelle notification.
     */
    public Notification(int id, String titre, EnumTypeNotification type, String message, Date dateEnvoi, boolean lu, int utilisateur_id) {
        this.id = id;
        this.titre = titre;
        this.type = type;
        this.message = message;
        this.dateEnvoi = dateEnvoi;
        this.lu = lu;
        this.utilisateur_id = utilisateur_id;
    }

    /**
     * Méthode d'envoi (indiquée par le signe '+')
     */
    public void envoyer() {
        // Logique d'envoi à implémenter selon les besoins du groupe
        System.out.println("Notification " + id + " envoyée : " + message);
    }

    // --- Getters et Setters ---
    // Indispensables pour accéder aux attributs privés depuis les autres classes

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public EnumTypeNotification getType() {
		return type;
	}

	public void setType(EnumTypeNotification type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateEnvoi() {
		return dateEnvoi;
	}

	public void setDateEnvoi(Date dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}

	public boolean isLu() {
		return lu;
	}

	public void setLu(boolean lu) {
		this.lu = lu;
	}

	public int getUtilisateur_id() {
		return utilisateur_id;
	}

	public void setUtilisateur_id(int utilisateur_id) {
		this.utilisateur_id = utilisateur_id;
	}


   
}