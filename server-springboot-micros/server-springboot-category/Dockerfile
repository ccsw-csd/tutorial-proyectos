# Use the official Ubuntu 22.04 LTS base image
FROM ubuntu:22.04

# Install necessary packages
RUN apt-get update && apt-get install -y \
    openjdk-19-jdk \
    maven \
    wget \
    curl \
    gnupg \
    && rm -rf /var/lib/apt/lists/*

# Set the working directory
WORKDIR /app

# Copy the project files to the container
COPY . .

# Build the project using Maven
RUN mvn clean package

# Expose the application port
EXPOSE 8091

# Run the Spring Boot application
CMD ["java", "-jar", "target/tutorial-category-0.0.1-SNAPSHOT.jar"]