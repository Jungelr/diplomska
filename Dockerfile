FROM ubuntu:latest
RUN apt update -y
RUN apt install -y git make gcc wget
RUN git clone --depth 1 --branch v79 https://github.com/joan2937/pigpio.git
RUN cd ./pigpio && make && make install
RUN pigpiod
RUN wget https://download.oracle.com/java/22/latest/jdk-22_linux-aarch64_bin.tar.gz
RUN mkdir -p /usr/lib/jvm
RUN tar -zxf jdk-*_linux-aarch64_bin.tar.gz -C /usr/lib/jvm
ENV JAVA_HOME="/usr/lib/jvm/jdk-22.0.1"
ENV PATH="${JAVA_HOME}/bin:${PATH}"
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]