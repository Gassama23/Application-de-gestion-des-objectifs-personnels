package org.odk;


import org.odk.model.BadgeUtilisateur;
import org.odk.repository.BadgeUtilisateurRepository;

import java.time.LocalDate;

public class MainUtilisateurBadge {

    public static void main(String[] args) {

        BadgeUtilisateur b =
            new BadgeUtilisateur(
                0, // id ignoré (AUTO_INCREMENT)
                1, // utilisateur_id
                1, // badge_id
                LocalDate.now()
            );

        BadgeUtilisateurRepository dao = new BadgeUtilisateurRepository();

        dao.ajouter(b);
    }
}