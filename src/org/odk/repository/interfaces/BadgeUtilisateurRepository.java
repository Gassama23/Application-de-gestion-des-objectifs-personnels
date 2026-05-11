package org.odk.repository.interfaces;

import java.util.List;

import org.odk.model.BadgeUtilisateur;

public interface BadgeUtilisateurRepository {
	
	BadgeUtilisateur sauvegarder(BadgeUtilisateur badgeUtilisateur);

    boolean badgeDejaAttribue(int utilisateurId, int badgeId);

    List<BadgeUtilisateur> findByUtilisateurId(int utilisateurId);

}
