# kkek-backend

Backend API Service for KKEK built with **Java 25**, **Spring Boot 4**, and **Docker**.

## Architecture & Features
* **Java 25 & Spring Boot 4**: Modern LTS runtime with virtual threads & high-throughput concurrency.
* **REST Endpoints**:
  * `GET /api/health`: Health status & system information.
  * `GET /api/info`: JVM runtime memory stats and available processors.
  * `GET /actuator/health`: Spring Boot Actuator diagnostics.
* **Docker & Docker Compose**: Multi-stage build container with Redis integration ready out-of-the-box.
* **CORS Configured**: Pre-configured to accept requests from frontend clients (`kkek.dev` and local dev).

## Running Locally

### With Maven Wrapper:
```bash
./mvnw spring-boot:run
```

### With Docker Compose:
```bash
docker compose up -d --build
```
Access at: `http://localhost:8080/api/health`
