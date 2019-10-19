FROM openjdk:8-jdk-alpine
ADD target/soa-http-endpoint-1.0-SNAPSHOT.jar soa-http-endpoint-1.0-SNAPSHOT.jar
EXPOSE 8096
ENTRYPOINT ["java","-jar","soa-http-endpoint-1.0-SNAPSHOT.jar"]