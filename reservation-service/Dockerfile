FROM openjdk:8-jre-alpine
RUN apk add --no-cache openssl

ENV DOCKERIZE_VERSION v0.5.0
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz
ADD target/reservation-service.jar reservation-service.jar
EXPOSE 8010
EXPOSE 8002
ENTRYPOINT ["dockerize", "-timeout", "5m", "-wait", "http://config-service:8888/health", "java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8002", "-jar", "reservation-service.jar"]