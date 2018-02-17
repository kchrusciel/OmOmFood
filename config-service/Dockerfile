FROM openjdk:8-jre-alpine
RUN apk add --no-cache openssl

ENV DOCKERIZE_VERSION v0.5.0
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz
RUN apk upgrade --update && \
    apk add --update curl unzip && \
    curl -jksSLH "Cookie: oraclelicense=accept-securebackup-cookie" -o /tmp/unlimited_jce_policy.zip "http://download.oracle.com/otn-pub/java/jce/8/jce_policy-8.zip" && \
    unzip -jo -d ${JAVA_HOME}/jre/lib/security /tmp/unlimited_jce_policy.zip && \
    apk del curl unzip && \
    rm -rf /tmp/* /var/cache/apk/*
ADD target/config-service.jar config-service.jar
EXPOSE 8888
EXPOSE 8889
ENTRYPOINT ["dockerize", "-timeout", "5m", "-wait", "http://discovery-service:8761", "java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8889", "-jar", "config-service.jar"]