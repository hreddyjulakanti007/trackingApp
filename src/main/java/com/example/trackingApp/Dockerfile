# Start from an OpenJDK base image
FROM openjdk:17-jdk-alpine

# Add a volume pointing to /tmp
VOLUME /tmp

# Copy the jar file to the container
COPY target/trackingApp.jar app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]
