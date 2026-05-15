# 📦 Package Service

## 🎯 Rôle

Le package **service** contient la **logique métier** de l'application.

---

## 📁 Contenu

### Services disponibles

1. **UserService.java** - Service de base
2. **AdminService.java** - Service admin
3. **UtilisateurService.java** - Service utilisateur

---

## 🏗️ Architecture

```
Model (Admin/Utilisateur)
    ↓ Délègue
Service (AdminService/UtilisateurService)
    ↓ Utilise
Service (UserService)
    ↓ Appelle
Repository (UserRepository)
```

---

## 📝 UserService.java

### Rôle
Service de base pour tous les utilisateurs (Admin et Utilisateur).

### Responsabilités
- ✅ Validation des données (email, mot de passe)
- ✅ Règles métier communes
- ✅ Coordination avec le Repository

### Méthodes principales

```java
// Inscription avec validation
public boolean inscrire(User user)

// Connexion
public User connecter(String email, String motDePasse)

// Vérification email
public boolean emailExiste(String email)

// Validation email
public boolean validerEmail(String email)

// Validation mot de passe
public boolean validerMotDePasse(String motDePasse)
```

### Exemple d'utilisation

```java
UserService userService = new UserService();

// Inscription
User user = new Utilisateur("Dupont", "Jean", "jean@email.com", "password123");
boolean success = userService.inscrire(user);

// Connexion
User connectedUser = userService.connecter("jean@email.com", "password123");
```

---

## 👨‍💼 AdminService.java

### Rôle
Service spécifique aux administrateurs.

### Responsabilités
- ✅ Gestion des fonctionnalités admin
- ✅ Vérification des droits admin
- ✅ Utilise UserService pour les opérations communes

### Méthodes principales

```java
// Inscription admin
public boolean inscrireAdmin(Admin admin)

// Connexion admin
public Admin connecterAdmin(String email, String motDePasse)

// Voir statistiques globales
public void voirStatistiques(Admin admin)

// Voir tous les objectifs
public void voirTousLesObjectifs(Admin admin)

// Tester fonctionnalités utilisateur
public void testerFonctionnalitesUtilisateur(Admin admin)
```

### Exemple d'utilisation

```java
AdminService adminService = new AdminService();

// Inscription
Admin admin = new Admin("Martin", "Sophie", "admin@email.com", "admin123");
boolean success = adminService.inscrireAdmin(admin);

// Connexion
Admin connectedAdmin = adminService.connecterAdmin("admin@email.com", "admin123");

// Utilisation
if (connectedAdmin != null) {
    adminService.voirStatistiques(connectedAdmin);
}
```

---

## 👤 UtilisateurService.java

### Rôle
Service spécifique aux utilisateurs réguliers.

### Responsabilités
- ✅ Gestion des fonctionnalités utilisateur
- ✅ Vérification de connexion
- ✅ Utilise UserService pour les opérations communes

### Méthodes principales

```java
// Inscription utilisateur
public boolean inscrireUtilisateur(Utilisateur utilisateur)

// Connexion utilisateur
public Utilisateur connecterUtilisateur(String email, String motDePasse)

// Modifier profil
public void modifierProfil(Utilisateur utilisateur)

// Créer objectif
public void creerObjectif(Utilisateur utilisateur)

// Consulter objectifs
public void consulterObjectifs(Utilisateur utilisateur)

// Consulter historique
public void consulterHistorique(Utilisateur utilisateur)

// Consulter notifications
public void consulterNotifications(Utilisateur utilisateur)

// Consulter badges
public void consulterBadges(Utilisateur utilisateur)

// Configurer rappel
public void configurerRappel(Utilisateur utilisateur)

// Demander conseils
public void demanderConseils(Utilisateur utilisateur)

// Valider étape
public void validerEtape(Utilisateur utilisateur)
```

### Exemple d'utilisation

```java
UtilisateurService utilisateurService = new UtilisateurService();

// Inscription
Utilisateur user = new Utilisateur("Dupont", "Jean", "jean@email.com", "password123");
boolean success = utilisateurService.inscrireUtilisateur(user);

// Connexion
Utilisateur connectedUser = utilisateurService.connecterUtilisateur("jean@email.com", "password123");

// Utilisation
if (connectedUser != null) {
    utilisateurService.creerObjectif(connectedUser);
    utilisateurService.consulterObjectifs(connectedUser);
}
```

---

## 🔄 Flux de données

### Inscription

