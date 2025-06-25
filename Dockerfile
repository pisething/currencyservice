# Use official OpenJDK image as base
FROM eclipse-temurin:17-jdk-alpine

# Set environment variable to reduce image size
ENV JAVA_OPTS="-XX:+UseContainerSupport"

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot jar (make sure to build it first using `mvn clean package`)
COPY target/currencyservice-0.0.1-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 8080

# Start the Spring Boot application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]