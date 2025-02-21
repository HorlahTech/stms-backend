FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests
FROM openjdk:17-jdk-slim
COPY --form=build /target/stms-0.01-SNAPSHOT.jar stms.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "stms.jar"]

