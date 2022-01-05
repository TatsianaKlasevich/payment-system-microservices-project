FROM adoptopenjdk/openjdk11:alpine
COPY build/libs/notification-service-1.0-SNAPSHOT.jar .
RUN apk add --no-cache bash
COPY wait-for.sh /wait-for.sh
RUN chmod +x /wait-for.sh
ENTRYPOINT ["/wait-for.sh", "user-service:8081", "--timeout=130", "--", "java", "-jar", "notification-service-1.0-SNAPSHOT.jar"]

