## Running the Project with Docker

This project includes a multi-stage Docker setup for building and running a Java 17 Spring Boot application. The provided `Dockerfile` and `docker-compose.yml` files allow you to containerize and run the application easily.

### Requirements & Versions
- **Java Version:** Eclipse Temurin 17 (JDK for build, JRE for runtime)
- **Build Tool:** Maven Wrapper (`mvnw`)
- **Docker Compose:** Uses a single service named `java-app`

### Environment Variables
- No required environment variables are specified by default in the Docker or Compose files.
- If you need to provide environment variables (e.g., for Spring profiles or secrets), uncomment and use the `env_file` section in `docker-compose.yml`.

### Build and Run Instructions
1. **Build and start the application:**
   ```sh
   docker compose up --build
   ```
   This will build the Docker image using the multi-stage `Dockerfile` and start the `java-app` service.

2. **Access the application:**
   - The application will be available on [http://localhost:8080](http://localhost:8080)

### Ports
- **8080:** Exposed by the `java-app` service (mapped to host port 8080)

### Special Configuration
- The application runs as a non-root user (`appuser`) inside the container for improved security.
- JVM is configured for container resource awareness via `JAVA_OPTS`.
- No external services (such as databases) are configured by default. If your application requires additional services, update `docker-compose.yml` accordingly.

---

_If you add environment variables or external services (like a database), update the `docker-compose.yml` and this section to reflect those changes._
