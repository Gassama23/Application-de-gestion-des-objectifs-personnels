package org.odk.service;

import java.time.LocalTime;
import java.util.List;

import org.odk.model.Planning;
import org.odk.model.Rappel;
import org.odk.model.Utilisateur;
import org.odk.repository.interfaces.RappelRepository;
import org.odk.repository.jdbc.JdbcRappelRepository;

public class RappelService {

    private final RappelRepository rappelRepository;

    private final NotificationService notificationService;

    public RappelService() {

        this.rappelRepository = new JdbcRappelRepository();

        this.notificationService =
                new NotificationService();
    }

    public Rappel creerRappel(
            Planning planning,
            Utilisateur utilisateur,
            LocalTime heureRappel,
            String message
    ) {

        if (planning == null
                || planning.getId() <= 0) {

            System.err.println(
                    "Erreur : planning invalide."
            );

            return null;
        }

        if (utilisateur == null
                || utilisateur.getId() <= 0) {

            System.err.println(
                    "Erreur : utilisateur invalide."
            );

            return null;
        }

        if (heureRappel == null) {

            System.err.println(
                    "Erreur : heure du rappel obligatoire."
            );

            return null;
        }

        if (message == null
                || message.isBlank()) {

            System.err.println(
                    "Erreur : message obligatoire."
            );

            return null;
        }

        Rappel rappel =
                new Rappel();

        rappel.setPlanningId(
                planning.getId()
        );

        rappel.setHeureRappel(
                heureRappel
        );

        rappel.setMessage(message);

        rappel.setActif(true);

        Rappel rappelSauvegarde =
                rappelRepository.sauvegarder(
                        rappel
                );

        notificationService.notifierRappel(
                utilisateur,
                message
        );

        return rappelSauvegarde;
    }

    public List<Rappel>
    listerRappelsPlanning(
            int planningId
    ) {

        if (planningId <= 0) {

            System.err.println(
                    "Erreur : planning invalide."
            );

            return List.of();
        }

        return rappelRepository.findByPlanningId(
                planningId
        );
    }

    public void activerRappel(
            int rappelId
    ) {

        if (rappelId <= 0) {

            System.err.println(
                    "Erreur : rappel invalide."
            );

            return;
        }

        rappelRepository.activer(rappelId);
    }

    public void desactiverRappel(
            int rappelId
    ) {

        if (rappelId <= 0) {

            System.err.println(
                    "Erreur : rappel invalide."
            );

            return;
        }

        rappelRepository.desactiver(rappelId);
    }
}