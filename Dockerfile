FROM maven:3.8.1-openjdk-11-slim

COPY ./ ./

RUN mvn clean install -Dmaven.test.skip=true

ARG FILE_NAME=target/*.jar

COPY $FILE_NAME /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]