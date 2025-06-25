# 💱 Currency Service

A backend REST API built with Spring Boot for managing currencies. It supports full CRUD operations, dynamic search with pagination, caching using Redis, and is fully containerized using Docker Compose.

---

## 🚀 Tech Stack

- **Java 17+**
- **Spring Boot 3**
- **Spring Data with MyBatis**
- **MySQL 8.2**
- **Redis 7.2** (for caching)
- **Docker & Docker Compose**
- **Lombok & MapStruct**
- **Swagger/OpenAPI**
- **JUnit & Mockito**

---

## 🏗️ Features

- Create, update, and delete currencies
- Fetch a currency by ID or retrieve all
- Search currencies with filters and pagination
- Redis-based caching (GET by ID)
- Global exception handling with Spring's `ProblemDetail`
- Dockerized setup including:
  - MySQL database
  - Redis cache
  - Adminer for database inspection

---

## 🛠️ How to Run the Application

### 📦 1. Clone and Navigate to Project

```bash
git clone https://github.com/your-username/currencyservice.git
cd currencyservice
```

### 🐳 2. Start All Services via Docker Compose

```bash
docker compose up -d
```

This will start the following services:
- MySQL (port: 3307)
- Redis (port: 6379)
- Adminer (port: 9000)
- Currency Service (port: 8080)

### 🌐 3. Access Swagger UI

Open your browser and go to:

```
http://localhost:8080/swagger-ui/index.html
```

You can test all endpoints directly from Swagger UI.

---

## 📬 Sample API Requests

### 1. 🔧 Create a Currency

**POST** `/api/currencies`

```json
{
  "code": "KHR",
  "name": "Khmer Reil",
  "status": "active"
}
```

---

### 2. ✏️ Update Currency by ID

**PUT** `/api/currencies/4`

```json
{
  "code": "KHR",
  "name": "Khmer Reil",
  "status": "active"
}
```

---

### 3. ❌ Delete Currency by ID

**DELETE** `/api/currencies/1`

---

### 4. 📄 Get Currency by ID

**GET** `/api/currencies/1`

---

### 5. 📚 Get All Currencies

**GET** `/api/currencies`

---

### 6. 🔍 Search with Filters and Pagination

**GET**

```
/api/currencies/search?code=KHR&status=active&page=0&size=1
```

---

## 🧪 Run Unit Tests

```bash
./mvnw test
```

---

## 📂 Project Structure

```
controller/   -> REST endpoints
service/      -> Business logic
repository/   -> MyBatis interfaces
mapper/       -> MapStruct converters
entity/       -> Data model classes
dto/          -> Data Transfer Objects
config/       -> Redis and caching configuration
exception/    -> Custom exceptions and global handler
```

---

## 🐞 Troubleshooting

- Make sure ports `3307`, `6379`, `8080`, and `9000` are available.
- Check `docker compose logs` if services fail to start.
- The MySQL `init.sql` script only runs when the database volume is first created.

---

## 👨‍💻 Author

**Ing Piseth** – Senior Java Developer | Trainer