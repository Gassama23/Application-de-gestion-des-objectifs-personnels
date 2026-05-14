INSERT INTO badge (nom, description, condition_succes)
SELECT 'Premier pas', 'Attribué après la première action réalisée.', 1
WHERE NOT EXISTS (SELECT 1 FROM badge WHERE nom = 'Premier pas');

INSERT INTO badge (nom, description, condition_succes)
SELECT 'Discipliné', 'Attribué après 7 actions réalisées.', 7
WHERE NOT EXISTS (SELECT 1 FROM badge WHERE nom = 'Discipliné');

INSERT INTO badge (nom, description, condition_succes)
SELECT 'Persévérant', 'Attribué après 30 actions réalisées.', 30
WHERE NOT EXISTS (SELECT 1 FROM badge WHERE nom = 'Persévérant');

INSERT INTO badge (nom, description, condition_succes)
SELECT 'Légende',
       'Attribué après 365 actions réalisées.',
       365
WHERE NOT EXISTS (
    SELECT 1 FROM badge WHERE nom = 'Légende'
);

INSERT INTO badge (nom, description, condition_succes)
SELECT 'Objectif atteint',
       'Attribué après finalisation complète d’un objectif.',
       999
WHERE NOT EXISTS (
    SELECT 1 FROM badge WHERE nom = 'Objectif atteint'
);

INSERT INTO badge (nom, description, condition_succes)
SELECT 'Régulier',
       'Attribué après 15 actions réalisées.',
       15
WHERE NOT EXISTS (
    SELECT 1 FROM badge WHERE nom = 'Régulier'
);

INSERT INTO badge (nom, description, condition_succes)
SELECT 'Persévérant',
       'Attribué après 30 actions réalisées.',
       30
WHERE NOT EXISTS (
    SELECT 1 FROM badge WHERE nom = 'Persévérant'
);

INSERT INTO badge (nom, description, condition_succes)
SELECT 'Champion',
       'Attribué après 50 actions réalisées.',
       50
WHERE NOT EXISTS (
    SELECT 1 FROM badge WHERE nom = 'Champion'
);

INSERT INTO badge (nom, description, condition_succes)
SELECT 'Machine de discipline',
       'Attribué après 100 actions réalisées.',
       100
WHERE NOT EXISTS (
    SELECT 1 FROM badge WHERE nom = 'Machine de discipline'
);