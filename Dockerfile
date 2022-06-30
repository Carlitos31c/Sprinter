FROM openjdk:8-jdk-alpine
ADD target/sprinter-*.jar sprinter-ms.jar
ENTRYPOINT ["java","-Dspring.profiles.active=local","-jar","/sprinter-ms.jar"]