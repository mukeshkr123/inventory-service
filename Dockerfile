# Use a base image with Java runtime
FROM openjdk:17-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file from the target directory
COPY target/inventory-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port (default is 8080)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
