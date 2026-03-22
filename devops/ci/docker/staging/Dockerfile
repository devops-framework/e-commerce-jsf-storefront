# Stage 1: Build with Maven
FROM maven:3.8.4-openjdk-17 AS builder

# Copy the project files
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Create the final image with Payara
FROM payara/micro:6.2025.11-jdk17

# Copy the WAR file from the builder stage
COPY --from=builder /app/target/storefront.war ${DEPLOY_DIR}

# The application will be automatically deployed by Payara
EXPOSE 8080
