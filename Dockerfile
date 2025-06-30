# Build stage
FROM amazoncorretto:21 AS builder
WORKDIR /app
COPY . .
RUN ./gradlew assemble --no-daemon

# Runtime stage
FROM amazoncorretto:21
COPY --from=builder /app/build/libs/*-all.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]