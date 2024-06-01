FROM adoptopenjdk:8-jdk-hotspot

WORKDIR /TheNews-backend

COPY . .

RUN ./mvnw clean install

CMD ["./mvnw", "spring-boot:run"]
