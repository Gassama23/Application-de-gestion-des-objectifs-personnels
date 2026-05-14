CREATE TABLE IF NOT EXISTS utilisateur (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    mot_de_passe VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    streak_actuel INT DEFAULT 0,
    meilleur_streak INT DEFAULT 0,
    date_inscription DATE NOT NULL
);



CREATE TABLE IF NOT EXISTS objectif (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom_objectif VARCHAR(255) NOT NULL,
    description TEXT,
    date_debut DATE,
    date_fin DATE,
    statut VARCHAR(50),
    utilisateur_id INT NOT NULL,

    FOREIGN KEY (utilisateur_id)
        REFERENCES utilisateur(id)
        ON DELETE CASCADE
);



CREATE TABLE IF NOT EXISTS objectif_sport (
    objectif_id INT PRIMARY KEY,
    type_activite VARCHAR(100),
    duree_seance INT,
    nb_seances_realisees INT DEFAULT 0,
    frequence_hebdo INT,
    niveau_sportif VARCHAR(100),

    FOREIGN KEY (objectif_id)
        REFERENCES objectif(id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS objectif_economie (
    objectif_id INT PRIMARY KEY,
    type_economie VARCHAR(100),
    montant_cible DOUBLE,
    montant_epargne DOUBLE DEFAULT 0,
    delai_mois INT,

    FOREIGN KEY (objectif_id)
        REFERENCES objectif(id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS objectif_apprentissage (
    objectif_id INT PRIMARY KEY,
    type_apprentissage VARCHAR(100),
    sujet VARCHAR(255),
    duree_etude_par_jour INT,
    jours_etudies INT DEFAULT 0,
    ressource VARCHAR(255),

    FOREIGN KEY (objectif_id)
        REFERENCES objectif(id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS objectif_dev_personnel (
    objectif_id INT PRIMARY KEY,
    type_dev_personnel VARCHAR(100),
    duree_dev_personnel INT,
    jours_realises INT DEFAULT 0,
    habitude_cible VARCHAR(255),

    FOREIGN KEY (objectif_id)
        REFERENCES objectif(id)
        ON DELETE CASCADE
);



CREATE TABLE IF NOT EXISTS planning (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titre VARCHAR(255),
    date_creation DATE,
    actif BOOLEAN DEFAULT TRUE,
    objectif_id INT NOT NULL UNIQUE,

    FOREIGN KEY (objectif_id)
        REFERENCES objectif(id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS planning_detail (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titre_section VARCHAR(255),
    contenu TEXT,
    ordre_section INT,
    planning_id INT NOT NULL,

    FOREIGN KEY (planning_id)
        REFERENCES planning(id)
        ON DELETE CASCADE
);



CREATE TABLE IF NOT EXISTS action_quotidienne (
    id INT PRIMARY KEY AUTO_INCREMENT,
    description TEXT NOT NULL,
    date_prevue DATE NOT NULL,
    date_realisation DATE,
    statut VARCHAR(50) NOT NULL,
    commentaire TEXT,
    planning_id INT NOT NULL,

    FOREIGN KEY (planning_id)
        REFERENCES planning(id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS rappel (
    id INT PRIMARY KEY AUTO_INCREMENT,
    heure_rappel TIME NOT NULL,
    message TEXT NOT NULL,
    actif BOOLEAN DEFAULT TRUE,
    planning_id INT NOT NULL,

    FOREIGN KEY (planning_id)
        REFERENCES planning(id)
        ON DELETE CASCADE
);



CREATE TABLE IF NOT EXISTS progression (
    id INT PRIMARY KEY AUTO_INCREMENT,
    date_progression DATE NOT NULL,
    etat BOOLEAN NOT NULL,
    commentaire TEXT,
    objectif_id INT NOT NULL,
    action_quotidienne_id INT,

    FOREIGN KEY (objectif_id)
        REFERENCES objectif(id)
        ON DELETE CASCADE,

    FOREIGN KEY (action_quotidienne_id)
        REFERENCES action_quotidienne(id)
        ON DELETE SET NULL
);



CREATE TABLE IF NOT EXISTS badge (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    description TEXT,
    condition_succes INT NOT NULL
);

CREATE TABLE IF NOT EXISTS badge_utilisateur (
    id INT PRIMARY KEY AUTO_INCREMENT,
    date_obtention DATE NOT NULL,
    utilisateur_id INT NOT NULL,
    badge_id INT NOT NULL,

    UNIQUE (utilisateur_id, badge_id),

    FOREIGN KEY (utilisateur_id)
        REFERENCES utilisateur(id)
        ON DELETE CASCADE,

    FOREIGN KEY (badge_id)
        REFERENCES badge(id)
        ON DELETE CASCADE
);



CREATE TABLE IF NOT EXISTS notification (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titre VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    message TEXT NOT NULL,
    date_envoi DATE NOT NULL,
    lu BOOLEAN DEFAULT FALSE,
    utilisateur_id INT NOT NULL,

    FOREIGN KEY (utilisateur_id)
        REFERENCES utilisateur(id)
        ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS attentes_utilisateur (
    id INT PRIMARY KEY AUTO_INCREMENT,

    -- Économie
    revenu_mensuel DOUBLE DEFAULT 0,
    epargne_actuelle DOUBLE DEFAULT 0,
    montant_vise DOUBLE DEFAULT 0,
    delai_mois INT DEFAULT 0,
    comportement_financier INT DEFAULT 0,
    categories_depenses TEXT,
    situation_dettes INT DEFAULT 0,
    montant_dettes DOUBLE DEFAULT 0,
    fonds_urgence INT DEFAULT 0,

    -- Sport
    objectif_sport INT DEFAULT 0,
    poids_a_perdre DOUBLE DEFAULT 0,
    niveau INT DEFAULT 0,
    frequence_hebdo INT DEFAULT 0,
    duree_seance INT DEFAULT 0,
    acces_salle BOOLEAN DEFAULT FALSE,
    type_entrainement INT DEFAULT 0,
    contraintes_sante INT DEFAULT 0,

    -- Apprentissage
    sujet VARCHAR(255),
    objectif_final INT DEFAULT 0,
    temps_quotidien INT DEFAULT 0,
    style_apprentissage INT DEFAULT 0,
    historique INT DEFAULT 0,

    -- Développement personnel
    domaine_prioritaire INT DEFAULT 0,
    niveau_discipline INT DEFAULT 0,
    type_defi INT DEFAULT 0,

    -- Relations
    utilisateur_id INT NOT NULL,
    objectif_id INT NOT NULL UNIQUE,

    FOREIGN KEY (utilisateur_id)
        REFERENCES utilisateur(id)
        ON DELETE CASCADE,

    FOREIGN KEY (objectif_id)
        REFERENCES objectif(id)
        ON DELETE CASCADE
);

CREATE TABLE historique (
    id INT PRIMARY KEY AUTO_INCREMENT,
    utilisateur_id INT NOT NULL,
    objectif_id INT NOT NULL,
    action VARCHAR(255) NOT NULL,
    date_historique DATETIME NOT NULL,

    CONSTRAINT fk_historique_utilisateur
        FOREIGN KEY (utilisateur_id)
        REFERENCES utilisateur(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_historique_objectif
        FOREIGN KEY (objectif_id)
        REFERENCES objectif(id)
        ON DELETE CASCADE
);