package org.odk.ui;

import org.odk.model.AttentesUtilisateur;
import org.odk.model.Objectif;
import org.odk.model.ObjectifApprentissage;
import org.odk.model.ObjectifDevPersonnel;
import org.odk.model.ObjectifEconomie;
import org.odk.model.ObjectifSport;

import org.odk.util.SaisieHelper;

public class AttentesUtilisateurConsoleView {

    public AttentesUtilisateur collecterAttentes(
            Objectif objectif,
            int utilisateurId
    ) {

        AttentesUtilisateur attentes =
                new AttentesUtilisateur();

        attentes.setUtilisateurId(utilisateurId);

        attentes.setObjectifId(objectif.getId());

        if (objectif instanceof ObjectifEconomie) {
            return questionnaireEconomie(attentes);
        }

        if (objectif instanceof ObjectifSport) {
            return questionnaireSport(attentes);
        }

        if (objectif instanceof ObjectifApprentissage) {
            return questionnaireApprentissage(attentes);
        }

        if (objectif instanceof ObjectifDevPersonnel) {
            return questionnaireDevPersonnel(attentes);
        }

        return attentes;
    }
    
    private AttentesUtilisateur questionnaireSport(AttentesUtilisateur attentes) {

        System.out.println("\n===== QUESTIONNAIRE SPORT =====");

        attentes.setObjectifSport(
                SaisieHelper.lireChoix(
                        "Votre objectif sport ? 1. Perte de poids  2. Prise de muscle  3. Endurance  4. Remise en forme : ",
                        1,
                        4
                )
        );

        attentes.setPoidsAPerdre(
                SaisieHelper.lireDouble("Poids à perdre en kg (0 si aucun) : ")
        );

        attentes.setNiveau(
                SaisieHelper.lireChoix(
                        "Votre niveau ? 1. Débutant  2. Moyen  3. Avancé : ",
                        1,
                        3
                )
        );

        attentes.setFrequenceHebdo(
                SaisieHelper.lireEntier("Combien de séances par semaine pouvez-vous faire ? ")
        );

        attentes.setDureeSeance(
                SaisieHelper.lireEntier("Durée d'une séance en minutes : ")
        );

        attentes.setAccesSalle(
                SaisieHelper.lireBoolean("Avez-vous accès à une salle de sport ?")
        );

        attentes.setTypeEntrainement(
                SaisieHelper.lireChoix(
                        "Type d'entraînement préféré ? 1. Maison  2. Salle  3. Course  4. Mixte : ",
                        1,
                        4
                )
        );

        attentes.setContraintesSante(
                SaisieHelper.lireChoix(
                        "Avez-vous des contraintes santé ? 1. Non  2. Oui : ",
                        1,
                        2
                )
        );

        return attentes;
    }
    
    private AttentesUtilisateur questionnaireApprentissage(AttentesUtilisateur attentes) {

        System.out.println("\n===== QUESTIONNAIRE APPRENTISSAGE =====");


        attentes.setObjectifFinal(
                SaisieHelper.lireChoix(
                        "Votre objectif final ? 1. Comprendre les bases  2. Être autonome  3. Niveau professionnel  4. Préparer un examen : ",
                        1,
                        4
                )
        );

        attentes.setHistorique(
                SaisieHelper.lireChoix(
                        "Avez-vous déjà des bases ? 1. Non  2. Un peu  3. Oui : ",
                        1,
                        3
                )
        );

        return attentes;
    }
    
    private AttentesUtilisateur questionnaireDevPersonnel(AttentesUtilisateur attentes) {

        System.out.println("\n===== QUESTIONNAIRE DÉVELOPPEMENT PERSONNEL =====");

        attentes.setDomainePrioritaire(
                SaisieHelper.lireChoix(
                        "Domaine prioritaire ? 1. Discipline  2. Lecture  3. Organisation  4. Confiance en soi : ",
                        1,
                        4
                )
        );

        attentes.setNiveauDiscipline(
                SaisieHelper.lireChoix(
                        "Votre niveau de discipline actuel ? 1. Faible  2. Moyen  3. Bon : ",
                        1,
                        3
                )
        );

        attentes.setTypeDefi(
                SaisieHelper.lireChoix(
                        "Type de défi ? 1. Quotidien  2. Hebdomadaire  3. Progressif : ",
                        1,
                        3
                )
        );

        attentes.setTempsQuotidien(
                SaisieHelper.lireEntier("Combien de minutes par jour pouvez-vous consacrer ? ")
        );

        return attentes;
    }
    
    private AttentesUtilisateur questionnaireEconomie(AttentesUtilisateur attentes) {

        System.out.println("\n===== QUESTIONNAIRE ÉCONOMIE =====");

        attentes.setRevenuMensuel(
                SaisieHelper.lireDouble("Quel est votre revenu mensuel ? ")
        );

        attentes.setEpargneActuelle(
                SaisieHelper.lireDouble("Combien avez-vous déjà épargné ? ")
        );

        attentes.setComportementFinancier(
                SaisieHelper.lireChoix(
                        "Dépensez-vous beaucoup ? 1. Peu  2. Moyen  3. Beaucoup : ",
                        1,
                        3
                )
        );

        attentes.setCategoriesDepenses(
                SaisieHelper.lireTexte(
                        "Quelles sont vos principales dépenses ? "
                )
        );

        attentes.setSituationDettes(
                SaisieHelper.lireChoix(
                        "Avez-vous des dettes ? 1. Non  2. Oui : ",
                        1,
                        2
                )
        );

        if (attentes.getSituationDettes() == 2) {
            attentes.setMontantDettes(
                    SaisieHelper.lireDouble("Montant total des dettes : ")
            );
        }

        attentes.setFondsUrgence(
                SaisieHelper.lireChoix(
                        "Avez-vous un fonds d’urgence ? 1. Non  2. Oui : ",
                        1,
                        2
                )
        );

        return attentes;
    }
  }