package org.odk.repository.interfaces;

import java.util.List;
import org.odk.model.AttentesUtilisateur;

public interface AttentesUtilisateurRepository {

    void save(AttentesUtilisateur attentes);

    void update(AttentesUtilisateur attentes);

    void delete(int id);

    AttentesUtilisateur findById(int id);

    List<AttentesUtilisateur> findAll();

    AttentesUtilisateur findByUtilisateurId(int utilisateurId);

    AttentesUtilisateur findByObjectifId(int objectifId);
}