# build
FROM gradle:9.3.1-jdk21-alpine AS build
WORKDIR /app
COPY . .
RUN gradle bootJar --no-daemon

# run
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]