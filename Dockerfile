FROM maven:3.8.4-openjdk-17 AS build
COPY  . .
RUN mvn clean package -DskipTests 

COPY --from=build target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]