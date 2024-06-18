FROM openjdk:22-jdk
RUN microdnf install -y git
RUN microdnf install -y make
RUN git clone https://github.com/joan2937/pigpio.git
RUN ls && sleep 5
RUN cd ./pigpio && make .
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]