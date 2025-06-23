# syntax=docker/dockerfile:1

# --- Build stage ---
FROM openjdk:17-jdk-alpine AS build
WORKDIR /app

# Copy Maven wrapper and project files for dependency resolution
COPY --link pom.xml mvnw ./
COPY --link .mvn .mvn

# Download dependencies (leverages Docker cache)
RUN chmod +x mvnw && ./mvnw dependency:go-offline

# Copy the rest of the source code
COPY --link src ./src

# Package the application (skip tests for faster build)
RUN ./mvnw package -DskipTests

# --- Runtime stage ---
FROM eclipse-temurin:17-jre
WORKDIR /app

# Create a non-root user and group
RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser

# Copy the built jar from the build stage
COPY --from=build /app/target/*.jar /app/app.jar

# Set permissions
RUN chown -R appuser:appgroup /app
USER appuser

# Expose the application port
EXPOSE 8080

# JVM options for container resource awareness
ENV JAVA_OPTS="-XX:MaxRAMPercentage=80.0 -XX:+UseContainerSupport"

# Use exec form for proper signal handling
ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar /app/app.jar"]
