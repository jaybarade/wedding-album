FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy only required files first (better caching)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Give permission to mvnw
RUN chmod +x mvnw

# Download dependencies first
RUN ./mvnw dependency:go-offline

# Now copy full project
COPY src src

# Build jar
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/backend-0.0.1-SNAPSHOT.jar"]
