package org.odk.model;

import java.time.LocalDate;

public class BadgesUtilisateur {

    private int id;
    private int idUtilisateur;
    private int idBadge;
    private LocalDate dateObtention;

    public BadgesUtilisateur() {}

 public BadgesUtilisateur(int utilisateurId, int badgeId, LocalDate dateObtention) {
        
        if (utilisateurId <= 0) {
            throw new IllegalArgumentException("utilisateurId invalide");
        }

        if (badgeId <= 0) {
            throw new IllegalArgumentException("badgeId invalide");
        }

        this.idUtilisateur = utilisateurId;
        this.idBadge = badgeId;
        this.dateObtention = dateObtention;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUtilisateurId() { return idUtilisateur; }
    public void setUtilisateurId(int utilisateurId) { this.idUtilisateur = utilisateurId; }

    public int getBadgeId() { return idBadge; }
    public void setBadgeId(int badgeId) { this.idBadge = badgeId; }

    public LocalDate getDateObtention() { return dateObtention; }
    public void setDateObtention(LocalDate dateObtention) { this.dateObtention = dateObtention; }

    @Override
    public String toString() {
        return "BadgeUtilisateur{" +
                "id=" + id +
                ", utilisateurId=" + idUtilisateur +
                ", badgeId=" + idBadge +
                ", dateObtention=" + dateObtention +
                '}';
    }
}