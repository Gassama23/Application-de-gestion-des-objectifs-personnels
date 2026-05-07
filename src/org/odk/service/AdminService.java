package org.odk.service;

import org.odk.model.Admin;
import org.odk.model.User;
import org.odk.enums.EnumRole;
import org.odk.repository.UserRepository;

/**
 * Service pour la gestion des administrateurs
 * Contient la logique métier spécifique aux admins
 */
public class AdminService {
    
    private UserRepository userRepository;
    private UserService userService;
    
    public AdminService() {
        this.userRepository = new UserRepository();
        this.userService = new UserService();
    }
    
    /**
     * Inscrire un nouvel administrateur
     * @param admin L'admin à inscrire
     * @return true si succès, false sinon
     */
    public boolean inscrireAdmin(Admin admin) {
        // Utiliser la validation du UserService
        return userService.inscrire(admin);
    }
    
    /**
     * Connecter un administrateur
     * @param email L'email de l'admin
     * @param motDePasse Le mot de passe
     * @return L'admin connecté ou null si échec
     */
    public Admin connecterAdmin(String email, String motDePasse) {
        User user = userService.connecter(email, motDePasse);
        
        // Vérifier que c'est bien un admin
        if (user != null && user.getRole() == EnumRole.ADMIN) {
            if (user instanceof Admin) {
                return (Admin) user;
            }
        } else if (user != null) {
            System.err.println("Erreur : Cet utilisateur n'est pas un administrateur");
        }
        
        return null;
    }
    
    /**
     * Voir les statistiques globales
     * @param admin L'admin qui demande les stats
     */
    public void voirStatistiques(Admin admin) {
        if (admin == null || admin.getRole() != EnumRole.ADMIN) {
            System.err.println("Erreur : Accès refusé. Droits administrateur requis.");
            return;
        }
        
        System.out.println("=== Statistiques globales ===");
        // TODO: Implémenter la logique pour récupérer les statistiques
        System.out.println("Nombre total d'utilisateurs : [À implémenter]");
        System.out.println("Nombre d'objectifs créés : [À implémenter]");
        System.out.println("Taux de complétion moyen : [À implémenter]");
    }
    
    /**
     * Voir tous les objectifs des utilisateurs
     * @param admin L'admin qui demande les objectifs
     */
    public void voirTousLesObjectifs(Admin admin) {
        if (admin == null || admin.getRole() != EnumRole.ADMIN) {
            System.err.println("Erreur : Accès refusé. Droits administrateur requis.");
            return;
        }
        
        System.out.println("=== Tous les objectifs ===");
        // TODO: Implémenter la logique pour récupérer tous les objectifs
        System.out.println("[À implémenter]");
    }
    
    /**
     * Tester les fonctionnalités utilisateur
     * @param admin L'admin qui effectue le test
     */
    public void testerFonctionnalitesUtilisateur(Admin admin) {
        if (admin == null || admin.getRole() != EnumRole.ADMIN) {
            System.err.println("Erreur : Accès refusé. Droits administrateur requis.");
            return;
        }
        
        System.out.println("=== Test des fonctionnalités utilisateur ===");
        // TODO: Implémenter la logique de test
        System.out.println("[À implémenter]");
    }
}
