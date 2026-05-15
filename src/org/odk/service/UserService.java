package org.odk.service;

import org.odk.model.User;
import org.odk.model.Utilisateur;
import org.odk.repository.interfaces.UserRepository;
import org.odk.repository.jdbc.JdbcRepositoryUser;
import org.odk.util.MotDePasseHelper;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new JdbcRepositoryUser();
    }

    public User inscrire(User user) {
        if (user == null) {
            System.err.println("Erreur : utilisateur invalide.");
            return null;
        }
        
        if (user.getNom() == null || user.getNom().isBlank()) {
            System.err.println("Erreur : nom obligatoire.");
            return null;
        }

        if (user.getPrenom() == null || user.getPrenom().isBlank()) {
            System.err.println("Erreur : prénom obligatoire.");
            return null;
        }

        if (!validerEmail(user.getEmail())) {
            System.err.println("Erreur : email invalide.");
            return null;
        }

        if (!validerMotDePasse(user.getMotDePasse())) {
            System.err.println("Erreur : mot de passe trop court.");
            return null;
        }

        if (userRepository.emailExiste(user.getEmail())) {
            System.err.println("Erreur : cet email existe déjà.");
            return null;
        }

        String motDePasseHashe = MotDePasseHelper.hasherMotDePasse(user.getMotDePasse());

        user.setMotDePasse(motDePasseHashe);

        return userRepository.sauvegarder(user);
    }

    public User connecter(String email, String motDePasse) {

        if (!validerEmail(email)) {
            System.err.println("Erreur : email invalide.");
            return null;
        }

        if (motDePasse == null || motDePasse.isBlank()) {
            System.err.println("Erreur : mot de passe obligatoire.");
            return null;
        }

        User user = userRepository.trouverParEmail(email);

        if (user == null) {
            System.err.println("Erreur : aucun compte trouvé.");
            return null;
        }

        boolean motDePasseCorrect = MotDePasseHelper.verifierMotDePasse(motDePasse, user.getMotDePasse());

        if (!motDePasseCorrect) {
            System.err.println("Erreur : mot de passe incorrect.");
            return null;
        }

        return user;
    }

    public boolean emailExiste(String email) {

        if (email == null || email.isBlank()) {
            return false;
        }

        return userRepository.emailExiste(email);
    }

    public boolean validerEmail(String email) {

        if (email == null || email.isBlank()) {
            return false;
        }

        String regex =
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        return email.matches(regex);
    }
    
    public void mettreAJourStreak(Utilisateur utilisateur) {

        if (utilisateur == null || utilisateur.getId() <= 0) {

            System.err.println("Utilisateur invalide.");

            return;
        }

        userRepository.mettreAJourStreak(utilisateur);
    }

    public boolean validerMotDePasse(String motDePasse) {

        if (motDePasse == null) {
            return false;
        }

        return motDePasse.length() >= 6;
    }
}