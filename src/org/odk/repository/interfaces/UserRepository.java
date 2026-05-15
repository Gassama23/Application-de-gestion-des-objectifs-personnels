package org.odk.repository.interfaces;

import org.odk.model.User;
import org.odk.model.Utilisateur;

public interface UserRepository {
	
	   User sauvegarder(User user);

	   User trouverParEmail(String email);

	   boolean emailExiste(String email);
	   
	   void mettreAJourStreak(Utilisateur utilisateur);
}
