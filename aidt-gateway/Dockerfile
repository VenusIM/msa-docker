FROM openjdk:17.0.1-jdk-slim

VOLUME /msa
ARG JAR_FILE=/build/libs/aidt-gateway-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} ../../gateway.jar
ENTRYPOINT ["java","-jar","/gateway.jar"]

ENV spring.rabbitmq.host=rabbitmq
ENV eureka.client.serviceUrl.defaultZone=http://eureka:8761/eureka/
ENV spring.zipkin.base-url=http://zipkin:9411/
ENV spring.cloud.config.uri=http://config:8888/
ENV spring.config.import=optional:configserver:http://config:8888
