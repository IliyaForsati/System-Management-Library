FROM ubuntu:22.04

RUN apt-get update \
 && apt-get install openjdk-17-jdk \
    maven -y \
 && rm -rf /var/lib/apt/lists/*


RUN java -version && mvn -version

WORKDIR /home/app

COPY . /app
