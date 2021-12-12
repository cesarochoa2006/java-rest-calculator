## Build and compile stage
FROM maven:3.6.0-jdk-11-slim AS build
WORKDIR /usr/src/app
COPY . .
RUN mvn clean install -U

## Package artifacts stage
FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /usr/src/app
COPY --from=build /usr/src/app/main-spring-app/main-app/target/main-app-1.0.0.jar .
EXPOSE 8888
CMD ["java", "-jar", "main-app-1.0.0.jar"]

