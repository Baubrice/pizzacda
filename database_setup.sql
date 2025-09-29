-- Script de création de la base de données et de l'utilisateur pour CDAPizza
-- À exécuter en tant qu'administrateur MySQL

-- Création de la base de données
CREATE DATABASE IF NOT EXISTS cdapizza 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

-- Création de l'utilisateur
CREATE USER IF NOT EXISTS 'cdapizza_user'@'localhost' IDENTIFIED BY 'cdapizza_password';

-- Attribution des privilèges sur la base de données
GRANT ALL PRIVILEGES ON cdapizza.* TO 'cdapizza_user'@'localhost';

-- Application des privilèges
FLUSH PRIVILEGES;

-- Affichage des informations de connexion
SELECT 'Base de données créée avec succès!' as message;
SELECT 'Utilisateur: cdapizza_user' as utilisateur;
SELECT 'Mot de passe: cdapizza_password' as mot_de_passe;
SELECT 'Base de données: cdapizza' as base_de_donnees;
SELECT 'Host: localhost' as host;
SELECT 'Port: 3306' as port;
