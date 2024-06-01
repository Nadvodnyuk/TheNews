# Stage 1: Build the application
#FROM maven:3.8.5-openjdk-17 AS build
#WORKDIR /app
#COPY . .
#RUN mvn clean install -DskipTests

# Stage 2: Run the application
#FROM adoptopenjdk:8-jre-hotspot
#WORKDIR /TheNews-backend
#COPY --from=build /app/target/TheNews-0.0.1-SNAPSHOT.jar /TheNews-backend/TheNews-0.0.1-SNAPSHOT.jar
#CMD ["java", "-jar", "/TheNews-backend/TheNews-0.0.1-SNAPSHOT.jar"]

FROM maven:3.8.1-jdk-8

WORKDIR /TheNews-backend

COPY . .

RUN mvn clean install -DskipTests

#CMD ["java", "-jar", "TheNews-backend/target/TheNews-0.0.1-SNAPSHOT.jar"]
CMD ["java", "-jar", "target/TheNews-0.0.1-SNAPSHOT.jar"]
