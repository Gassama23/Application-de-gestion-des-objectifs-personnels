package org.odk.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateHelper {
	
	/*
     * Formats utilisés dans l'application.
     */
    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final DateTimeFormatter FORMAT_HEURE = DateTimeFormatter.ofPattern("HH:mm");

    private static final DateTimeFormatter FORMAT_DATE_HEURE =DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    
    public static LocalDate aujourdHui() {
        return LocalDate.now();
    }

  
    public static LocalTime heureActuelle() {
        return LocalTime.now();
    }

    
    public static LocalDateTime maintenant() {
        return LocalDateTime.now();
    }

    
    public static String formaterDate(LocalDate date) {

        if (date == null) {
            return "";
        }

        return date.format(FORMAT_DATE);
    }

    
    public static String formaterHeure(LocalTime heure) {

        if (heure == null) {
            return "";
        }

        return heure.format(FORMAT_HEURE);
    }

    
    public static String formaterDateHeure(LocalDateTime dateHeure) {

        if (dateHeure == null) {
            return "";
        }

        return dateHeure.format(FORMAT_DATE_HEURE);
    }

    public static LocalDate convertirEnDate(String date) {

        if (date == null || date.isBlank()) {
            return null;
        }

        return LocalDate.parse(date);
    }

    public static boolean estAujourdHui(LocalDate date) {

        if (date == null) {
            return false;
        }

        return date.equals(LocalDate.now());
    }

    
    public static boolean estPassee(LocalDate date) {

        if (date == null) {
            return false;
        }

        return date.isBefore(LocalDate.now());
    }

    
    public static LocalDate ajouterJours(LocalDate date, int jours) {

        if (date == null) {
            return null;
        }

        return date.plusDays(jours);
    }

    
    public static LocalDate ajouterMois(LocalDate date, int mois) {

        if (date == null) {
            return null;
        }

        return date.plusMonths(mois);
    }

    
    public static boolean dateValide(LocalDate dateDebut, LocalDate dateFin) {

        if (dateDebut == null || dateFin == null) {
            return false;
        }

        return dateDebut.isBefore(dateFin)
                || dateDebut.equals(dateFin);
    }

   
    public static long calculerNombreJours( LocalDate dateDebut,LocalDate dateFin) {

        if (dateDebut == null || dateFin == null) {
            return 0;
        }

        return java.time.temporal.ChronoUnit.DAYS.between(
                dateDebut,
                dateFin
        );
    }

}
