FROM openjdk:18

COPY target/kafka-producer.jar producer.jar
ENTRYPOINT ["java","-jar","/producer.jar"]