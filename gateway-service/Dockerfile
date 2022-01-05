FROM adoptopenjdk/openjdk11:alpine
EXPOSE 8989
COPY build/libs/gateway-service-1.0-SNAPSHOT.jar .
RUN apk add --no-cache bash
COPY wait-for.sh /wait-for.sh
RUN chmod +x /wait-for.sh
ENTRYPOINT ["/wait-for.sh", "auth-service:8282", "--timeout=70", "--", "java", "-jar", "gateway-service-1.0-SNAPSHOT.jar"]

