# Switch to a new stage for building the Spring Boot application
FROM eclipse-temurin:17-jdk-alpine as builder

# Set the working directory in the container
COPY . /app
WORKDIR /app

# Install Vim to convert CRLF to LF
RUN apk add vim

# Convert CRLF to LF for mvnw script
RUN vim mvnw -c "set ff=unix" -c ":wq"

# Make the mvnw script executable
RUN chmod +x mvnw

# Compile the Spring Boot Application and generate JAR
RUN ./mvnw clean package -DskipTests

################################################################################
################################################################################

# Switch to a new stage for running the JAR under a JRE
FROM eclipse-temurin:17-jre-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/server-side-0.0.1-SNAPSHOT.jar ./app.jar

# Expose the port that the Spring Boot application will run on
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "app.jar"]

################################################################################
################################################################################