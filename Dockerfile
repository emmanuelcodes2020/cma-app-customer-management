# Use a base image with Java 11
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory
WORKDIR /app
# Copy the JAR file to the container
COPY target/*.jar app.jar
# Expose the port that your Spring Boot application listens on
EXPOSE 8081
# Define the command to run your application
CMD ["java", "-jar", "app.jar"]
