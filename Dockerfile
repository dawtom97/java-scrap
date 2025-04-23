# Start from a base Java image
FROM eclipse-temurin:17-jdk

# Set the working directory
WORKDIR /app

# Copy the built jar file
COPY target/*.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
