FROM openjdk:22-jdk
RUN microdnf install git
RUN microdnf install make gcc
RUN git clone --depth 1 --branch v79 https://github.com/joan2937/pigpio.git
RUN cd ./pigpio && make
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]