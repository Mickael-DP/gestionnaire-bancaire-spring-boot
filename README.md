# 🏦 Banque Spring Boot - API REST

Mon premier projet Spring Boot - Une API REST pour la gestion de comptes bancaires.

## 📋 Description

Application bancaire permettant de gérer des comptes avec les opérations suivantes :
- Création de compte
- Consultation de compte
- Dépôt d'argent
- Retrait d'argent
- Liste de tous les comptes

## 🛠️ Technologies utilisées

- **Java 17**
- **Spring Boot 4.0.2**
- **Maven**
- **Lombok**
- **Spring Web**

## 📦 Architecture

Le projet suit l'architecture en 3 couches :
```
src/
├── model/          # Entités (Compte)
├── service/        # Logique métier (CompteService)
└── controller/     # Endpoints REST (CompteController)
```

## 🚀 Lancement de l'application
```bash
# Cloner le projet
git clone https://github.com/Mickael-DP/gestionnaire-bancaire-spring-boot

# Aller dans le répertoire
cd gestionnaire-bancaire-spring-boot

# Lancer l'application
./mvnw spring-boot:run
```

L'application sera accessible sur `http://localhost:8080`

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

## 🎯 Prochaines améliorations

- [ ] Intégration base de données (H2/MySQL)
- [ ] Gestion des exceptions
- [ ] Validation des données
- [ ] Endpoint virement entre comptes
- [ ] Documentation Swagger
- [ ] Tests unitaires

## 👨‍💻 Auteur

**Mickael DALLE PASQUALINE** - Apprentissage Spring Boot

## 📄 Licence

Ce projet est à but éducatif.