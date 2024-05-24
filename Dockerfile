FROM openjdk:22-jdk-bookworm
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]