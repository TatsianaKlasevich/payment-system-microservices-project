FROM adoptopenjdk/openjdk11:alpine
EXPOSE 8282
COPY build/libs/auth-service-1.0-SNAPSHOT.jar .
RUN apk add --no-cache bash
COPY wait-for.sh /wait-for.sh
RUN chmod +x /wait-for.sh
ENTRYPOINT ["/wait-for.sh", "registry-service:8761", "--timeout=70", "--", "java", "-jar", "auth-service-1.0-SNAPSHOT.jar"]

