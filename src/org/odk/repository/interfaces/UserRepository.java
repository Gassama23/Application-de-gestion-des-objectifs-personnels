package org.odk.repository.interfaces;

import org.odk.model.User;

public interface UserRepository {
	
	   User sauvegarder(User user);

	   User trouverParEmail(String email);

	   boolean emailExiste(String email);
}
