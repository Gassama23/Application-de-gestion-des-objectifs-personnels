package org.odk.service;

import org.odk.model.User;
import org.odk.repository.interfaces.UserRepository;
import org.odk.repository.jdbc.JdbcRepositoryUser;
import org.odk.util.MotDePasseHelper;

/**
 * Service pour la gestion des utilisateurs
 * Contient la logique métier
 */
public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new JdbcRepositoryUser();
    }

    /**
     * Inscrire un nouvel utilisateur
     *
     * @param user utilisateur à inscrire
     * @return utilisateur sauvegardé ou null si erreur
     */
    public User inscrire(User user) {

        // Validation métier
        if (user == null) {
            System.err.println("Erreur : L'utilisateur ne peut pas être null");
            return null;
        }

        if (user.getNom() == null || user.getNom().trim().isEmpty()) {
            System.err.println("Erreur : Le nom est obligatoire");
            return null;
        }

        if (user.getPrenom() == null || user.getPrenom().trim().isEmpty()) {
            System.err.println("Erreur : Le prénom est obligatoire");
            return null;
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            System.err.println("Erreur : L'email est obligatoire");
            return null;
        }

        // Validation format email
        if (!validerEmail(user.getEmail())) {
            System.err.println("Erreur : Format email invalide");
            return null;
        }

        // Validation mot de passe
        if (!validerMotDePasse(user.getMotDePasse())) {
            System.err.println("Erreur : Le mot de passe doit contenir au moins 6 caractères");
            return null;
        }

        // Vérifier si l'email existe déjà
        if (userRepository.emailExiste(user.getEmail())) {
            System.err.println("Erreur : Cet email est déjà utilisé : " + user.getEmail());
            return null;
        }

        /*
         * Hasher le mot de passe avant sauvegarde.
         * Le mot de passe ne sera jamais stocké en clair.
         */
        String motDePasseHashe =
                MotDePasseHelper.hasherMotDePasse(
                        user.getMotDePasse()
                );

        user.setMotDePasse(motDePasseHashe);

        // Sauvegarder utilisateur
        User userSauvegarde =
                userRepository.sauvegarder(user);

        if (userSauvegarde != null) {
            System.out.println("✓ Inscription réussie pour : "
                    + userSauvegarde.getEmail());
        } else {
            System.err.println("✗ Échec de l'inscription.");
        }

        return userSauvegarde;
    }

    /**
     * Connecter un utilisateur
     *
     * @param email email utilisateur
     * @param motDePasse mot de passe saisi
     * @return utilisateur connecté ou null
     */
    public User connecter(String email, String motDePasse) {

        // Validation métier
        if (email == null || email.trim().isEmpty()) {
            System.err.println("Erreur : L'email est obligatoire");
            return null;
        }

        if (motDePasse == null || motDePasse.trim().isEmpty()) {
            System.err.println("Erreur : Le mot de passe est obligatoire");
            return null;
        }

        // Recherche utilisateur par email
        User user = userRepository.trouverParEmail(email);

        // Vérifier si utilisateur existe
        if (user == null) {
            System.err.println("✗ Aucun utilisateur trouvé avec cet email.");
            return null;
        }

        /*
         * Vérification du mot de passe :
         * - on hash le mot de passe saisi
         * - on compare avec le hash stocké en base
         */
        boolean motDePasseCorrect =
                MotDePasseHelper.verifierMotDePasse(
                        motDePasse,
                        user.getMotDePasse()
                );

        if (!motDePasseCorrect) {
            System.err.println("✗ Mot de passe incorrect.");
            return null;
        }

        System.out.println("✓ Connexion réussie pour : "
                + user.getEmail());

        return user;
    }

    /**
     * Vérifier si un email existe déjà
     */
    public boolean emailExiste(String email) {

        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        return userRepository.emailExiste(email);
    }

    /**
     * Validation format email
     */
    public boolean validerEmail(String email) {

        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        String emailRegex =
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        return email.matches(emailRegex);
    }

    /**
     * Validation mot de passe
     */
    public boolean validerMotDePasse(String motDePasse) {

        if (motDePasse == null) {
            return false;
        }

        return motDePasse.length() >= 6;
    }
}