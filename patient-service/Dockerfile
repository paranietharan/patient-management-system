######### Build application ################
FROM maven:3.9-eclipse-temurin-25 AS builder

WORKDIR /app

# Cache dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build package (skipping tests for faster build)
COPY src ./src
RUN mvn clean package -DskipTests

######### Runtime environment ###############
FROM eclipse-temurin:25-jre AS runner

WORKDIR /app

# Create non-root user for security
RUN groupadd -r spring && useradd -r -g spring spring
USER spring:spring

# Copy built JAR from builder stage
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]