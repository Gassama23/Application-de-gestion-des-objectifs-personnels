package org.odk.model;

import java.time.LocalDate;

public class badge_tilisateur {

    private int id;
    private int idUtilisateur;
    private int idBadge;
    private LocalDate dateObtention;

    public badge_tilisateur(int id, int idUtilisateur, int idBadge, LocalDate dateObtention) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.idBadge = idBadge;
        this.dateObtention = dateObtention;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtilisateurId() {
        return idUtilisateur;
    }

    public void setUtilisateurId(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getBadgeId() {
        return idBadge;
    }

    public void setBadgeId(int idBadge) {
        this.idBadge = idBadge;
    }

    public LocalDate getDateObtention() {
        return dateObtention;
    }

    public void setDateObtention(LocalDate dateObtention) {
        this.dateObtention = dateObtention;
    }

    public void testerUtilisateur() {
        System.out.println("Id : " + id);
        System.out.println("Id Utilisateur : " + idUtilisateur);
        System.out.println("Id Badge : " + idBadge);
        System.out.println("Date Obtention : " + dateObtention);
    }
}