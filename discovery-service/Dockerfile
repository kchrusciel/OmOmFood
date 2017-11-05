FROM openjdk:8-jre-alpine
ADD target/discover-service.jar discover-service.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "discover-service.jar"]