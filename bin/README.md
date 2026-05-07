# Application de gestion des objectifs personnels

Application Java permettant de définir, suivre et atteindre des objectifs personnels (apprentissage, sport, finances, développement personnel) sans utilisation de frameworks.

## ✨ Fonctionnalités implémentées

### ✅ Gestion des utilisateurs
- **Inscription** : Création de compte utilisateur ou admin
- **Connexion** : Authentification avec email et mot de passe
- **Rôles** : Distinction entre ADMIN et UTILISATEUR
- **Base de données MySQL** : Persistance des données

### 🎯 Fonctionnalités à venir
- Création et suivi d'objectifs
- Gestion des étapes
- Système de badges
- Notifications et rappels
- Statistiques et historique

## 🏗️ Architecture

```
┌─────────────────┐
│  Présentation   │  Main.java, MainTest.java
├─────────────────┤
│     Métier      │  User, Admin, Utilisateur
├─────────────────┤
│   Repository    │  UserRepository (CRUD)
├─────────────────┤
│   Base de données│  DatabaseConnection
├─────────────────┤
│   MySQL Server  │  gestion_objectifs
└─────────────────┘
```

## 🚀 Installation rapide

### Prérequis
- Java 17+
- MySQL Server
- Eclipse IDE

### Étapes

1. **Cloner le projet**
   ```bash
   git clone <url-du-repo>
   cd appGestionObjPerso
   ```

2. **Créer la base de données**
   ```bash
   mysql -u root -p < database/schema.sql
   ```

3. **Configurer Eclipse**
   - Importer le projet dans Eclipse
   - Ajouter `lib/mysql-connector-j-9.0.0.jar` au Build Path
   - Modifier les credentials dans `DatabaseConnection.java`

4. **Exécuter les tests**
   - Run `MainTest.java`

📖 **Guide complet** : Voir [INSTALLATION_JDBC.md](INSTALLATION_JDBC.md)

## 📁 Structure du projet

```
appGestionObjPerso/
├── src/org/odk/
│   ├── database/          # Connexion MySQL
│   ├── repository/        # Accès aux données
│   ├── model/             # Entités métier
│   ├── enums/             # Énumérations
│   └── MainTest.java      # Tests
├── database/
│   ├── schema.sql         # Script BDD
│   └── README.md          # Doc BDD
├── lib/
│   └── mysql-connector-j-9.0.0.jar
└── docs/
    ├── INSTALLATION_JDBC.md
    ├── ARCHITECTURE.md
    └── RESUME_IMPLEMENTATION.md
```

## 💻 Utilisation

### Inscription
```java
Utilisateur user = new Utilisateur("Dupont", "Jean", "jean@email.com", "password123");
user.sInscrire();
```

### Connexion
```java
Utilisateur user = new Utilisateur();
user.setEmail("jean@email.com");
user.setMotDePasse("password123");
user.seConnecter();

if (user.getId() > 0) {
    // Connexion réussie
    user.creerObjectif();
}
```

## 🗄️ Base de données

### Tables
- `users` - Utilisateurs et admins
- `objectifs` - Objectifs personnels
- `etapes` - Étapes des objectifs
- `notifications` - Notifications
- `badges` - Badges disponibles
- `users_badges` - Attribution des badges
- `rappels` - Rappels configurés

## 🔒 Sécurité

✅ **Implémenté**
- PreparedStatements (protection SQL injection)
- Vérification email unique
- Gestion des erreurs SQL

⚠️ **À implémenter pour production**
- Hashing des mots de passe (BCrypt)
- Validation des entrées
- Gestion des sessions
- Variables d'environnement

## 📚 Documentation

- [INSTALLATION_JDBC.md](INSTALLATION_JDBC.md) - Guide d'installation complet
- [ARCHITECTURE.md](ARCHITECTURE.md) - Architecture détaillée
- [RESUME_IMPLEMENTATION.md](RESUME_IMPLEMENTATION.md) - Résumé de l'implémentation
- [FICHIERS_CREES.md](FICHIERS_CREES.md) - Liste des fichiers créés
- [database/README.md](database/README.md) - Documentation BDD

## 🧪 Tests

Exécutez `MainTest.java` pour tester :
1. ✅ Inscription utilisateur
2. ✅ Inscription admin
3. ✅ Email déjà utilisé (échec attendu)
4. ✅ Connexion utilisateur
5. ✅ Connexion admin
6. ✅ Mauvais mot de passe (échec attendu)
7. ✅ Méthodes utilisateur
8. ✅ Méthodes admin

## 🛠️ Technologies

- **Langage** : Java 17
- **Base de données** : MySQL 8.0
- **Driver JDBC** : MySQL Connector/J 9.0.0
- **IDE** : Eclipse

## 📈 Prochaines étapes

1. Implémenter ObjectifRepository
2. Créer BadgeRepository
3. Ajouter hashing des mots de passe
4. Développer interface graphique (Swing/JavaFX)
5. Ajouter tests unitaires (JUnit)

## 👥 Auteur

Projet développé dans le cadre de l'apprentissage Java et JDBC.

## 📄 Licence

Ce projet est à des fins éducatives.
