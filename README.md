# 🏦 Gestionnaire Bancaire - API REST Spring Boot

Mon premier projet Spring Boot - Une API REST sécurisée pour la gestion de comptes bancaires.

## 📋 Description

Application bancaire permettant de gérer des comptes avec les opérations suivantes :
- Inscription et authentification (JWT)
- Création de compte lié à l'utilisateur connecté
- Consultation de compte
- Dépôt d'argent
- Retrait d'argent
- Liste des comptes de l'utilisateur connecté
- Virement entre comptes
- Suppression de compte

## 🛠️ Technologies utilisées

- **Java 17**
- **Spring Boot 4.0.2**
- **Spring Security + JWT (jjwt 0.12.6)**
- **Maven**
- **Lombok**
- **Spring Web**
- **Spring Data JPA / Hibernate**
- **MySQL 8.0**
- **Docker**
- **Bean Validation**
- **Swagger / OpenAPI**
- **JUnit 5 / Mockito**

## 📦 Architecture

Le projet suit l'architecture en couches :
```
src/
├── main/
│   ├── java/com/micka/banque/
│   │   ├── config/         # Configuration Swagger
│   │   ├── controller/     # Endpoints REST (CompteController, AuthController)
│   │   ├── dto/            # Objets de transfert (CompteRequest, OperationRequest,
│   │   │                   # VirementRequest, AuthRequest, AuthResponse, RegisterRequest)
│   │   ├── exception/      # Gestion des erreurs (404, 400, 403)
│   │   ├── model/          # Entités JPA (Compte, User)
│   │   ├── repository/     # Accès base de données (CompteRepository, UserRepository)
│   │   ├── security/       # JWT + Spring Security (JwtService, JwtAuthFilter,
│   │   │                   # SecurityConfig, UserDetailsServiceImpl)
│   │   └── service/        # Logique métier (CompteService, AuthService)
│   └── resources/
│       └── application.properties
├── test/
│   └── java/com/micka/banque/
│       └── service/        # Tests unitaires (CompteServiceTest)
└── docker-compose.yml
```

## 🚀 Lancement de l'application

### Prérequis
- Java 17
- Docker

### Étapes
```bash
# Cloner le projet
git clone https://github.com/Mickael-DP/gestionnaire-bancaire-spring-boot

# Aller dans le répertoire
cd gestionnaire-bancaire-spring-boot

# Lancer MySQL avec Docker
docker-compose up -d

# Lancer l'application
./mvnw spring-boot:run
```

L'application sera accessible sur `http://localhost:8080`

La documentation Swagger sera accessible sur `http://localhost:8080/swagger-ui/index.html`

## 🔐 Authentification

L'API utilise JWT. Pour accéder aux endpoints protégés :

1. Créer un compte via `POST /auth/register`
2. Se connecter via `POST /auth/login` pour obtenir un token
3. Ajouter le token dans le header de chaque requête :

```
Authorization: Bearer <token>
```

## 📡 Endpoints disponibles

### Authentification
```http
POST /auth/register   # Créer un compte utilisateur (username généré auto : prenom.nom)
POST /auth/login      # Se connecter par email + password → retourne un token JWT
GET  /auth/me         # Récupérer les infos de l'utilisateur connecté
```

### Comptes (🔒 Token requis)
```http
GET    /api/comptes              # Lister les comptes de l'utilisateur connecté
POST   /api/comptes              # Créer un compte lié à l'utilisateur connecté
GET    /api/comptes/{id}         # Consulter un compte
PUT    /api/comptes/{id}/depot   # Faire un dépôt
PUT    /api/comptes/{id}/retrait # Faire un retrait
POST   /api/comptes/virement     # Virement entre comptes
DELETE /api/comptes/{id}         # Supprimer un compte
```

## 🔒 Sécurité

- Chaque compte appartient à un utilisateur (`@ManyToOne` entre `Compte` et `User`)
- Un utilisateur ne peut voir et modifier que ses propres comptes
- Erreur 403 si tentative d'accès aux comptes d'autrui
- Connexion par email (le `getUsername()` de `UserDetails` retourne l'email)
- CORS configuré pour `http://localhost:4200`

## ⚠️ Gestion des erreurs

| Code | Description |
|------|-------------|
| 400  | Données invalides ou solde insuffisant |
| 401  | Non authentifié |
| 403  | Accès refusé (compte appartenant à un autre utilisateur) |
| 404  | Compte introuvable |

## 🎯 Prochaines améliorations

- [x] Intégration base de données (MySQL + Docker)
- [x] Gestion des exceptions
- [x] Validation des données
- [x] Endpoint virement entre comptes
- [x] Documentation Swagger
- [x] Tests unitaires
- [x] Authentification Spring Security + JWT
- [x] Liaison comptes ↔ utilisateurs
- [x] Connexion frontend Angular
- [ ] Historique des mouvements

## 👨‍💻 Auteur

**Mickael DALLE PASQUALINE** - Apprentissage Spring Boot

## 📄 Licence

Ce projet est à but éducatif.