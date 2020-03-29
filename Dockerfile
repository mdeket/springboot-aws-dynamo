FROM openjdk:8
EXPOSE 80
COPY . /app
WORKDIR /app
RUN ./mvnw package
ENTRYPOINT java -Dspring.profiles.active=aws -jar target/demo-0.0.1-SNAPSHOT.jar