```
1. Model (Admin/Utilisateur)
   user.sInscrire()
   
2. Service spécifique (AdminService/UtilisateurService)
   adminService.inscrireAdmin(user)
   
3. Service de base (UserService)
   userService.inscrire(user)
   - Validation email
   - Validation mot de passe
   - Vérification email unique
   
4. Repository (UserRepository)
   userRepository.inscrireUtilisateur(user)
   
5. Database
   INSERT INTO users ...
```

### Connexion

```
1. Model (Admin/Utilisateur)
   user.seConnecter()
   
2. Service spécifique (AdminService/UtilisateurService)
   adminService.connecterAdmin(email, password)
   
3. Service de base (UserService)
   userService.connecter(email, password)
   - Validation
   
4. Repository (UserRepository)
   userRepository.connecterUtilisateur(email, password)
   
5. Database
   SELECT * FROM users WHERE email = ? AND mot_de_passe = ?
```

---

## ✅ Règles à suivre

### À FAIRE dans un Service

1. **Valider les données**
   ```java
   if (!validerEmail(user.getEmail())) {
       return false;
   }
   ```

2. **Appliquer les règles métier**
   ```java
   if (userRepository.emailExiste(user.getEmail())) {
       System.err.println("Email déjà utilisé");
       return false;
   }
   ```

3. **Coordonner les repositories**
   ```java
   return userRepository.inscrireUtilisateur(user);
   ```

4. **Gérer les erreurs**
   ```java
   if (user == null) {
       System.err.println("Erreur : User null");
       return false;
   }
   ```

### À NE PAS FAIRE dans un Service

1. ❌ **Accéder directement à la base de données**
   ```java
   // ❌ Mauvais
   Connection conn = DriverManager.getConnection(...);
   
   // ✅ Bon
   userRepository.inscrireUtilisateur(user);
   ```

2. ❌ **Contenir du code SQL**
   ```java
   // ❌ Mauvais
   String sql = "INSERT INTO users ...";
   
   // ✅ Bon
   userRepository.inscrireUtilisateur(user);
   ```

3. ❌ **Gérer les connexions**
   ```java
   // ❌ Mauvais
   Connection conn = DatabaseConnection.getConnection();
   
   // ✅ Bon
   Laisser le Repository gérer les connexions
   ```

---

## 🎓 Patterns utilisés

### 1. Service Layer Pattern
- Séparation de la logique métier
- Réutilisabilité du code

### 2. Dependency Injection (simple)
- Chaque Service a ses dépendances
- Injection via constructeur

### 3. Validation centralisée
- Toutes les validations dans UserService
- Pas de duplication

---

## 🧪 Tests

### Tester les Services

```java
// Test UserService
UserService userService = new UserService();

// Test validation email
boolean valid = userService.validerEmail("test@email.com");
System.out.println("Email valide : " + valid);

// Test validation mot de passe
boolean validMdp = userService.validerMotDePasse("password123");
System.out.println("Mot de passe valide : " + validMdp);

// Test inscription
User user = new Utilisateur("Nom", "Prenom", "email@test.com", "password");
boolean success = userService.inscrire(user);
System.out.println("Inscription : " + success);
```

---

## 📚 Documentation

Pour plus d'informations :

- **[ARCHITECTURE_SERVICE.md](../../../../ARCHITECTURE_SERVICE.md)** - Architecture complète
- **[RESUME_ARCHITECTURE_MRS.md](../../../../RESUME_ARCHITECTURE_MRS.md)** - Résumé visuel
- **[MIGRATION_VERS_SERVICE.md](../../../../MIGRATION_VERS_SERVICE.md)** - Guide de migration

---

## 🚀 Prochaines étapes

### Pour étendre

1. **Créer ObjectifService**
   ```java
   public class ObjectifService {
       private ObjectifRepository objectifRepository;
       
       public boolean creerObjectif(Objectif objectif) {
           // Validation + règles métier
       }
   }
   ```

2. **Créer BadgeService**
   ```java
   public class BadgeService {
       private BadgeRepository badgeRepository;
       
       public void attribuerBadge(User user, Badge badge) {
           // Logique d'attribution
       }
   }
   ```

3. **Créer NotificationService**
   ```java
   public class NotificationService {
       private NotificationRepository notificationRepository;
       
       public void envoyerNotification(User user, String message) {
           // Logique d'envoi
       }
   }
   ```

---

## ✨ Résumé

### Services disponibles
- ✅ UserService (base)
- ✅ AdminService (admin)
- ✅ UtilisateurService (utilisateur)

### Responsabilités
- ✅ Validation des données
- ✅ Règles métier
- ✅ Coordination repositories

### Avantages
- ✅ Code réutilisable
- ✅ Testable
- ✅ Maintenable

**Les Services sont le cœur de la logique métier !** 🎯
