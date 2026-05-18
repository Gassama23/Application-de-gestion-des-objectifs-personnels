package org.odk.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class SaisieHelper {
	
	/*
     * Scanner unique partagé dans toute l'application.
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Lire une chaîne de caractères.
     */
    public static String lireTexte(String message) {

        while (true) {

            System.out.print(message);

            String valeur = scanner.nextLine().trim();

            if (!valeur.isEmpty()) {
                return valeur;
            }

            System.out.println("❌ La valeur ne peut pas être vide.");
        }
    }

    /**
     * Lire un entier.
     */
    public static int lireEntier(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        scanner.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println("❌ Veuillez entrer un entier valide.");
            }
        }
    }

    /**
     * Lire un double.
     */
    public static double lireDouble(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Double.parseDouble(
                        scanner.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println("❌ Veuillez entrer un nombre valide.");
            }
        }
    }

    /**
     * Lire un booléen.
     *
     * Exemple :
     * oui/non
     */
    public static boolean lireBoolean(String message) {

        while (true) {

            System.out.print(message + " (oui/non) : ");

            String reponse =
                    scanner.nextLine().trim().toLowerCase();

            if (reponse.equals("oui")
                    || reponse.equals("o")) {

                return true;
            }

            if (reponse.equals("non")
                    || reponse.equals("n")) {

                return false;
            }

            System.out.println("❌ Réponse invalide.");
        }
    }

    /**
     * Lire une date.
     *
     * Format :
     * yyyy-MM-dd
     *
     * Exemple :
     * 2026-05-08
     */
    public static LocalDate lireDate(String message) {

        while (true) {

            try {

                System.out.print(message);

                String date =
                        scanner.nextLine().trim();

                return LocalDate.parse(date);

            } catch (Exception e) {

                System.out.println(
                        "❌ Format invalide. Exemple : 2026-05-08"
                );
            }
        }
    }

    /**
     * Lire une heure.
     *
     * Format :
     * HH:mm
     *
     * Exemple :
     * 08:30
     */
    public static LocalTime lireHeure(String message) {

        while (true) {

            try {

                System.out.print(message);

                String heure =
                        scanner.nextLine().trim();

                return LocalTime.parse(heure);

            } catch (Exception e) {

                System.out.println(
                        "❌ Format invalide. Exemple : 08:30"
                );
            }
        }
    }

    /**
     * Lire un choix dans un intervalle.
     */
    public static int lireChoix(
            String message,
            int min,
            int max
    ) {

        while (true) {

            int choix = lireEntier(message);

            if (choix >= min && choix <= max) {
                return choix;
            }

            System.out.println(
                    "❌ Choix invalide. Choisissez entre "
                            + min + " et " + max
            );
        }
    }

    /**
     * Pause console.
     */
    public static void pause() {

        System.out.println("\nAppuyez sur Entrée pour continuer...");
        scanner.nextLine();
    }

    /**
     * Fermer le scanner.
     * À appeler à la fermeture de l'application.
     */
    public static void fermerScanner() {
        scanner.close();
    }

}
