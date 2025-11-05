FROM amazoncorretto:21-alpine3.15
LABEL MANTAINER="Ecommerce"

WORKDIR /app

ARG JAR_FILE=target/*.jar

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
