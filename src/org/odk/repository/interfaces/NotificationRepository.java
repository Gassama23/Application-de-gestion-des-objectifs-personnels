package org.odk.repository.interfaces;

import java.util.List;

import org.odk.model.Notification;

public interface NotificationRepository {
	
	Notification sauvegarder(Notification notification);

    List<Notification> findByUtilisateurId(int utilisateurId);

    void marquerCommeLue(int notificationId);

}
