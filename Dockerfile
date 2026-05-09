# Use Maven image for building
FROM maven:3.9.4-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy pom.xml first for better caching
COPY pom.xml .

# Download dependencies (gunakan mvn langsung, bukan ./mvnw)
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use Eclipse Temurin Alpine for runtime
FROM eclipse-temurin:17-jre-alpine

# Install curl for health checks (alpine uses apk)
RUN apk add --no-cache curl

WORKDIR /app

# Copy the built jar from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Create non-root user (alpine syntax)
RUN addgroup -g 1000 -S appuser && adduser -S appuser -G appuser -u 1000
RUN chown -R appuser:appuser /app
USER appuser

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "app.jar"]