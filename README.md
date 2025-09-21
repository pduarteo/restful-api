# RESTful API — Spring Boot + PostgreSQL

RESTful **Users** API built with Spring Boot 3 and PostgreSQL, following Roy Fielding's REST principles:

- Versioned resources (`/api/v1/users`)
- Correct HTTP verbs (`GET`, `POST`, `PUT`, `DELETE`)
- Hypermedia (HATEOAS) in response links
- Content negotiation with vendor media types
- Status code patterns and clear messages

---

## Technologies

- Java 21
- Spring Boot 3 (Web, Data JPA, Validation, HATEOAS)
- PostgreSQL 16
- Docker + Docker Compose
- Postman (collection in `/docs`)

---

## Prerequisites

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/)

Optional:
- [Maven](https://maven.apache.org/) for local build

---

## Configuration

The project uses a `.env` file to configure the database and profiles:

```env
SPRING_PROFILES_ACTIVE=xxxxxxxxxxxxxxx
POSTGRES_DB=xxxxxxxxxxxxxxx
POSTGRES_USER=xxxxxxxxxxxxxxx
POSTGRES_PASSWORD=xxxxxxxxxxxxxxx
POSTGRES_PORT=xxxxxxxxxxxxxxx
POSTGRES_HOST=xxxxxxxxxxxxxxx
```
# How to run the project

## Uploading the environment with Docker

### 1. Upload from JAR (optional)
If you want to build manually before running:
```bash
./mvnw clean package -DskipTests
```

### 2. Upload containers (API + PostgreSQL)
```bash
docker compose up --build
```

##### Or in the background:
```bash
docker compose up -d --build
```

### The application will be available at: [http://localhost:8080/api](http://localhost:8080/api)



### 3. View logs
```
docker compose logs -f app
 docker compose logs -f db
```

### 4. Shut down containers
```bash
docker compose down
```

---

## Example of API usage

### Create user
**POST** `/api/v1/users`

**Headers**
```headers
Content-Type: application/vnd.pduarteo.user.v1+json
Accept: application/vnd.pduarteo.user.v1+json
```

**Body**
```json
{
  "name": "João Silva",
  "email": "joao.silva@example.com",
  "passwordHash" : "S3cr3t@"
}

```

**RESPONSE**
```json
{
  "id": "b8cc2df4-8092-4365-9075-9719bf925cd2",
  "name": "João Silva",
  "email": "joao.silva@example.com",
  "createdAt": "2025-09-21T18:31:30.999373-03:00",
  "_links": [
    {
      "rel": "self",
      "href": "http://localhost:8080/api/v1/users/b8cc2df4-8092-4365-9075-9719bf925cd2",
      "type": "application/vnd.pduarteo.user.v1+json"
    },
    {
      "rel": "update",
      "href": "http://localhost:8080/api/v1/users/b8cc2df4-8092-4365-9075-9719bf925cd2",
      "type": "application/vnd.pduarteo.user.v1+json"
    },
    {
      "rel": "delete",
      "href": "http://localhost:8080/api/v1/users/b8cc2df4-8092-4365-9075-9719bf925cd2"
    }
  ]
}

```

## Testing with Postman

This project includes a **Postman** collection to facilitate testing of the User API.

---

## How to import into Postman

1. Open Postman
2. Click **Import**
3. Select **Upload Files**
4. Choose the file `UserAPI.postman_collection.json` in `/docs`
5. The collection will appear ready to use in your workspace

---

## Collection structure

The collection contains requests organized for the main API endpoints:

- **POST** `/api/v1/users` → Create user
- **GET** `/api/v1/users/{id}` → Search by ID
- **PUT** `/api/v1/users/{id}` → Update user
- **DELETE** `/api/v1/users/{id}` → Delete user

---




