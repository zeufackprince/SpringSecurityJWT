FROM maven:3.9.6-eclipse-temurin-17 AS build
COPY  . .
RUN mvn clean package -DskipTests 

FROM eclipse-temurin:17-jre-alpine
COPY --from=build target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]