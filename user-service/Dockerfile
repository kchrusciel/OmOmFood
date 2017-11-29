FROM openjdk:8-jre-alpine
RUN apk add --no-cache openssl

ENV DOCKERIZE_VERSION v0.5.0
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz
ADD target/user-service.jar user-service.jar
EXPOSE 8020
EXPOSE 8021
ENTRYPOINT ["dockerize", "-timeout", "5m", "-wait", "http://config-service:8888/health", "java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8021", "-jar", "user-service.jar"]