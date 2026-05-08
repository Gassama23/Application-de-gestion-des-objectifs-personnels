package org.odk.repository.interfaces;

import java.util.List;

import org.odk.model.Progression;

public interface ProgressionRepository {
Progression sauvegarder(Progression progress);
List<Progression> afficher();

//void modifierCommentaire(
//        int id,
//        String nouveauCommentaire
//);
}
