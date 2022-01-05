FROM adoptopenjdk/openjdk11:alpine
EXPOSE 8082
COPY build/libs/payment-service-1.0-SNAPSHOT.jar .
RUN apk add --no-cache bash
COPY wait-for.sh /wait-for.sh
RUN chmod +x /wait-for.sh
ENTRYPOINT ["/wait-for.sh", "user-service:8081", "--timeout=110", "--", "java", "-jar", "payment-service-1.0-SNAPSHOT.jar"]

