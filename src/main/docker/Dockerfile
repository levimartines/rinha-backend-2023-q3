FROM openjdk:17-alpine
COPY build/libs/*.jar service.jar
ENTRYPOINT ["java", "-jar", "service.jar"]