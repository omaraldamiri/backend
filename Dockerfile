# Use an OpenJDK image with Maven
FROM maven:3.8.5-openjdk-17-slim AS build

# Set working directory
WORKDIR /app

# Copy pom and src
COPY pom.xml .
COPY src ./src

# Package the app
RUN mvn clean package -DskipTests

# Use a lighter image for the final run
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the jar from the builder stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]