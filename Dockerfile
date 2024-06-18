FROM openjdk:22-jdk
RUN microdnf install -y git
RUN microdnf install -y make
RUN microdnf install -y gcc
RUN git clone https://github.com/joan2937/pigpio.git
RUN cd ./pigpio && ls && make &&sleep 10
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]