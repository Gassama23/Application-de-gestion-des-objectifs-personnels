package org.odk.model;

import java.time.LocalDate;

public class badge_utilisateur {

    private int id;
    private int id_utilisateur;
    private int id_badge;
    private LocalDate date_obtention;

    public badge_utilisateur(int id, int idUtilisateur, int idBadge, LocalDate dateObtention) {
        this.id = id;
        this.id_utilisateur = idUtilisateur;
        this.id_badge = idBadge;
        this.date_obtention = dateObtention;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtilisateurId() {
        return id_utilisateur;
    }

    public void setUtilisateurId(int idUtilisateur) {
        this.id_utilisateur = idUtilisateur;
    }

    public int getBadgeId() {
        return id_badge;
    }

    public void setBadgeId(int idBadge) {
        this.id_badge = idBadge;
    }

    public LocalDate getDateObtention() {
        return date_obtention;
    }

    public void setDateObtention(LocalDate dateObtention) {
        this.date_obtention = dateObtention;
    }

    public void testerUtilisateur() {
        System.out.println("Id : " + id);
        System.out.println("Id Utilisateur : " + id_utilisateur);
        System.out.println("Id Badge : " + id_badge);
        System.out.println("Date Obtention : " + date_obtention);
    }
}