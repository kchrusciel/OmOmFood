FROM openjdk:8-jre-alpine
ADD target/config-service.jar config-service.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "config-service.jar"]