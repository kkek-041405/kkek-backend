# Stage 1: Build application
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw

COPY src/ src/
RUN ./mvnw clean package -DskipTests

# Stage 2: Minimal runtime image
FROM eclipse-temurin:21-jre
WORKDIR /app

# Non-root user for security
RUN groupadd -r spring && useradd -r -g spring spring
USER spring:spring

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

# Production JVM optimizations for containers (constrained RAM)
ENTRYPOINT ["java", "-XX:+UseG1GC", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]
