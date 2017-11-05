FROM openjdk:8-jre-alpine
ADD target/offer-service.jar offer-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "offer-service.jar"]