package org.odk.ui;

import java.time.LocalDate;

import org.odk.enums.EnumStatut;
import org.odk.model.Objectif;
import org.odk.model.ObjectifApprentissage;
import org.odk.model.ObjectifDevPersonnel;
import org.odk.model.ObjectifEconomie;
import org.odk.model.ObjectifSport;
import org.odk.model.Planning;
import org.odk.model.Utilisateur;
import org.odk.service.ObjectifService;
import org.odk.util.SaisieHelper;

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
        System.out.println("║       CHOISIR LE TYPE D'OBJECTIF    ║");
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

        Objectif objectif = saisirInformationsObjectif(choix, utilisateur.getId());

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

            if (planning != null) {
                System.out.println("✓ Planning généré automatiquement.");
                System.out.println("Planning : " + planning.getTitre());
            }

        } catch (Exception e) {
            System.out.println("✗ Erreur création objectif : " + e.getMessage());
        }
    }

    private Objectif saisirInformationsObjectif(
            int choix,
            int utilisateurId
    ) {

        System.out.println("\n══════════════════════════════════════");
        System.out.println("       INFORMATIONS GÉNÉRALES");
        System.out.println("══════════════════════════════════════");

        String nomObjectif = SaisieHelper.lireTexte("Titre de l'objectif : ");
        String description = SaisieHelper.lireTexte("Description : ");

        LocalDate dateDebut =
                SaisieHelper.lireDate("Date début (yyyy-MM-dd) : ");

        LocalDate dateFin =
                SaisieHelper.lireDate("Date fin (yyyy-MM-dd) : ");

        if (dateFin.isBefore(dateDebut)) {
            System.out.println("✗ La date de fin ne peut pas être avant la date de début.");
            return null;
        }

        EnumStatut statut = EnumStatut.EN_COURS;

        return switch (choix) {
            case 1 -> creerObjectifEconomie(
                    nomObjectif,
                    utilisateurId,
                    description,
                    dateDebut,
                    dateFin,
                    statut
            );

            case 2 -> creerObjectifSport(
                    nomObjectif,
                    utilisateurId,
                    description,
                    dateDebut,
                    dateFin,
                    statut
            );

            case 3 -> creerObjectifApprentissage(
                    nomObjectif,
                    utilisateurId,
                    description,
                    dateDebut,
                    dateFin,
                    statut
            );

            case 4 -> creerObjectifDevPersonnel(
                    nomObjectif,
                    utilisateurId,
                    description,
                    dateDebut,
                    dateFin,
                    statut
            );

            default -> null;
        };
    }

    private ObjectifEconomie creerObjectifEconomie(
            String nomObjectif,
            int utilisateurId,
            String description,
            LocalDate dateDebut,
            LocalDate dateFin,
            EnumStatut statut
    ) {

        System.out.println("\n--- Objectif Économie ---");

        int delaiMois =
                SaisieHelper.lireEntier("Délai en mois : ");

        double montantCible =
                SaisieHelper.lireDouble("Montant cible à économiser : ");

        double montantEpargne =
                SaisieHelper.lireDouble("Montant déjà épargné : ");

        String typeEconomie =
                SaisieHelper.lireTexte("Type économie : ");

        return new ObjectifEconomie(
                nomObjectif,
                utilisateurId,
                description,
                dateDebut,
                dateFin,
                statut,
                delaiMois,
                montantCible,
                montantEpargne,
                typeEconomie
        );
    }

    private ObjectifSport creerObjectifSport(
            String nomObjectif,
            int utilisateurId,
            String description,
            LocalDate dateDebut,
            LocalDate dateFin,
            EnumStatut statut
    ) {

        System.out.println("\n--- Objectif Sport ---");

        int dureeSeance =
                SaisieHelper.lireEntier("Durée d'une séance en minutes : ");

        int frequenceHebdo =
                SaisieHelper.lireEntier("Fréquence par semaine : ");

        int nbSeancesRealisees =
                SaisieHelper.lireEntier("Nombre de séances déjà réalisées : ");

        String niveauSportif =
                SaisieHelper.lireTexte("Niveau sportif : ");

        String typeActivite =
                SaisieHelper.lireTexte("Type d'activité : ");

        return new ObjectifSport(
                0,
                nomObjectif,
                utilisateurId,
                description,
                dateDebut,
                dateFin,
                statut,
                dureeSeance,
                frequenceHebdo,
                nbSeancesRealisees,
                niveauSportif,
                typeActivite
        );
    }

    private ObjectifApprentissage creerObjectifApprentissage(
            String nomObjectif,
            int utilisateurId,
            String description,
            LocalDate dateDebut,
            LocalDate dateFin,
            EnumStatut statut
    ) {

        System.out.println("\n--- Objectif Apprentissage ---");

        int dureeEtudeParJour =
                SaisieHelper.lireEntier("Durée d'étude par jour en minutes : ");

        int joursEtudies =
                SaisieHelper.lireEntier("Nombre de jours déjà étudiés : ");

        String ressource =
                SaisieHelper.lireTexte("Ressource principale : ");

        return new ObjectifApprentissage(
                0,
                nomObjectif,
                utilisateurId,
                description,
                dateDebut,
                dateFin,
                statut,
                dureeEtudeParJour,
                joursEtudies,
                ressource
        );
    }

    private ObjectifDevPersonnel creerObjectifDevPersonnel(
            String nomObjectif,
            int utilisateurId,
            String description,
            LocalDate dateDebut,
            LocalDate dateFin,
            EnumStatut statut
    ) {

        System.out.println("\n--- Objectif Développement Personnel ---");

        int dureeDevPersonnel =
                SaisieHelper.lireEntier("Durée quotidienne en minutes : ");

        String habitudeCible =
                SaisieHelper.lireTexte("Habitude cible : ");

        int joursRealises =
                SaisieHelper.lireEntier("Nombre de jours déjà réalisés : ");

        String typeDevPersonnel =
                SaisieHelper.lireTexte("Type développement personnel : ");

        return new ObjectifDevPersonnel(
                0,
                nomObjectif,
                utilisateurId,
                description,
                dateDebut,
                dateFin,
                statut,
                dureeDevPersonnel,
                habitudeCible,
                joursRealises,
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