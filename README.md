# 🚀 Order Management Service (OrderMS)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Quarkus](https://img.shields.io/badge/Quarkus-4695EB?style=for-the-badge&logo=quarkus&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-FF6600?style=for-the-badge&logo=rabbitmq&logoColor=white)

A high-performance, event-driven microservice built with **Quarkus** to handle order processing. This project is a robust solution for the **BTG Pactual Backend Challenge**.

---

## 🎯 The Challenge

The goal was to build a system that digests order data, persists it, and allows for efficient querying. You can find the original challenge details [here](https://github.com/buildrun-tech/buildrun-desafio-backend-btg-pactual/blob/main/problem.md).

**Key Features:**
*   **Event-Driven:** Consumes order events asynchronously via RabbitMQ.
*   **NoSQL Persistence:** Stores order details efficiently using MongoDB with Panache.
*   **Aggregation:** Calculates total spend per customer using MongoDB Aggregation Pipelines.
*   **REST API:** Exposes clean endpoints for data retrieval.

## 🛠️ Tech Stack

*   **Core:** Java 17+, Quarkus (Supersonic Subatomic Java)
*   **Database:** MongoDB (Panache Repository Pattern)
*   **Messaging:** RabbitMQ (SmallRye Reactive Messaging)
*   **Containerization:** Docker & Docker Compose

## ⚡ Quick Start

### Prerequisites
*   JDK 17+
*   Docker & Docker Compose
*   Maven (wrapper included)

### 1. Start Infrastructure
Spin up MongoDB and RabbitMQ using the provided Docker Compose file:
```bash
docker-compose -f local/docker-compose.yaml up -d
```

### 2. Run the Application
Run in dev mode for live coding:
```bash
./mvnw quarkus:dev
```

The application will be available at `http://localhost:8080`.
Access the **Dev UI** at `http://localhost:8080/q/dev/`.

## 🔌 API Endpoints

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/customers/{id}/orders` | List all orders for a specific customer (Paginated) |
| `GET` | `/customers/{id}/total` | Get total amount spent by a customer (Coming Soon) |

## 📦 Building for Production

**Standard JVM Jar:**
```bash
./mvnw package
```
Produces `quarkus-run.jar` in `target/quarkus-app/`.

**Native Executable (GraalVM):**
```bash
./mvnw package -Dnative
```

## 📚 Reference Documentation

*   [Quarkus - MongoDB with Panache](https://quarkus.io/guides/mongodb-panache)
*   [Quarkus - RabbitMQ Reference](https://quarkus.io/guides/rabbitmq)
*   [Quarkus - REST Data](https://quarkus.io/guides/rest)

---
*Powered by Quarkus*