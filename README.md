# CDAPizza - Système de Gestion des Commandes

## Description

Application de gestion des commandes pour la pizzeria CDAPizza. Cette application permet de gérer les commandes en temps réel entre la caisse et la cuisine.

## Fonctionnalités

### Interface Caisse
- Saisie de nouvelles commandes
- Affichage des commandes récentes
- Mise à jour de l'état des commandes (PAYER → LIVRER)
- Détails des commandes

### Interface Cuisine
- Affichage des commandes en cours
- Mise à jour de l'état des commandes (PREPARATION → PRETE)
- Suivi en temps réel des commandes
- Détails des pizzas à préparer

### États des Commandes
1. **PAYER** - Commande payée
2. **PREPARATION** - En cours de préparation
3. **PRETE** - Prête à être livrée
4. **LIVRER** - Livrée au client

## Technologies Utilisées

- **Java 21**
- **Spring Boot 3.5.6**
- **Spring Data JPA**
- **MySQL**
- **Thymeleaf**
- **Bootstrap 5**
- **Maven**

## Prérequis

- Java 21 ou supérieur
- MySQL 8.0 ou supérieur
- Maven 3.6 ou supérieur

## Installation

### 1. Configuration de la Base de Données

Exécutez le script `database_setup.sql` en tant qu'administrateur MySQL :

```sql
mysql -u root -p < database_setup.sql
```

### 2. Configuration de l'Application

Les paramètres de connexion à la base de données sont configurés dans `src/main/resources/application.properties` :

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cdapizza
spring.datasource.username=cdapizza
spring.datasource.password=cdapizzaafpa
```

### 3. Lancement de l'Application

```bash
mvn spring-boot:run
```

L'application sera accessible à l'adresse : http://localhost:8080

## Utilisation

### Page d'Accueil
- Accès aux interfaces caisse et cuisine
- Vue d'ensemble du système

### Interface Caisse (`/caisse`)
- Créer une nouvelle commande
- Consulter les commandes récentes
- Marquer une commande comme livrée

### Interface Cuisine (`/cuisine`)
- Consulter les commandes en préparation
- Marquer une commande comme prête
- Suivi en temps réel

## Structure du Projet

```
src/
├── main/
│   ├── java/com/example/cdapizza/
│   │   ├── controller/          # Contrôleurs REST
│   │   ├── model/              # Entités JPA
│   │   ├── repository/         # Repositories Spring Data
│   │   ├── service/            # Services métier
│   │   └── CdapizzaApplication.java
│   └── resources/
│       ├── templates/          # Templates Thymeleaf
│       ├── application.properties
│       └── data.sql           # Données d'initialisation
└── test/                      # Tests unitaires
```

## Base de Données

### Tables Principales
- `pizzas` - Catalogue des pizzas
- `commandes` - Commandes clients
- `lignes_commande` - Détail des pizzas par commande

### Données d'Initialisation
- 10 pizzas de base avec prix et descriptions
- Données insérées automatiquement au premier démarrage

## Développement

### Ajout de Nouvelles Fonctionnalités
1. Créer l'entité JPA si nécessaire
2. Créer le repository
3. Créer le service métier
4. Créer le contrôleur
5. Créer les templates Thymeleaf

### Tests
```bash
mvn test
```

## Contribution

1. Fork le projet
2. Créer une branche feature (`git checkout -b feature/nouvelle-fonctionnalite`)
3. Commit les changements (`git commit -am 'Ajout nouvelle fonctionnalité'`)
4. Push vers la branche (`git push origin feature/nouvelle-fonctionnalite`)
5. Créer une Pull Request

## Licence

Ce projet est sous licence MIT. Voir le fichier `LICENSE` pour plus de détails.
