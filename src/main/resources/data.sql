-- Script de création de la base de données et des données de base pour CDAPizza
-- ATTENTION: Ce fichier est exécuté automatiquement au démarrage de l'application
-- Assurez-vous que la base de données 'cdapizza' existe déjà

-- Insertion des pizzas de base
INSERT INTO pizzas (nom, description, prix, disponible) VALUES
('Margherita', 'Tomate, mozzarella, basilic frais', 8.50, true),
('Pepperoni', 'Tomate, mozzarella, pepperoni', 10.50, true),
('Quatre Fromages', 'Tomate, mozzarella, gorgonzola, parmesan, chèvre', 12.00, true),
('Chorizo', 'Tomate, mozzarella, chorizo, oignons', 11.00, true),
('Végétarienne', 'Tomate, mozzarella, légumes grillés, olives', 9.50, true),
('Hawaïenne', 'Tomate, mozzarella, jambon, ananas', 10.00, true),
('Calzone', 'Tomate, mozzarella, jambon, champignons (pizza pliée)', 11.50, true),
('Diavola', 'Tomate, mozzarella, salami piquant, piments', 11.00, true),
('Prosciutto', 'Tomate, mozzarella, jambon de Parme, roquette', 13.00, true),
('Quatre Saisons', 'Tomate, mozzarella, jambon, champignons, artichauts, olives', 12.50, true);

-- Insertion de quelques commandes d'exemple (optionnel pour le POC)
-- Ces commandes seront créées automatiquement lors des tests

-- Note: Les commandes d'exemple sont commentées car elles seront créées via l'interface
-- INSERT INTO commandes (nom_client, date_commande, montant, etat) VALUES
-- ('Jean Dupont', NOW(), 19.00, 'PAYER'),
-- ('Marie Martin', NOW(), 25.50, 'PREPARATION'),
-- ('Pierre Durand', NOW(), 16.50, 'PRETE');

-- Note: Les lignes de commande seront créées automatiquement via l'interface
-- INSERT INTO lignes_commande (commande_id, pizza_id, quantite) VALUES
-- (1, 1, 1), (1, 2, 1),
-- (2, 3, 1), (2, 4, 1),
-- (3, 1, 2);
