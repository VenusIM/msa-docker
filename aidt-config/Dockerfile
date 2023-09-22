FROM openjdk:17.0.1-jdk-slim
VOLUME /msa
ARG JAR_FILE=/build/libs/aidt-config-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} ../../config.jar
ENTRYPOINT ["java","-jar","/config.jar"]

ENV spring.rabbitmq.host=rabbitmq
ENV eureka.client.serviceUrl.defaultZone=http://eureka:8761/eureka/