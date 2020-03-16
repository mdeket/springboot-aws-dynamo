FROM openjdk:8
COPY . /app
WORKDIR /app
RUN ./mvnw package
COPY target/demo-0.0.1-SNAPSHOT.jar demo.jar
ENTRYPOINT java -jar demo.jar