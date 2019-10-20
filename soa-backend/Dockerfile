FROM openjdk:8-jdk-alpine
ADD target/soa-backend-1.0-SNAPSHOT.jar soa-backend-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "-Dserver.port=8097","soa-backend-1.0-SNAPSHOT.jar"]