# Build Process
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean -e package -DskipTests
RUN ls -la /app

# Running Process
FROM eclipse-temurin:21 as runner
WORKDIR /app
COPY --from=builder /app/target/*SNAPSHOT.jar ./app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
