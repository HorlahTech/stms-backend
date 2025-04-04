# FROM maven:3.8.3-openjdk-17 AS build
# COPY . .
# RUN mvn clean package -DskipTests
# FROM openjdk:17-jdk-slim
# COPY --from=build /target/stms-0.0.1-SNAPSHOT.jar stms.jar
# EXPOSE 8080
# ENTRYPOINT ["java", "-jar", "stms.jar"]


# Build stage
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/stms-0.0.1-SNAPSHOT.jar stms.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "stms.jar"]
