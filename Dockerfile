# Build stage
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn -B clean package -DskipTests

# Package stage (use jammy so we have bash)
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Copy jar and helper script
COPY --from=build /app/target/facebook-0.0.1-SNAPSHOT.jar /app/app.jar
COPY wait-for-db.sh /app/wait-for-db.sh
RUN chmod +x /app/wait-for-db.sh

EXPOSE 8080
ENTRYPOINT ["/app/wait-for-db.sh"]