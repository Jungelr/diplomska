FROM openjdk:22-jdk
RUN apt update
RUN apt install -y pigpio sudo
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]