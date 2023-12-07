FROM openjdk:8-jdk-alpine
ADD target/org-0.0.1-SNAPSHOT.jar demo1.jar
ENTRYPOINT ["java", "-jar", "demo1.jar"]