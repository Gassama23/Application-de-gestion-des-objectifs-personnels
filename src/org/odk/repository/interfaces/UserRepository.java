package org.odk.repository.interfaces;

import java.util.List;

import org.odk.model.User;
import org.odk.model.Utilisateur;

public interface UserRepository {
	
	   User sauvegarder(User user);

	   User trouverParEmail(String email);

	   boolean emailExiste(String email);
	   
	   void mettreAJourStreak(Utilisateur utilisateur);
	   
	   int nbUtilsateurs();
	   
	   List<Utilisateur> listeUtilisateurs();
}
