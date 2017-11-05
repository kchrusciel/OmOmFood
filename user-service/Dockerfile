FROM openjdk:8-jre-alpine
ADD target/user-service.jar user-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "user-service.jar"]