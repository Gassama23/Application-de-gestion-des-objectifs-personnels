package org.odk.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateHelper {
	
	/*
     * Formats utilisés dans l'application.
     */
    private static final DateTimeFormatter FORMAT_DATE =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final DateTimeFormatter FORMAT_HEURE =
            DateTimeFormatter.ofPattern("HH:mm");

    private static final DateTimeFormatter FORMAT_DATE_HEURE =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    
    public static LocalDate aujourdHui() {
        return LocalDate.now();
    }

  
    public static LocalTime heureActuelle() {
        return LocalTime.now();
    }

    
    public static LocalDateTime maintenant() {
        return LocalDateTime.now();
    }

    /**
     * Formater une date.
     *
     * Exemple :
     * 08/05/2026
     */
    public static String formaterDate(LocalDate date) {

        if (date == null) {
            return "";
        }

        return date.format(FORMAT_DATE);
    }

    /**
     * Formater une heure.
     *
     * Exemple :
     * 08:30
     */
    public static String formaterHeure(LocalTime heure) {

        if (heure == null) {
            return "";
        }

        return heure.format(FORMAT_HEURE);
    }

    /**
     * Formater date + heure.
     *
     * Exemple :
     * 08/05/2026 14:20
     */
    public static String formaterDateHeure(LocalDateTime dateHeure) {

        if (dateHeure == null) {
            return "";
        }

        return dateHeure.format(FORMAT_DATE_HEURE);
    }

    /**
     * Convertir String -> LocalDate
     *
     * Exemple :
     * "2026-05-08"
     */
    public static LocalDate convertirEnDate(String date) {

        if (date == null || date.isBlank()) {
            return null;
        }

        return LocalDate.parse(date);
    }

    /**
     * Vérifier si une date est aujourd'hui.
     */
    public static boolean estAujourdHui(LocalDate date) {

        if (date == null) {
            return false;
        }

        return date.equals(LocalDate.now());
    }

    /**
     * Vérifier si une date est passée.
     */
    public static boolean estPassee(LocalDate date) {

        if (date == null) {
            return false;
        }

        return date.isBefore(LocalDate.now());
    }

    /**
     * Ajouter des jours à une date.
     */
    public static LocalDate ajouterJours(LocalDate date, int jours) {

        if (date == null) {
            return null;
        }

        return date.plusDays(jours);
    }

    /**
     * Ajouter des mois à une date.
     */
    public static LocalDate ajouterMois(LocalDate date, int mois) {

        if (date == null) {
            return null;
        }

        return date.plusMonths(mois);
    }

    /**
     * Vérifier si date début < date fin.
     */
    public static boolean dateValide(LocalDate dateDebut, LocalDate dateFin) {

        if (dateDebut == null || dateFin == null) {
            return false;
        }

        return dateDebut.isBefore(dateFin)
                || dateDebut.equals(dateFin);
    }

    /**
     * Calculer nombre de jours entre deux dates.
     */
    public static long calculerNombreJours(
            LocalDate dateDebut,
            LocalDate dateFin
    ) {

        if (dateDebut == null || dateFin == null) {
            return 0;
        }

        return java.time.temporal.ChronoUnit.DAYS.between(
                dateDebut,
                dateFin
        );
    }

}
