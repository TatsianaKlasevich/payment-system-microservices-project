FROM adoptopenjdk/openjdk11:alpine
EXPOSE 8081
COPY build/libs/user-service-1.0-SNAPSHOT.jar .
RUN apk add --no-cache bash
COPY wait-for.sh /wait-for.sh
RUN chmod +x /wait-for.sh
ENTRYPOINT ["/wait-for.sh", "gateway-service:8989", "--timeout=90", "--", "java", "-jar", "user-service-1.0-SNAPSHOT.jar"]


