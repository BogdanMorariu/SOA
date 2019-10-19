FROM openjdk:8-jdk-alpine
ADD target/SOApp-1.0-SNAPSHOT.jar SOApp-1.0-SNAPSHOT.jar
EXPOSE 8096
ENTRYPOINT ["java","-jar","SOApp-1.0-SNAPSHOT.jar"]