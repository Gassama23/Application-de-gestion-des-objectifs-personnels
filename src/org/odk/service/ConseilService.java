package org.odk.service;

import org.odk.model.Objectif;
import org.odk.model.ObjectifApprentissage;
import org.odk.model.ObjectifDevPersonnel;
import org.odk.model.ObjectifEconomie;
import org.odk.model.ObjectifSport;

public class ConseilService {

    public String genererConseil(Objectif objectif) {

        if (objectif instanceof ObjectifEconomie) {
            return conseilEconomie();
        }

        if (objectif instanceof ObjectifSport) {
            return conseilSport();
        }

        if (objectif instanceof ObjectifApprentissage) {
            return conseilApprentissage();
        }

        if (objectif instanceof ObjectifDevPersonnel) {
            return conseilDevPersonnel();
        }

        return "Restez régulier et avancez chaque jour vers votre objectif.";
    }

    private String conseilEconomie() {
        return "Épargnez une petite somme chaque jour et évitez les dépenses non essentielles.";
    }

    private String conseilSport() {
        return "Respectez votre fréquence d’entraînement et progressez doucement pour éviter les blessures.";
    }

    private String conseilApprentissage() {
        return "Étudiez un peu chaque jour, faites des exercices pratiques et révisez régulièrement.";
    }

    private String conseilDevPersonnel() {
        return "Commencez par une petite habitude quotidienne et suivez votre régularité.";
    }
}