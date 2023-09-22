FROM openjdk:17.0.1-jdk-slim

VOLUME /msa
ARG JAR_FILE=/build/libs/aidt-eureka-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} ../../eureka.jar
ENTRYPOINT ["java","-jar","/eureka.jar"]