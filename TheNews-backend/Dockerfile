FROM maven:3.8.1-jdk-8

WORKDIR /TheNews-backend

COPY . .

RUN mvn clean install -DskipTests

CMD ["java", "-jar", "target/TheNews-0.0.1-SNAPSHOT.jar"]
