# 📊 Dynamic Percentage Calculator API

Una API REST en Spring Boot (Java 21) que realiza cálculos con un porcentaje dinámico, almacena historial de llamadas en PostgreSQL y está preparada para producción con Docker.

---

## 🚀 Funcionalidades

1. **Cálculo con porcentaje dinámico**  
   Endpoint que recibe `num1` y `num2`, los suma y aplica un porcentaje adicional obtenido desde un servicio externo (simulado).

2. **Caché del porcentaje**  
   El porcentaje se guarda en memoria durante 30 minutos.  
   Si el servicio externo falla, se usa el último valor; si no hay, se devuelve un error.

3. **Historial de llamadas (asíncrono)**  
   Todas las llamadas se registran con fecha, parámetros, respuesta o error, y se guardan en PostgreSQL sin afectar el rendimiento del cálculo.

---

## 📦 Endpoints principales

| Método | URL                         | Descripción                            |
|--------|-----------------------------|----------------------------------------|
| GET    | `/api/calculate`            | Calcula con porcentaje dinámico        |
| GET    | `/api/logs?page=0&size=5`   | Devuelve historial de llamadas         |

---

## 🛠️ Tecnologías usadas

- Java 21 + Spring Boot 3
- PostgreSQL (via Docker)
- Spring Cache + Scheduled
- Swagger (OpenAPI)
- Docker + Docker Compose
- JUnit 5 + Mockito

---

## 🐳 Cómo ejecutar el proyecto

### ✅ Requisitos
- Docker + Docker Compose
- JDK 21
- Maven

### 📥 1. Clonar el repositorio
```bash
git clone https://github.com/marcelorsosa/dynamic-percentage-api.git
cd dynamic-percentage-api
cd challenge

docker-compose up --build

Documentación Swagger
Una vez levantado el proyecto, accedé a:
👉 http://localhost:8080/swagger-ui.html
