# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Set the working directory
WORKDIR /app

# Add Maven dependencies (this way they will be cached unless POM changes)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the project source code
COPY src ./src

# Show Maven and Java versions for debugging
RUN mvn -v && java -version

# Show dependency tree for debugging
RUN mvn dependency:tree

# Clean and package the application
RUN mvn clean package -DskipTests

# Copy the packaged jar file to the container
COPY target/UserAuthentication-0.1.jar UserAuthentication-0.1.jar

# Copy wait-for-it script
COPY wait-for-it.sh /app/wait-for-it.sh

# Expose the port your app runs on
EXPOSE 8080

# Define environment variables for localization
ENV LANG=en_US.UTF-8
ENV LANGUAGE=en_US:en
ENV LC_ALL=en_US.UTF-8

# Run the application
CMD ["./wait-for-it.sh", "db:3306", "--","java", "-jar", "UserAuthentication-0.1.jar"]
