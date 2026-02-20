# 🏦 Gestionnaire Bancaire - API REST Spring Boot

Mon premier projet Spring Boot - Une API REST pour la gestion de comptes bancaires.

## 📋 Description

Application bancaire permettant de gérer des comptes avec les opérations suivantes :
- Création de compte
- Consultation de compte
- Dépôt d'argent
- Retrait d'argent
- Liste de tous les comptes
- Virement entre comptes

## 🛠️ Technologies utilisées

- **Java 17**
- **Spring Boot 4.0.2**
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
│   │   ├── controller/     # Endpoints REST (CompteController)
│   │   ├── exception/      # Gestion des erreurs (404, 400)
│   │   ├── model/          # Entités JPA (Compte)
│   │   ├── repository/     # Accès base de données (CompteRepository)
│   │   └── service/        # Logique métier (CompteService)
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

## 📡 Endpoints disponibles

### Lister tous les comptes
```http
GET /api/comptes
```

### Créer un compte
```http
POST /api/comptes?titulaire=NomPrenom&type=COURANT
```

### Consulter un compte
```http
GET /api/comptes/{id}
```

### Faire un dépôt
```http
PUT /api/comptes/{id}/depot?montant=100
```

### Faire un retrait
```http
PUT /api/comptes/{id}/retrait?montant=50
```

### Virement entre deux comptes
```http
POST /api/comptes/virement?idSource=1&idDestination=2&montant=100
```

## ⚠️ Gestion des erreurs

| Code | Description |
|------|-------------|
| 404  | Compte introuvable |
| 400  | Solde insuffisant ou données invalides |

## 📝 Exemple d'utilisation avec Postman

1. **Créer un compte :**
   - Méthode : `POST`
   - URL : `http://localhost:8080/api/comptes`
   - Params : `titulaire=John Doe`, `type=COURANT`

2. **Faire un dépôt :**
   - Méthode : `PUT`
   - URL : `http://localhost:8080/api/comptes/1/depot`
   - Params : `montant=500`

3. **Consulter le solde :**
   - Méthode : `GET`
   - URL : `http://localhost:8080/api/comptes/1`

4. **Faire un virement :**
   - Méthode : `POST`
   - URL : `http://localhost:8080/api/comptes/virement`
   - Params : `idSource=1`, `idDestination=2`, `montant=100`

## 🎯 Prochaines améliorations

- [x] Intégration base de données (MySQL + Docker)
- [x] Gestion des exceptions
- [x] Validation des données
- [x] Endpoint virement entre comptes
- [x] Documentation Swagger
- [x] Tests unitaires
- [ ] Connexion frontend Angular

## 👨‍💻 Auteur

**Mickael DALLE PASQUALINE** - Apprentissage Spring Boot

## 📄 Licence

Ce projet est à but éducatif.