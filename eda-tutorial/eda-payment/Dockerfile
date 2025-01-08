FROM ubuntu:18.04
LABEL authors="kkkkkksssssaaaa"

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    apt-get clean;

WORKDIR /app

COPY build/libs/eda-payment.jar /app/app.jar

CMD ["java", "-jar", "app.jar"]