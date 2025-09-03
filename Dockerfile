FROM openjdk:17-jdk-slim


ADD target/*.jar Backend-0.0.1-SNAPSHOT.jar

EXPOSE 8087

CMD ["java", "-jar", "Backend-0.0.1-SNAPSHOT.jar"]