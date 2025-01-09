FROM openjdk:21-slim
ARG JAR_fILE=target/*.jar
COPY ./target/demo-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]