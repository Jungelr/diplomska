FROM arm64v8/openjdk:22
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
