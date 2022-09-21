FROM openjdk:18

COPY target/kafka-consumer.jar consumer.jar
ENTRYPOINT ["java","-jar","/consumer.jar"]
