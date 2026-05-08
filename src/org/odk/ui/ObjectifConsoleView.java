package org.odk.ui;

import java.time.LocalDate;

import org.odk.model.Objectif;
import org.odk.model.ObjectifApprentissage;
import org.odk.model.ObjectifDevPersonnel;
import org.odk.model.ObjectifEconomie;
import org.odk.model.ObjectifSport;
import org.odk.model.Planning;
import org.odk.model.Utilisateur;
import org.odk.service.ObjectifService;
import org.odk.util.SaisieHelper;

/**
 * ObjectifConsoleView
 *
 * Rôle :
 * - afficher le menu de création d'objectif
 * - récupérer les informations de base
 * - créer la bonne classe fille d'Objectif
 * - appeler ObjectifService pour générer le planning adapté
 */
public class ObjectifConsoleView {

    private final ObjectifService objectifService;

    public ObjectifConsoleView() {
        this.objectifService = new ObjectifService();
    }

    public void creerObjectifAvecPlanning(Utilisateur utilisateur) {

        if (utilisateur == null || utilisateur.getId() <= 0) {
            System.out.println("✗ Utilisateur non connecté.");
            return;
        }

        afficherHeader();

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║       CHOISIR LE TYPE D'OBJECTIF     ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ 1. Économie                         ║");
        System.out.println("║ 2. Sport                            ║");
        System.out.println("║ 3. Apprentissage                    ║");
        System.out.println("║ 4. Développement personnel          ║");
        System.out.println("║ 0. Retour                           ║");
        System.out.println("╚══════════════════════════════════════╝");

        int choix = SaisieHelper.lireChoix("Votre choix : ", 0, 4);

        if (choix == 0) {
            return;
        }

        Objectif objectif = saisirInformationsObjectif(choix);

        if (objectif == null) {
            System.out.println("✗ Création annulée.");
            return;
        }

        try {
            Planning planning =
                    objectifService.creerObjectifAvecPlanning(
                            objectif,
                            utilisateur.getId()
                    );

            System.out.println("\n✓ Objectif créé avec succès.");
            System.out.println("✓ Planning généré automatiquement.");

            if (planning != null) {
                System.out.println(planning.afficherPlanning());
            }

        } catch (Exception e) {
            System.out.println("✗ Erreur lors de la création de l'objectif : " + e.getMessage());
        }
    }

    private Objectif saisirInformationsObjectif(int choix) {

        System.out.println("\n══════════════════════════════════════");
        System.out.println("       INFORMATIONS GÉNÉRALES");
        System.out.println("══════════════════════════════════════");

        String nomObjectif = SaisieHelper.lireTexte("Titre de l'objectif : ");
        String description = SaisieHelper.lireTexte("Description : ");

        LocalDate dateDebut = SaisieHelper.lireDate("Date début (yyyy-MM-dd) : ");
        LocalDate dateFin = SaisieHelper.lireDate("Date fin (yyyy-MM-dd) : ");

        if (dateFin.isBefore(dateDebut)) {
            System.out.println("✗ La date de fin ne peut pas être avant la date de début.");
            return null;
        }

        return switch (choix) {
            case 1 -> creerObjectifEconomie(nomObjectif, description, dateDebut, dateFin);
            case 2 -> creerObjectifSport(nomObjectif, description, dateDebut, dateFin);
            case 3 -> creerObjectifApprentissage(nomObjectif, description, dateDebut, dateFin);
            case 4 -> creerObjectifDevPersonnel(nomObjectif, description, dateDebut, dateFin);
            default -> null;
        };
    }

    private ObjectifEconomie creerObjectifEconomie(
            String nomObjectif,
            String description,
            LocalDate dateDebut,
            LocalDate dateFin
    ) {
        System.out.println("\n--- Objectif Économie ---");

        double montantCible =
                SaisieHelper.lireDouble("Montant cible à économiser : ");

        return new ObjectifEconomie(
                nomObjectif,
                description,
                dateDebut,
                dateFin,
                montantCible
        );
    }

    private ObjectifSport creerObjectifSport(
            String nomObjectif,
            String description,
            LocalDate dateDebut,
            LocalDate dateFin
    ) {
        System.out.println("\n--- Objectif Sport ---");

        String typeActivite =
                SaisieHelper.lireTexte("Type d'activité : ");

        int dureeSeance =
                SaisieHelper.lireEntier("Durée d'une séance en minutes : ");

        int frequenceHebdo =
                SaisieHelper.lireEntier("Fréquence par semaine : ");

        String niveauSportif =
                SaisieHelper.lireTexte("Niveau sportif : ");

        return new ObjectifSport(
                nomObjectif,
                description,
                dateDebut,
                dateFin,
                typeActivite,
                dureeSeance,
                frequenceHebdo,
                niveauSportif
        );
    }

    private ObjectifApprentissage creerObjectifApprentissage(
            String nomObjectif,
            String description,
            LocalDate dateDebut,
            LocalDate dateFin
    ) {
        System.out.println("\n--- Objectif Apprentissage ---");

        String sujet =
                SaisieHelper.lireTexte("Sujet à apprendre : ");

        int dureeEtudeParJour =
                SaisieHelper.lireEntier("Durée d'étude par jour en minutes : ");

        String ressource =
                SaisieHelper.lireTexte("Ressource principale : ");

        return new ObjectifApprentissage(
                nomObjectif,
                description,
                dateDebut,
                dateFin,
                sujet,
                dureeEtudeParJour,
                ressource
        );
    }

    private ObjectifDevPersonnel creerObjectifDevPersonnel(
            String nomObjectif,
            String description,
            LocalDate dateDebut,
            LocalDate dateFin
    ) {
        System.out.println("\n--- Objectif Développement Personnel ---");

        String habitudeCible =
                SaisieHelper.lireTexte("Habitude cible : ");

        int dureeDevPersonnel =
                SaisieHelper.lireEntier("Durée quotidienne en minutes : ");

        String typeDevPersonnel =
                SaisieHelper.lireTexte("Type de développement personnel : ");

        return new ObjectifDevPersonnel(
                nomObjectif,
                description,
                dateDebut,
                dateFin,
                habitudeCible,
                dureeDevPersonnel,
                typeDevPersonnel
        );
    }

    private void afficherHeader() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║          CRÉATION OBJECTIF          ║");
        System.out.println("╚══════════════════════════════════════╝");
    }
}